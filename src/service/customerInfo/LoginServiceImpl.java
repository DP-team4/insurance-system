package service.customerInfo;

import domain.customer.Customer;
import repository.CustomerRepository;

public class LoginServiceImpl implements LoginService {
	private final static LoginService loginService = new LoginServiceImpl();
    private static final CustomerRepository customerRepository = CustomerRepository.getInstance();

	// Singleton
	private LoginServiceImpl(){}
	public static LoginService getInstance() { return loginService; }

	@Override
	public Customer login(String email, String password) {
		Customer customer = customerRepository.getByEmail(email);
		if(customer == null) return null;
		else if(password.equals(customer.getPassword())) return customer;
		else return null;
	}
}
