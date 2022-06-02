package service.customerInfo;

import domain.customer.Customer;
import repository.CustomerListImpl;

public class SignUpServiceImpl implements SignUpService {
	private final static SignUpService signUpService = new SignUpServiceImpl();
    private static final CustomerListImpl customerRepository = CustomerListImpl.getInstance();

	// Singleton
	private SignUpServiceImpl(){}
	public static SignUpService getInstance() { return signUpService; }

	@Override
	public boolean signUp(Customer customer) {
		return (customerRepository.add(customer));
	}

}
