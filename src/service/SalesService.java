package service;
import java.util.ArrayList;
import dao.ContractDao;
import dao.InsuranceDao;
import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Insurance;

public class SalesService {
	
	 private static final ContractDao contractDao = new ContractDao();
	 
	 // 계약 목록 요청
	 public ArrayList<Contract> requestAllContracts() {
		 ArrayList<Contract> contracts = contractDao.retrieveAll();
	     return contracts;
	 }
	 
	 // 보험 정보 요청
	 public Insurance requestInsurance(String name) {
		 InsuranceDao insuranceDao = new InsuranceDao();
		 Insurance insurance = insuranceDao.retrieveByName(name);
		return insurance;
	 }
	 
	 public void requestUW(Contract contract) {
		 
	 }
	 
	 // 가입 접수 목록 요청
	 public void requestAllContractWaiting() {
		 
	 }
	 
	 // 가입 통과
	 public void requestAcceptContract() {
		 
	 }
	 // 가입 실패
	 public void requestRejectContract() {
		 
	 }
	 // 고객 찾기
	 public Customer requestCustomer() {
		 
		return null;
	 }
	 
}
