package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.customer.AdditionalInfo;
import domain.customer.Customer;

public class AdditionalInfoDao extends Dao{
	public AdditionalInfoDao() {
		super.connect();
	}

	public String createAndGetId(Customer customer) {
    	AdditionalInfo additionalInfo = customer.getAdditionalInfo();
        String query = String.format(
                "insert into additional_info values (0, %s, %s, %s, %s, %s)",
                customer.getId(), additionalInfo.getCarPrice(), additionalInfo.getHousePrice(),
                additionalInfo.getDrivingCareer(), additionalInfo.getShipPrice()
        );
        return super.createAndGetId(query);
	}
	
    public boolean create(Customer customer) {
    	AdditionalInfo additionalInfo = customer.getAdditionalInfo();
        String query = String.format(
                "insert into additional_info values (0, %s, %s, %s, %s, %s)",
                customer.getId(), additionalInfo.getCarPrice(), additionalInfo.getHousePrice(),
                additionalInfo.getDrivingCareer(), additionalInfo.getShipPrice()
        );
        return super.create(query);
    }

    public boolean update(Customer customer) {
    	AdditionalInfo additionalInfo = customer.getAdditionalInfo();
        String query = String.format(
                "update additional_info set customer_id=%s, car_price=%s, house_price=%s, driving_career=%s, ship_price=%s where id=%s",
                customer.getId(), additionalInfo.getCarPrice(), additionalInfo.getHousePrice(),
                additionalInfo.getDrivingCareer(), additionalInfo.getShipPrice(), additionalInfo.getId()
        );
        return super.update(query);
    }

    public boolean deleteByCustomerId(String customerId) {
        String query = String.format("delete from additional_info where customer_id=%s", customerId);
        return super.delete(query);
    }

    public AdditionalInfo retrieveByCustomerId(String customerId) {
        try {
            String query = String.format("select * from additional_info where customer_id=%s", customerId);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null || !resultSet.next()) return null;
            AdditionalInfo additionalInfo = getCurrentAdditionalInfo(resultSet);
            return additionalInfo;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private AdditionalInfo getCurrentAdditionalInfo(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        int carPrice = resultSet.getInt("car_price");
        int housePrice = resultSet.getInt("house_price");
        int drivingCareer = resultSet.getInt("driving_career");
        int shipPrice = resultSet.getInt("ship_price");
        AdditionalInfo additionalInfo = new AdditionalInfo();
        additionalInfo.setId(id);
        additionalInfo.setCarPrice(carPrice);
        additionalInfo.setHousePrice(housePrice);
        additionalInfo.setDrivingCareer(drivingCareer);
        additionalInfo.setShipPrice(shipPrice);
        return additionalInfo;
    }

}
