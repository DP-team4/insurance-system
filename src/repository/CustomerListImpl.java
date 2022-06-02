package repository;

import java.util.ArrayList;

import dao.AdditionalInfoDao;
import dao.CustomerDao;
import domain.customer.Customer;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:26
 */
public class CustomerListImpl {
	private static final CustomerListImpl customerList = new CustomerListImpl();
	private static final CustomerDao customerDao = new CustomerDao();
	private static final AdditionalInfoDao additionalInfoDao = new AdditionalInfoDao();

	private CustomerListImpl(){}
	public static CustomerListImpl getInstance() { return customerList; }

	public boolean add(Customer customer) {
		String id = customerDao.createAndGetId(customer);
		if(id==null) return false;
		customer.setId(id);
		String additionalInfoId = additionalInfoDao.createAndGetId(customer);
		customer.getAdditionalInfo().setId(additionalInfoId);
		return true;
	}
	
	public void update(Customer customer){
		customerDao.update(customer);
	}

	public boolean delete(String id) {
		return customerDao.delete(id);
	}

	public ArrayList<Customer> getAll() {
		 return customerDao.retrieveAll();
	}

	public Customer get(String id) {
		return customerDao.retrieveById(id);
	}
	
	public Customer getByEmail(String email) {
		return customerDao.retrieveByEmail(email);
	}

	public ArrayList<Customer> getByName(String name) {
		return customerDao.retrieveByName(name);
	}
}//end CustomerListImpl 