package repository.customer;

import java.util.ArrayList;

import domain.Customer.Customer;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:26
 */
public class CustomerListImpl implements CustomerList {
	private static final ArrayList<Customer> customers = new ArrayList<>();
	private static final CustomerListImpl customerList = new CustomerListImpl();

	private CustomerListImpl(){}
	public static CustomerListImpl getInstance() { return customerList; }

	@Override
	public boolean add(Customer customer) {
		return customers.add(customer);
	}

	@Override
	public boolean delete(String customerID) {
		Customer customer = this.get(customerID);
		return customers.remove(customer);
	}

	@Override
	public Customer get(String customerID) {
		for(Customer e : customers) {
			if(e.getId().equals(customerID))	return e;
		}
		return null;
	}

	@Override
	public ArrayList<Customer> getAll() {
		 return customers;
	}
	
	public void printAll() {
		customers.forEach(i -> {
			System.out.println(i);
			System.out.println();
		});
	}
//	public void update(){
//
//	}
}//end CustomerListImpl