package dao;

import domain.insurance.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsuranceDao extends Dao {

    public InsuranceDao() {
        super.connect();
    }

    public String createAndGetId(Insurance insurance) {
        String query = String.format(
                "insert into insurance values (0, '%s', '%s', '%s')",
                insurance.getName(), insurance.getInsuranceCategory().name(), insurance.getInsuranceState().name()
        );
        return super.createAndGetId(query);
    }

    public boolean create(Insurance insurance) {
        String query = String.format(
                "insert into insurance values (0, '%s', '%s', '%s')",
                insurance.getName(), insurance.getInsuranceCategory().name(), insurance.getInsuranceState().name()
        );
        return super.create(query);
    }

    public boolean update(Insurance insurance) {
        String query = String.format(
                "update insurance set name='%s', insurance_category='%s', insurance_state='%s' where id=%s",
                insurance.getName(), insurance.getInsuranceCategory().name(), insurance.getInsuranceState().name(), insurance.getId()
        );
        return super.update(query);
    }

    public boolean delete(String id) {
        String query = String.format("delete from insurance where id=%s", id);
        return super.delete(query);
    }

    public ArrayList<Insurance> retrieveAll() {
        try {
            String query = "select * from insurance";
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null) return null;
            ArrayList<Insurance> insurances = new ArrayList<>();
            while (resultSet.next()) {
                Insurance insurance = getCurrentInsurance(resultSet);
                insurances.add(insurance);
            }
            return insurances;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Insurance retrieveById(String id) {
        try {
            String query = String.format("select * from insurance where id=%s", id);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null || !resultSet.next()) return null;
            Insurance insurance = getCurrentInsurance(resultSet);
            return insurance;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Insurance retrieveByName(String name) {
        try {
            String query = String.format("select * from insurance where name='%s'", name);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null || !resultSet.next()) return null;
            Insurance insurance = getCurrentInsurance(resultSet);
            return insurance;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Insurance getCurrentInsurance(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        InsuranceCategory insuranceCategory = InsuranceCategory.valueOf(resultSet.getString("insurance_category"));
        InsuranceState insuranceState = InsuranceState.valueOf(resultSet.getString("insurance_state"));
        Insurance insurance = null;
        switch (insuranceCategory) {
            case FIRE: insurance = new FireInsurance(); break;
            case MARINE: insurance = new MarineInsurance(); break;
            case CAR: insurance = new CarInsurance(); break;
            case DRIVER: insurance = new DriverInsurance(); break;
        }
        insurance.setId(id);
        insurance.setName(name);
        insurance.setInsuranceCategory(insuranceCategory);
        insurance.setInsuranceState(insuranceState);
        return insurance;
    }
}
