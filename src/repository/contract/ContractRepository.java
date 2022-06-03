package repository.contract;

import java.util.ArrayList;

import dao.ContractDao;
import domain.contract.Contract;

public class ContractRepository {
	private static final ContractRepository instance = new ContractRepository();
	private static final ContractDao contractDao = new ContractDao();
	
	private ContractRepository() {}
	public static ContractRepository getInstance() { return instance; }
	
	public boolean add(Contract contract) {
		return contractDao.create(contract);
	}
	
	public boolean update(Contract contract) {
		return contractDao.update(contract);
	}
	
	public boolean delete(String id) {
		return contractDao.delete(id);
	}

	public ArrayList<Contract> getAll() {
		 return contractDao.retrieveAll();
	}

	public Contract get(String id) {
		return contractDao.retrieveById(id);
	}

	public ArrayList<Contract> getByCustomerId(String customerId) {
		return contractDao.retrieveByCustomerId(customerId);
	}
}
