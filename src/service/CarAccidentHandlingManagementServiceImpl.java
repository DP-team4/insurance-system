package service;

import domain.carAccidentHandling.CarAccidentHandling;
import repository.carAccidentHandling.CarAccidentHandlingRepository;

import java.util.ArrayList;

public class CarAccidentHandlingManagementServiceImpl implements CarAccidentHandlingManagementService {
    private static final CarAccidentHandlingManagementService instance = new CarAccidentHandlingManagementServiceImpl();
    private final CarAccidentHandlingRepository carAccidentHandlingRepository = CarAccidentHandlingRepository.getInstance();

    //singleton
    private CarAccidentHandlingManagementServiceImpl(){}
    public static CarAccidentHandlingManagementService getInstance() {
        return instance;
    }

    @Override
    public ArrayList<CarAccidentHandling> getAll() {
        return carAccidentHandlingRepository.getAll();
    }
    @Override
    public CarAccidentHandling getById(String id) {
        return carAccidentHandlingRepository.getById(id);
    }
    @Override
    public boolean createCarAccidentHandling(CarAccidentHandling carAccidentHandling) {
        return carAccidentHandlingRepository.create(carAccidentHandling);
    }
    @Override
    public boolean updateCarAccidentHandling(CarAccidentHandling carAccidentHandling) {
        return carAccidentHandlingRepository.update(carAccidentHandling);
    }
    @Override
    public boolean deleteCarAccidentHandling(String id) {
        return carAccidentHandlingRepository.delete(id);
    }
}
