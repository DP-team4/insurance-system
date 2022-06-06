package service;
import java.util.ArrayList;
import domain.consultApplication.ConsultApplication;
import domain.contract.Contract;
import domain.contract.ContractState;
import domain.customer.Customer;
import domain.insurance.Clause;
import domain.insurance.Insurance;
import domain.uw.UW;
import domain.uw.UWDocument;
import domain.uw.UWState;
import repository.CustomerRepository;
import repository.consultApplication.ConsultApplicationRepository;
import repository.contract.ContractRepository;
import repository.insurance.InsuranceRepository;
import repository.uw.UWRepository;

public class SalesServiceImpl implements SalesService{
	 private static final SalesService instance = new SalesServiceImpl();
	 private static final ContractRepository contractRepository = ContractRepository.getInstance();
	 private static final ConsultApplicationRepository consultApplicationRepository = ConsultApplicationRepository.getInstance();
	 private static final CustomerRepository customerRepository = CustomerRepository.getInstance();
	 private static final UWRepository uwRepository = UWRepository.getInstance();
	 private static final InsuranceRepository insuranceRepository = InsuranceRepository.getInstance();
	 
	 private SalesServiceImpl() {}
	 public static SalesService getInstance() {return instance;}
	 
	 // 계약 목록 요청
	 @Override
	 public ArrayList<Contract> getAllContract() {
		 return contractRepository.getAll();
	 }
	 // 보험 정보 요청
	 @Override
	 public Insurance getInsuranceByName(String name) {
		return insuranceRepository.getByName(name);
	 }
	 @Override
	public Insurance getInsurance(String id) {
		return insuranceRepository.get(id);
	}
	 @Override
	 public boolean requestUW(Contract contract, ArrayList<UWDocument> documents) {
		 contract.setState(ContractState.UNDER_UW);
		 String contractId = this.addContractAndGetId(contract);
		 
		 UW uw = new UW();
		 uw.setDocuments(documents);
		 uw.setUwState(UWState.UNDER_AUDIT);
		 uw.setContractId(contractId);
		 return uwRepository.add(uw);
	 }
	 
	 // 가입상담신청 목록 요청
	 @Override
	 public ArrayList<ConsultApplication> getAllConsultApplication() {
		 return consultApplicationRepository.getAll();
	 }
	 // 가입상담신청 수정
	 @Override
	 public boolean updateConsultApplication(ConsultApplication consultApplication) {
		 return consultApplicationRepository.update(consultApplication);
	 }
	 // 계약 수정
	 @Override
	 public boolean updateContract(Contract contract) {
		 return contractRepository.update(contract);
	 }
	
	 // 고객 찾기
	 @Override
	 public Customer getCustomerByEmail(String email) {
		return customerRepository.getByEmail(email);
	 }
	 @Override
	 public Customer getCustomer(String id) {
		 return customerRepository.get(id);
	 }
	 
	 // 보상금액
	 @Override
	 public long getInsuredAmount(Insurance insurance, Customer customer) {
		 ArrayList<Clause> clauses = insurance.getClauses();
		 long insuredAmount = 0;
		 for(Clause clause: clauses) {
			 insuredAmount += clause.getInsuredAmount();
		 }
		 return insuredAmount;
	 }
	 // 월 납입료
	 @Override
	 public long getInsurancePremium(Insurance insurance, Customer customer, int totalPaymentMonth) {
		 ArrayList<Clause> clauses = insurance.getClauses();
		 long premium = 0;
		 for(Clause clause: clauses) {
			 premium += clause.getPremium();
		 }
		 double ratio = insurance.calculateRatio(customer);
		 
		 return (long) (premium * ratio)/totalPaymentMonth;
	 }
	 @Override
	 public ConsultApplication getConsultApplication(String id) {
		 return consultApplicationRepository.get(id);
	 }
	@Override
	public boolean addContract(Contract contract) {
		return contractRepository.add(contract);
	}
	public String addContractAndGetId(Contract contract) {
		return contractRepository.addAndGetId(contract);
	}
	@Override
	public ArrayList<Contract> getAllContractRegister() {
		ArrayList<Contract> contracts = contractRepository.getAll();
		ArrayList<Contract> contractRegisters = new ArrayList<Contract>();
		for(Contract contract: contracts) {
			if(contract.getState() == ContractState.UNDER_UW) {
				contractRegisters.add(contract);
			}
		}
		return contractRegisters;
	}
	@Override
	public Contract getContract(String id) {
		return contractRepository.get(id);
	}
	@Override
	public UW getUW(Contract contract) {
		ArrayList<UW> UWs = uwRepository.getAll();
		for(UW uw: UWs) {
			if(uw.getContractId().equals(contract.getId())) {
				return uw;
			}
		}
		return null;
	}
	 
}
