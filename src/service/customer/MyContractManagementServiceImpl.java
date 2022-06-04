package service.customer;

import java.util.ArrayList;

import dao.ContractDao;
import domain.cancelApplication.CancelApplication;
import domain.contract.Contract;

public class MyContractManagementServiceImpl implements MyContractManagementService {
	private final static MyContractManagementService myContractManagementService = new MyContractManagementServiceImpl();
    private static final ContractDao contractDao = new ContractDao(); // Repository

	// Singleton
	private MyContractManagementServiceImpl(){}
	public static MyContractManagementService getInstance() { return myContractManagementService; }
	
	@Override
	public ArrayList<Contract> getCustomerContracts(String customerId) {
		return contractDao.retrieveByCustomerId(customerId);
	}

	@Override
	public boolean applyCancelApplication(CancelApplication cancelApplication) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMonthlyPremium() {
		// TODO Auto-generated method stub
		return 0;
	}

}
