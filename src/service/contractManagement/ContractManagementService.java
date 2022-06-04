package service.contractManagement;

import java.util.ArrayList;

import domain.cancelApplication.CancelApplication;
import domain.consultApplication.ConsultApplication;
import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Insurance;

public interface ContractManagementService {
	ArrayList<Contract> getAllContracts();
	public ArrayList<Contract> getAllMatureContracts();
	public boolean deleteContract(String id);
	public ArrayList<CancelApplication> getAllCancelApplications();
	Contract getContract(String id);
	Insurance getInsurance(String insuranceId);
	Customer getCustomer(String customerId);
	CancelApplication getCancelApplication(String id);
	boolean updateCancelApplication(CancelApplication cancelApplication);
}
