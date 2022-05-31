package repository.customer;

import java.util.ArrayList;

import domain.customer.Customer;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:26
 */
public interface CustomerList {
	boolean add(Customer customer);
	boolean delete(String id);
	Customer get(String id);
//	void update();
	ArrayList<Customer> getAll();
}