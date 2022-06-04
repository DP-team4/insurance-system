package service.customerInfo;

import domain.customer.Customer;

public interface SignUpService {
	boolean signUp(Customer customer);
	boolean isEmailDuplicated(String email);
	boolean isRegistrationNoDuplicated(String registrationNo);
	boolean isPhoneNoDuplicated(String phoneNo);
}
