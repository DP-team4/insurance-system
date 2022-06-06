package service.contractManagement;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import dao.CancelApplicationDao;
import dao.ContractDao;
import dao.InsuranceDao;
import domain.cancelApplication.CancelApplication;
import domain.cancelApplication.CancelApplicationState;
import domain.consultApplication.ConsultApplication;
import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Insurance;
import repository.CustomerRepository;
import repository.cancelApplication.CancelApplicationRepository;
import repository.contract.ContractRepository;
import repository.insurance.InsuranceRepository;

public class ContractManagementServiceImpl implements ContractManagementService{
	
	 private static final ContractManagementService instance = new ContractManagementServiceImpl();
	 private static final ContractRepository contractRepository = ContractRepository.getInstance();
	 private static final CancelApplicationRepository cancelApplicationRepository = CancelApplicationRepository.getInstance();
	 
	 private ContractManagementServiceImpl() {}
	 public static ContractManagementService getInstance() {
			return instance;
		}
	 //계약 목록 요청
	 public ArrayList<Contract> getAllContracts() {
		 ArrayList<Contract> contracts = contractRepository.getAll();
	     return contracts;
	 }
	
	 // 만기대상계약 목록 요청
	 public ArrayList<Contract> getAllMatureContracts() {
		 ArrayList<Contract> matureContracts = new ArrayList<>();
		 ArrayList<Contract> contracts = contractRepository.getAll();
		 for (Contract contract: contracts) { 
			 long difference = ChronoUnit.MONTHS.between(LocalDateTime.now(), contract.getExpirationDateTime());
			 if (difference <= 1) {
				 matureContracts.add(contract);
			 }
		 }
		 return matureContracts;
	 }
	 
	 // 계약 삭제 요청
	 public boolean deleteContract(String id) {
		 return contractRepository.delete(id);
	 }
	 
	 // 계약 해지 신청 목록 요청
	 public ArrayList<CancelApplication> getAllCancelApplications(){
		 return cancelApplicationRepository.getAll();
	 }
	@Override
	public Contract getContract(String id) {
		return contractRepository.get(id);
	}
	@Override
	public Insurance getInsurance(String id) {
		InsuranceRepository insuranceRepository = InsuranceRepository.getInstance(); 
		return insuranceRepository.get(id);
	}
	@Override
	public Customer getCustomer(String id) {
		CustomerRepository customerRepository = CustomerRepository.getInstance(); 
		return customerRepository.get(id);
	}
	@Override
	public CancelApplication getCancelApplication(String id) {
		return cancelApplicationRepository.get(id);
	}
	@Override
	public boolean updateCancelApplication(CancelApplication cancelApplication) {
		return cancelApplicationRepository.update(cancelApplication);
	}
	 
}
