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

public interface SalesService {
	ArrayList<Contract> getAllContract();
	Insurance getInsuranceByName(String name);
	Insurance getInsurance(String id);
	boolean requestUW(Contract contract, ArrayList<UWDocument> documents);
	ArrayList<ConsultApplication> getAllConsultApplication();
	boolean updateConsultApplication(ConsultApplication consultApplication);
	boolean updateContract(Contract contract);
	Customer getCustomerByEmail(String email);
	Customer getCustomer(String id);
	long getInsuredAmount(Insurance insurance, Customer customer);
	long getInsurancePremium(Insurance insurance, Customer customer, int totalPaymentMonth);
	ConsultApplication getConsultApplication(String id);
	boolean addContract(Contract contract);
	ArrayList<Contract> getAllContractRegister();
	Contract getContract(String id);
	UW getUW(Contract contract);
}
