package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import domain.cancelApplication.CancelApplication;
import domain.cancelApplication.CancelApplicationState;

public class CancelApplicationDao extends Dao {
	
	public CancelApplicationDao() {
		super.connect();
	}
	
	public boolean create(CancelApplication cancelApplication) {
        String query = String.format(
                "insert into cancel_application values (0, '%s', '%s', '%s')",
                cancelApplication.getContractId(), Timestamp.valueOf(cancelApplication.getApplicationDate())
                , cancelApplication.getState().name()
        );
        System.out.println("Query >> " + query);
        return super.create(query);
    }
	
    public boolean update(CancelApplication cancelApplication) {
        String query = String.format(
                "update cancel_application set contract_id=%s, application_date=%s, state=%s where id=%s",
                cancelApplication.getContractId(), Timestamp.valueOf(cancelApplication.getApplicationDate())
                , cancelApplication.getState().name(), cancelApplication.getId()
        );
        System.out.println("Query >> " + query);
        return super.update(query);
    }

    public boolean delete(String id) {
        String query = String.format("delete from cancel_application where id=%s", id);
        System.out.println("Query >> " + query);
        return super.delete(query);
    }

    public ArrayList<CancelApplication> retrieveAll() {
        try {
            String query = "select * from cancel_application";
            System.out.println("Query >> " + query);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null) return null;
            ArrayList<CancelApplication> cancelApplications = new ArrayList<>();
            while (resultSet.next()) {
            	CancelApplication cancelApplication = getCurrentCancelApplication(resultSet);
            	cancelApplications.add(cancelApplication);
            }
            return cancelApplications;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public CancelApplication retrieveById(String id) {
        try {
            String query = String.format("select * from cancel_application where id=%s", id);
            System.out.println("Query >> " + query);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null || !resultSet.next()) return null;
            CancelApplication cancelApplication = getCurrentCancelApplication(resultSet);
            return cancelApplication;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<CancelApplication> retrieveByCustomerId(String customerId) {
        try {
            String query = String.format("select * from cancel_application where customer_id='%s'", customerId);
            System.out.println("Query >> " + query);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null) return null;
            ArrayList<CancelApplication> cancelApplications = new ArrayList<>();
            while (resultSet.next()) {
            	CancelApplication cancelApplication = getCurrentCancelApplication(resultSet);
            	cancelApplications.add(cancelApplication);
            }
            return cancelApplications;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
	
    private CancelApplication getCurrentCancelApplication(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String contractId = resultSet.getString("contract_id");
        LocalDateTime applicationDate = resultSet.getTimestamp("application_date").toLocalDateTime();
        CancelApplicationState state = CancelApplicationState.valueOf(resultSet.getString("state"));
        
        CancelApplication cancelApplication = new CancelApplication();
        cancelApplication.setId(id);
        cancelApplication.setContractId(contractId);
        cancelApplication.setApplicationDate(applicationDate);
        cancelApplication.setState(state);
        return cancelApplication;
    }

}
