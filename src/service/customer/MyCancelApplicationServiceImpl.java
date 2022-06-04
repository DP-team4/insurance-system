package service.customer;

import java.util.ArrayList;

import dao.ContractDao;
import domain.cancelApplication.CancelApplication;
import domain.contract.Contract;
import repository.cancelApplication.CancelApplicationRepository;

public class MyCancelApplicationServiceImpl implements MyCancelApplicationService {
	private final static MyCancelApplicationService instance = new MyCancelApplicationServiceImpl();
    private static final CancelApplicationRepository cancelApplicationRepository = CancelApplicationRepository.getInstance();
    private static final ContractDao contractDao = new ContractDao(); // Repository

	// Singleton
	private MyCancelApplicationServiceImpl(){}
	public static MyCancelApplicationService getInstance() { return instance; }
	
	@Override
	public boolean applyCancellation(CancelApplication cancelApplication) {
		if(contractDao.retrieveById(cancelApplication.getContractId()) == null) return false;
		return (cancelApplicationRepository.add(cancelApplication));
	}
	@Override
	public ArrayList<CancelApplication> getByCustomerId(String customerId) {
		return cancelApplicationRepository.getByCustomerId(customerId);
	}
	@Override
	public boolean deleteMyCancellation(String id, String customerId) {
		CancelApplication cancelApplication = cancelApplicationRepository.get(id);
		if(cancelApplication == null) return false;
		Contract contract = contractDao.retrieveById(cancelApplication.getContractId());
		if(contract == null) return false;
		else if(contract.getCustomerId().equals(customerId)) return cancelApplicationRepository.delete(id);
		return false;
	}
}
