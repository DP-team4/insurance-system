package service.customerInfo;

import domain.customer.Customer;
import repository.CustomerRepository;

public class SignUpServiceImpl implements SignUpService {
	private final static SignUpService signUpService = new SignUpServiceImpl();
    private static final CustomerRepository customerRepository = CustomerRepository.getInstance();

	// Singleton
	private SignUpServiceImpl(){}
	public static SignUpService getInstance() { return signUpService; }

	@Override
	public boolean signUp(Customer customer) {
		return customerRepository.add(customer);
	}
	
	@Override
	public boolean isEmailDuplicated(String email) {
		return (customerRepository.getByEmail(email) != null);
	}
	
	@Override
	public boolean isRegistrationNoDuplicated(String registrationNo) {
		return (customerRepository.getByRegistrationNo(registrationNo) != null);
	}
	
	@Override
	public boolean isPhoneNoDuplicated(String phoneNo) {
		return (customerRepository.getByPhoneNo(phoneNo) != null);
	}
}
