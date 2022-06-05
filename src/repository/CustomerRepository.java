package repository;

import java.util.ArrayList;

import dao.AdditionalInfoDao;
import dao.CustomerDao;
import domain.customer.AdditionalInfo;
import domain.customer.Customer;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:26
 */
public class CustomerRepository {
	private static final CustomerRepository instance = new CustomerRepository();
	private static final CustomerDao customerDao = new CustomerDao();
	private static final AdditionalInfoDao additionalInfoDao = new AdditionalInfoDao();

	private CustomerRepository(){}
	public static CustomerRepository getInstance() { return instance; }

	public boolean add(Customer customer) {
		String id = customerDao.createAndGetId(customer);
		if(id == null) return false;
		customer.setId(id);
		String additionalInfoId = additionalInfoDao.createAndGetId(customer);
		if(additionalInfoId == null) return false;
		customer.getAdditionalInfo().setId(additionalInfoId);
		return true;
	}
	
	public boolean update(Customer customer){
		return customerDao.update(customer) && additionalInfoDao.update(customer);
	}

	public boolean delete(String id) {
		if(!additionalInfoDao.deleteByCustomerId(id)) return false;
		return customerDao.delete(id);
	}

	public ArrayList<Customer> getAll() {
		ArrayList<Customer> customers = customerDao.retrieveAll();
		for (Customer customer : customers) {
			AdditionalInfo additionalInfo = additionalInfoDao.retrieveByCustomerId(customer.getId());
			customer.setAdditionalInfo(additionalInfo);
		}
		return customers;
	}

	public Customer get(String id) {
		Customer customer = customerDao.retrieveById(id);
		if(customer == null) return null;
		AdditionalInfo additionalInfo = additionalInfoDao.retrieveByCustomerId(customer.getId());
		customer.setAdditionalInfo(additionalInfo);
		return customer;
	}
	
	public Customer getByEmail(String email) {
		Customer customer = customerDao.retrieveByEmail(email);
		if(customer == null) return null;
		AdditionalInfo additionalInfo = additionalInfoDao.retrieveByCustomerId(customer.getId());
		customer.setAdditionalInfo(additionalInfo);
		return customer;
	}
	
	public Customer getByRegistrationNo(String registrationNo) {
		Customer customer = customerDao.retrieveByRegistrationNo(registrationNo);
		if(customer == null) return null;
		AdditionalInfo additionalInfo = additionalInfoDao.retrieveByCustomerId(customer.getId());
		customer.setAdditionalInfo(additionalInfo);
		return customer;
	}
	
	public Customer getByPhoneNo(String phoneNo) {
		Customer customer = customerDao.retrieveByPhoneNo(phoneNo);
		if(customer == null) return null;
		AdditionalInfo additionalInfo = additionalInfoDao.retrieveByCustomerId(customer.getId());
		customer.setAdditionalInfo(additionalInfo);
		return customer;
	}

	public ArrayList<Customer> getByName(String name) {
		ArrayList<Customer> customers = customerDao.retrieveByName(name);
		for (Customer customer : customers) {
			AdditionalInfo additionalInfo = additionalInfoDao.retrieveByCustomerId(customer.getId());
			customer.setAdditionalInfo(additionalInfo);
		}
		return customers;
	}
}//end CustomerListImpl 