package dao;

import domain.carAccidentHandling.AccidentCar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccidentCarDao extends Dao{
    //desc accident_car
    private final String tableName = "accident_car";

    public AccidentCarDao() {
        super.connect();
    }

    public boolean create(AccidentCar accidentCar){
        String query = this.makeCreationQuery(accidentCar);
        
        return super.create(query);
    }

    public String createAndGetId(AccidentCar accidentCar){
        String query = this.makeCreationQuery(accidentCar);
        
        return super.createAndGetId(query);
    }

    private String makeCreationQuery(AccidentCar accidentCar){
        String query = String.format(
            "insert into %s values (" +
                    "0, '%d', " +
                    "'%s', '%d', '%s', '%s', '%s')",
            this.tableName,  Integer.parseInt(accidentCar.getCarAccidentHandlingId()),
            accidentCar.getCarNo(), accidentCar.getCost(), accidentCar.getOwnerName(),  accidentCar.getOwnerPhoneNo(), accidentCar.getVisitedShopName()
    );
        return query;
    }

    public boolean update(AccidentCar accidentCar) {
        String query = String.format(
                "update %s set " +
                        "car_no=%s, cost=%d, owner_name=%s, owner_phone_no=%s, visited_shop_name=%s",
                this.tableName,
                accidentCar.getCarNo(), accidentCar.getOwnerName(), accidentCar.getOwnerPhoneNo(), accidentCar.getVisitedShopName()
        );
        
        return super.create(query);
    }


    public boolean delete(String id, String carAccidentHandlingId) {
        String query = String.format("delete from %s where id=%s and car_accident_handling_id=%s ", this.tableName, id, carAccidentHandlingId);
        
        return super.delete(query);
    }

    public boolean deleteByCarAccidentHandlingId(String id) {
        String query = String.format("delete from %s where car_accident_handling_id=%s", this.tableName, id);
        return super.delete(query);
    }

    public ArrayList<AccidentCar> retrieveAll() {
        try{
            String query = "select * from "+this.tableName;
             //나중에 없앨 것
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null) return null;
            ArrayList<AccidentCar> accidentCars = new ArrayList<>();
            while(resultSet.next()){
				AccidentCar accidentCar = getCurrentRecord(resultSet);
				accidentCars.add(accidentCar);
            }
            return accidentCars;
        }catch (SQLException e) {
//			e.printStackTrace();
            return null;
        }
    }

    public AccidentCar retrieveById(String id, String carAccidentHandlingId){
        try{
            String query = String.format("select * from "+this.tableName+" where id=%s and car_accident_handling_id=%s", id, carAccidentHandlingId);
            
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null || !resultSet.next()) return null;
            AccidentCar accidentCar = this.getCurrentRecord(resultSet);
            return accidentCar;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<AccidentCar> retrieveByCarAccidentHandlingId(String carAccidentHandlingId){
        try {
            String query = "select * from accident_car where car_accident_handling_id=" + carAccidentHandlingId;
            ResultSet resultSet = super.retrieve(query);
            if (resultSet == null) return null;
            ArrayList<AccidentCar> accidentCars = new ArrayList<>();
            while (resultSet.next()) {
                AccidentCar accidentCar = getCurrentRecord(resultSet);
                accidentCars.add(accidentCar);
            }
            return accidentCars;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private AccidentCar getCurrentRecord(ResultSet resultSet) throws SQLException { //내부에서 처리되므로 상관X
        String id = resultSet.getString("id");
        String carAccidentHandlingId = resultSet.getString("car_accident_handling_id");
        String carNo = resultSet.getString("car_no");
        long cost = Long.parseLong(resultSet.getString("cost"));
        String ownerName = resultSet.getString("owner_name");
        String ownerPhoneNo = resultSet.getString("owner_phone_no");
        String visitedShopName = resultSet.getString("visited_shop_name");

        AccidentCar accidentCar = new AccidentCar();
        accidentCar.setId(id);
        accidentCar.setCarAccidentHandlingId(carAccidentHandlingId);
        accidentCar.setCarNo(carNo);
        accidentCar.setCost(cost);
        accidentCar.setOwnerName(ownerName);
        accidentCar.setOwnerPhoneNo(ownerPhoneNo);
        accidentCar.setVisitedShopName(visitedShopName);
        return accidentCar;
    }
}
