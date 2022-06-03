package service;
import java.util.ArrayList;
import dao.ContractDao;
import dao.CustomerDao;
import dao.InsuranceDao;
import dao.UWDao;
import domain.consultApplication.ConsultApplication;
import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Clause;
import domain.insurance.Insurance;
import domain.uw.UW;
import domain.uw.UWDocument;
import domain.uw.UWDocumentState;
import domain.uw.UWState;
import repository.CustomerRepository;
import repository.consultApplication.ConsultApplicationRepository;
import repository.contract.ContractRepository;
import repository.insurance.InsuranceRepository;
import repository.uw.UWRepository;

public class SalesService {
	 private static final SalesService instance = new SalesService();
	 private static final ContractRepository contractRepository = ContractRepository.getInstance();
	 private static final ConsultApplicationRepository consultApplicationRepository = ConsultApplicationRepository.getInstance();
	 private static final CustomerRepository customerDao = CustomerRepository.getInstance();
	 
	 private SalesService() {}
	 public static SalesService getInstance() {
		 return instance;
	 }
	 
	 // 계약 목록 요청
	 public ArrayList<Contract> requestAllContracts() {
		 return contractRepository.getAll();
	 }
	 
	 // 보험 정보 요청
	 public Insurance requestInsurance(String name) {
		 InsuranceRepository insuranceDao = InsuranceRepository.getInstance();
		 Insurance insurance = insuranceDao.getByName(name);
		return insurance;
	 }
	 
	 public boolean requestUW(Contract contract, ArrayList<UWDocument> documents) {
		 UW uw = new UW();
		 uw.setDocuments(documents);
		 uw.setUwState(UWState.UNDER_AUDIT);
		 uw.setContractId(contract.getId());
		 UWRepository uwRepository = UWRepository.getInstance();
		 return uwRepository.add(uw);
	 }
	 
	 // 가입상담신청 목록 요청
	 public ArrayList<ConsultApplication> getAllConsultApplication() {
		 return consultApplicationRepository.getAll();
	 }
	 // 가입상담신청 수정
	 public boolean updateConsultApplication(ConsultApplication consultApplication) {
		 return consultApplicationRepository.update(consultApplication);
	 }
	 // 계약 수정
	 public void updateContract() {
		 
	 }
	
	 // 고객 찾기
	 public Customer getCustomerByEmail(String email) {
		return customerDao.getByEmail(email);
	 }
	 public Customer getCustomer(String id) {
		 return customerDao.get(id);
	 }
	 
	 // 보상금액
	 public long getInsuredAmount(Insurance insurance, Customer customer) {
		 ArrayList<Clause> clauses = insurance.getClauses();
		 long insuredAmount = 0;
		 for(Clause clause: clauses) {
			 insuredAmount += clause.getInsuredAmount();
		 }
		 return insuredAmount;
	 }
	 // 월 납입료
	 public long getInsurancePremium(Insurance insurance, Customer customer) {
		 ArrayList<Clause> clauses = insurance.getClauses();
		 long premium = 0;
		 for(Clause clause: clauses) {
			 premium += clause.getPremium();
		 }
		 double ratio = insurance.calculateRatio(customer);
		 
		 return (long) (premium * ratio);
	 }
	
	public ConsultApplication getConsultApplication(String id) {
		return consultApplicationRepository.get(id);
	}
	 
}
