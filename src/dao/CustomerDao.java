package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.customer.Customer;

public class CustomerDao extends Dao {
	
	public CustomerDao() {
		super.connect();
	}

    public String createAndGetId(Customer customer) {
    	String query = String.format(
                "insert into customer values (0, '%s', '%s', '%s', %s, %s, '%s', '%s', '%s', %s)",
                customer.getEmail(), customer.getPassword(), customer.getName()
                , customer.getAge(), customer.getGender() ? 1 : 0, customer.getRegistrationNo()
                , customer.getPhoneNo(), customer.getAccountNo(), customer.isMarried() ? 1 : 0
        );
        return super.createAndGetId(query);
    }
	
    public boolean create(Customer customer) {
        String query = String.format(
                "insert into customer values (0, '%s', '%s', '%s', %s, %s, '%s', '%s', '%s', %s)",
                customer.getEmail(), customer.getPassword(), customer.getName()
                , customer.getAge(), customer.getGender() ? 1 : 0, customer.getRegistrationNo()
                , customer.getPhoneNo(), customer.getAccountNo(), customer.isMarried() ? 1 : 0
        );
        return super.create(query);
    }

    public boolean update(Customer customer) {
        String query = String.format(
                "update customer set email='%s', password='%s', name='%s', age='%s', gender=%s, registration_no='%s', phone_no='%s', account_no='%s', is_married=%s where id=%s",
                customer.getEmail(), customer.getPassword(), customer.getName()
                , customer.getAge(), customer.getGender(), customer.getRegistrationNo()
                , customer.getPhoneNo(), customer.getAccountNo(), customer.isMarried(), customer.getId()
        );
        return super.update(query);
    }

    public boolean delete(String id) {
        String query = String.format("delete from customer where id=%s", id);
        return super.delete(query);
    }

    public ArrayList<Customer> retrieveAll() {
        try {
            String query = "select * from customer";
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null) return null;
            ArrayList<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
            	Customer customer = getCurrentCustomer(resultSet);
            	customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Customer retrieveById(String id) {
        try {
            String query = String.format("select * from customer where id=%s", id);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null || !resultSet.next()) return null;
            Customer customer = getCurrentCustomer(resultSet);
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

	public Customer retrieveByEmail(String email) {
        try {
            String query = String.format("select * from customer where email='%s'", email);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null || !resultSet.next()) return null;
            Customer customer = getCurrentCustomer(resultSet);
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}

	public Customer retrieveByRegistrationNo(String registrationNo) {
        try {
            String query = String.format("select * from customer where registration_no='%s'", registrationNo);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null || !resultSet.next()) return null;
            Customer customer = getCurrentCustomer(resultSet);
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}

	public Customer retrieveByPhoneNo(String phoneNo) {
        try {
            String query = String.format("select * from customer where phone_no='%s'", phoneNo);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null || !resultSet.next()) return null;
            Customer customer = getCurrentCustomer(resultSet);
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}

    public ArrayList<Customer> retrieveByName(String name) {
        try {
            String query = String.format("select * from customer where name='%s'", name);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null) return null;
            ArrayList<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
            	Customer customer = getCurrentCustomer(resultSet);
            	customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Customer getCurrentCustomer(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        boolean gender = resultSet.getBoolean("gender");
        String registration_no = resultSet.getString("registration_no");
        String phone_no = resultSet.getString("phone_no");
        String account_no = resultSet.getString("account_no");
        boolean is_married = resultSet.getBoolean("is_married");
        Customer customer = new Customer();
        customer.setId(id);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setName(name);
        customer.setAge(age);
        customer.setGender(gender);
        customer.setRegistrationNo(registration_no);
        customer.setPhoneNo(phone_no);
        customer.setAccountNo(account_no);
        customer.setMarried(is_married);
        return customer;
    }
}
