package repository.customer;

import java.util.ArrayList;

import domain.Customer.Customer;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:26
 */
public interface CustomerList {
	boolean add(Customer customer);
	boolean delete(String customerID);
	Customer get(String customerID);
//	void update();
	ArrayList<Customer> getAll();
}