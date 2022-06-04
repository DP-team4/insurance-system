package repository;

import java.util.ArrayList;

import dao.CancelApplicationDao;
import dao.ContractDao;
import domain.cancelApplication.CancelApplication;
import domain.contract.Contract;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:25
 */
public class CancelApplicationRepository {
	private static final CancelApplicationRepository instance = new CancelApplicationRepository();
	private static final CancelApplicationDao cancelApplicationDao = new CancelApplicationDao();
	private static final ContractDao contractDao = new ContractDao();

	private CancelApplicationRepository(){ }
	public static CancelApplicationRepository getInstance() { return instance; }

	public boolean add(CancelApplication cancelApplication) {
		return cancelApplicationDao.create(cancelApplication);
	}
	
	public boolean update(CancelApplication cancelApplication) {
		return cancelApplicationDao.update(cancelApplication);
	}
	
	public boolean delete(String id) {
		return cancelApplicationDao.delete(id);
	}

	public ArrayList<CancelApplication> getAll() {
		 return cancelApplicationDao.retrieveAll();
	}

	public CancelApplication get(String id) {
		return cancelApplicationDao.retrieveById(id);
	}

	public ArrayList<CancelApplication> getByCustomerId(String customerId) {
		ArrayList<Contract> contracts = contractDao.retrieveByCustomerId(customerId);
		ArrayList<CancelApplication> cancelApplications = new ArrayList<>();
		for(Contract contract : contracts) {
			CancelApplication cancelApplication = cancelApplicationDao.retrieveByContractId(contract.getId());
			if(cancelApplication != null) cancelApplications.add(cancelApplication);
		}
		return cancelApplications;
	}
}//end CancelApplicationListImpl