package repository.carAccidentHandling;

import dao.AccidentCarDao;
import dao.AccidentPersonDao;
import dao.CarAccidentHandlingDao;
import domain.carAccidentHandling.*;

import java.util.ArrayList;

public class CarAccidentHandlingRepository {
    private static final CarAccidentHandlingRepository instance = new CarAccidentHandlingRepository();
    private final CarAccidentHandlingDao carAccidentHandlingDao = new CarAccidentHandlingDao();
    private final AccidentCarDao accidentCarDao = new AccidentCarDao();
    private final AccidentPersonDao accidentPersonDao = new AccidentPersonDao();

    // Singleton
    private CarAccidentHandlingRepository(){}
    public static CarAccidentHandlingRepository getInstance() { return instance; }

    public boolean create(CarAccidentHandling carAccidentHandling) {
        String id = carAccidentHandlingDao.createAndGetId(carAccidentHandling);
        if(id==null) return false;
        carAccidentHandling.setId(id);
        for(AccidentCar accidentCar : carAccidentHandling.getAccidentCars()) {
            accidentCar.setCarAccidentHandlingId(id);
            String accidentCarId = accidentCarDao.createAndGetId(accidentCar);
            if(accidentCarId==null) return false;
            accidentCar.setId(accidentCarId);
        }
        return true;
    }
    public boolean delete(String id) {
        boolean accidentCarsDeleted = accidentCarDao.deleteByCarAccidentHandlingId(id);
        boolean accidentPeopleDeleted = accidentPersonDao.deleteByCarAccidentHandlingId(id);
        if(!(accidentCarsDeleted && accidentPeopleDeleted)) return false;
        //rollback 하는 부분을 넣어야하나?
        return carAccidentHandlingDao.delete(id);
    }
    public CarAccidentHandling getById(String id) {
        CarAccidentHandling carAccidentHandling = carAccidentHandlingDao.retrieveById(id);
        ArrayList<AccidentCar> accidentCars = accidentCarDao.retrieveByCarAccidentHandlingId(id);
        ArrayList<AccidentPerson> accidentPeople = accidentPersonDao.retrieveByCarAccidentHandlingId(id);
        carAccidentHandling.setAccidentCars(accidentCars);
        carAccidentHandling.setAccidentPeople(accidentPeople);
        return carAccidentHandling;
    }
    public ArrayList<CarAccidentHandling> getAll() {
        ArrayList<CarAccidentHandling> carAccidentHandlings = carAccidentHandlingDao.retrieveAll();
        for (CarAccidentHandling carAccidentHandling : carAccidentHandlings) {
            ArrayList<AccidentCar> accidentCars = accidentCarDao.retrieveByCarAccidentHandlingId(carAccidentHandling.getId());
            carAccidentHandling.setAccidentCars(accidentCars);
            ArrayList<AccidentPerson> accidentPerson = accidentPersonDao.retrieveByCarAccidentHandlingId(carAccidentHandling.getId());
            carAccidentHandling.setAccidentPeople(accidentPerson);
        }
        return carAccidentHandlings;
    }
    public boolean update(CarAccidentHandling carAccidentHandling) {
        try {
            CarAccidentHandling before = this.getById(carAccidentHandling.getId());
            if(!before.equalsAttributes(carAccidentHandling))
                if(!carAccidentHandlingDao.update(carAccidentHandling))
                    return false;
            ArrayList<AccidentCar> beforeAccidentCars = before.getAccidentCars();
            ArrayList<AccidentCar> tobeAccidentCars = carAccidentHandling.getAccidentCars();

            // accidentCar 가 모두 삭제되는 경우 = 전부 삭제
            if(tobeAccidentCars.size() < 1) { beforeAccidentCars.forEach(c -> accidentCarDao.delete(c.getId())); return true;}
            // 기존에 accidentCar 가 하나도 없는 경우 = 전부 추가
            if(beforeAccidentCars.size() < 1) {tobeAccidentCars.forEach(accidentCarDao::create); return true; }

            for (AccidentCar tobeAccidentCar : tobeAccidentCars) {
                System.out.println("tobe in");
                //추가
                if(tobeAccidentCar.getId() == null) accidentCarDao.create(tobeAccidentCar);
                for (AccidentCar beforeAccidentCar : beforeAccidentCars) {
                    System.out.println("before in");

                    //삭제
                    if(!tobeAccidentCars.contains(beforeAccidentCar)) {
                        System.out.println("delete in");
                        accidentCarDao.delete(beforeAccidentCar.getId());
                    }
                    // 내용 수정
                    if (tobeAccidentCar.equals(beforeAccidentCar) && !tobeAccidentCar.equalsAttributes(beforeAccidentCar)) accidentCarDao.update(tobeAccidentCar);
                }
            }

            ArrayList<AccidentPerson> beforeAccidentPeople = before.getAccidentPeople();
            ArrayList<AccidentPerson> tobeAccidentPeople = carAccidentHandling.getAccidentPeople();

            for (AccidentPerson tobeAccidentPerson : tobeAccidentPeople) {
                System.out.println("tobe in");
                //추가
                if(tobeAccidentPerson.getId() == null) accidentPersonDao.create(tobeAccidentPerson);
                for (AccidentPerson beforeAccidentPerson : beforeAccidentPeople) {
                    System.out.println("before in");

                    //삭제
                    if(!tobeAccidentCars.contains(beforeAccidentPerson)) {
                        System.out.println("delete in");
                        accidentPersonDao.delete(beforeAccidentPerson.getId());
                    }
                    // 내용 수정
                    if (tobeAccidentPerson.equals(beforeAccidentPerson) && !tobeAccidentPerson.equalsAttributes(beforeAccidentPerson)) accidentPersonDao.update(tobeAccidentPerson);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
