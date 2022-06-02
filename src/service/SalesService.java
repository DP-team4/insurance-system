package service;
import java.util.ArrayList;
import dao.ContractDao;
import dao.InsuranceDao;
import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Insurance;

public class SalesService {
	
	 private static final ContractDao contractDao = new ContractDao();
	 
	 // ��� ��� ��û
	 public ArrayList<Contract> requestAllContracts() {
		 ArrayList<Contract> contracts = contractDao.retrieveAll();
	     return contracts;
	 }
	 
	 // ���� ���� ��û
	 public Insurance requestInsurance(String name) {
		 InsuranceDao insuranceDao = new InsuranceDao();
		 Insurance insurance = insuranceDao.retrieveByName(name);
		return insurance;
	 }
	 
	 public void requestUW(Contract contract) {
		 
	 }
	 
	 // ���� ���� ��� ��û
	 public void requestAllContractWaiting() {
		 
	 }
	 
	 // ���� ���
	 public void requestAcceptContract() {
		 
	 }
	 // ���� ����
	 public void requestRejectContract() {
		 
	 }
	 // �� ã��
	 public Customer requestCustomer() {
		 
		return null;
	 }
	 
}
