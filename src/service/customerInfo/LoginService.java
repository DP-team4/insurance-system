package service.customerInfo;

import domain.customer.Customer;

public interface LoginService {
	Customer login(String email, String password);
}
