package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import domain.consultApplication.ConsultApplication;
import domain.consultApplication.ConsultApplicationState;

public class ConsultApplicationDao extends Dao {
	
	public ConsultApplicationDao() {
		super.connect();
	}
	
    public boolean create(ConsultApplication consultApplication) {
        String query = String.format(
                "insert into consult_application values (0, '%s', '%s', '%s', '%s', '%s')",
                consultApplication.getCustomerId(), consultApplication.getContent(), Timestamp.valueOf(consultApplication.getApplicationDate())
                , Timestamp.valueOf(consultApplication.getConsultationDate()), consultApplication.getState().name()
        );
        System.out.println("Query >> " + query);
        return super.create(query);
    }
	
    public boolean update(ConsultApplication consultApplication) {
        String query = String.format(
                "update consult_application set customer_id=%s, content=%s, application_date=%s, consultation_date=%s, state=%s where id=%s",
                consultApplication.getCustomerId(), consultApplication.getContent(), Timestamp.valueOf(consultApplication.getApplicationDate())
                , Timestamp.valueOf(consultApplication.getConsultationDate()), consultApplication.getState().name(), consultApplication.getId()
        );
        System.out.println("Query >> " + query);
        return super.update(query);
    }

    public boolean delete(String id) {
        String query = String.format("delete from consult_application where id=%s", id);
        System.out.println("Query >> " + query);
        return super.delete(query);
    }

    public ArrayList<ConsultApplication> retrieveAll() {
        try {
            String query = "select * from consult_application";
            System.out.println("Query >> " + query);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null) return null;
            ArrayList<ConsultApplication> consultApplications = new ArrayList<>();
            while (resultSet.next()) {
            	ConsultApplication consultApplication = getCurrentConsultApplication(resultSet);
            	consultApplications.add(consultApplication);
            }
            return consultApplications;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ConsultApplication retrieveById(String id) {
        try {
            String query = String.format("select * from consult_application where id=%s", id);
            System.out.println("Query >> " + query);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null || !resultSet.next()) return null;
            ConsultApplication consultApplication = getCurrentConsultApplication(resultSet);
            return consultApplication;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ConsultApplication> retrieveByCustomerId(String customerId) {
        try {
            String query = String.format("select * from consult_application where customer_id='%s'", customerId);
            System.out.println("Query >> " + query);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null) return null;
            ArrayList<ConsultApplication> consultApplications = new ArrayList<>();
            while (resultSet.next()) {
            	ConsultApplication consultApplication = getCurrentConsultApplication(resultSet);
            	consultApplications.add(consultApplication);
            }
            return consultApplications;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
	
    private ConsultApplication getCurrentConsultApplication(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String customerId = resultSet.getString("customer_id");
        String content = resultSet.getString("content");
        LocalDateTime applicationDate = resultSet.getTimestamp("application_date").toLocalDateTime();
        LocalDateTime consultationDate = resultSet.getTimestamp("consultation_date").toLocalDateTime();
        ConsultApplicationState state = ConsultApplicationState.valueOf(resultSet.getString("state"));
        
        ConsultApplication consultApplication = new ConsultApplication();
        consultApplication.setId(id);
        consultApplication.setCustomerId(customerId);
        consultApplication.setContent(content);
        consultApplication.setApplicationDate(applicationDate);
        consultApplication.setConsultationDate(consultationDate);
        consultApplication.setState(state);
        return consultApplication;
    }

}
