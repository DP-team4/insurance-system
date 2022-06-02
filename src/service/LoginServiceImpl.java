package service;

import dao.CustomerDao;
import domain.customer.Customer;

public class LoginServiceImpl implements LoginService {
    private final CustomerDao customerDao = new CustomerDao();
	private final static LoginServiceImpl loginService = new LoginServiceImpl();

	// Singleton
	private LoginServiceImpl(){}
	public static LoginServiceImpl getInstance() { return loginService; }

	public Customer login(String email, String password) {
		Customer customer = customerDao.retrieveByEmail(email);
		if(customer == null) return null;
		else if(password.equals(customer.getPassword())) return customer;
		else return null;
	}
}
