package service.customer;

import java.util.ArrayList;

import domain.cancelApplication.CancelApplication;
import domain.contract.Contract;
import repository.cancelApplication.CancelApplicationRepository;
import repository.contract.ContractRepository;

public class CustomerCancelApplicationServiceImpl implements CustomerCancelApplicationService {
	private final static CustomerCancelApplicationService instance = new CustomerCancelApplicationServiceImpl();
    private static final CancelApplicationRepository cancelApplicationRepository = CancelApplicationRepository.getInstance();
    private static final ContractRepository contractRepository = ContractRepository.getInstance();

	// Singleton
	private CustomerCancelApplicationServiceImpl(){}
	public static CustomerCancelApplicationService getInstance() { return instance; }
	
	@Override
	public boolean applyCancellation(CancelApplication cancelApplication) {
		if(contractRepository.get(cancelApplication.getContractId()) == null) return false;
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
		Contract contract = contractRepository.get(cancelApplication.getContractId());
		if(contract == null) return false;
		else if(contract.getCustomerId().equals(customerId)) return cancelApplicationRepository.delete(id);
		return false;
	}
}
