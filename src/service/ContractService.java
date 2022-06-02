package service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import dao.CancelApplicationDao;
import dao.ContractDao;
import dao.InsuranceDao;
import domain.cancelApplication.CancelApplication;
import domain.cancelApplication.CancelApplicationState;
import domain.contract.Contract;
import domain.insurance.Insurance;

public class ContractService {
	
	 private static final ContractDao contractDao = new ContractDao();
	 private static final CancelApplicationDao cancelApplicationDao = new CancelApplicationDao();
	 
	 //��� ��� ��û
	 public ArrayList<Contract> requestAllContracts() {
		 ArrayList<Contract> contracts = contractDao.retrieveAll();
	     return contracts;
	 }
	
	 // �������� ��� ��û
	 public ArrayList<Contract> requestAllMatureContracts() {
		 ArrayList<Contract> matureContracts = new ArrayList<>();
		 ArrayList<Contract> contracts = contractDao.retrieveAll();
		 for (Contract contract: contracts) { 
			 long difference = ChronoUnit.DAYS.between(contract.getContractDateTime(), contract.getExpirationDateTime());
			 if (difference < 15) {
				 matureContracts.add(contract);
			 }
		 }
		 return matureContracts;
	 }
	 
	 // ��� ���� ��û
	 public boolean requestDeleteContract(String id) {
		 return contractDao.delete(id);
	 }
	 
	 // ��� ���� ��û ��� ��û
	 public ArrayList<CancelApplication> requestAllCancelApplications(){
		 ArrayList<CancelApplication> cancelApplications = cancelApplicationDao.retrieveAll();
		 return cancelApplications;
	 }
	 
	 // ��� ���� ����
	 public boolean requestAcceptCancleApplication(String id) {
		 CancelApplication cancelApplication = cancelApplicationDao.retrieveById(id);
		 cancelApplication.setState(CancelApplicationState.ACCEPTED);
		 return cancelApplicationDao.update(cancelApplication);
	 }
	 
	 // ��� ���� ����
	 public boolean requestRejectCancleApplication(String id) {
		CancelApplication cancelApplication = cancelApplicationDao.retrieveById(id);
		cancelApplication.setState(CancelApplicationState.REJECTED);
		return cancelApplicationDao.update(cancelApplication);
	 }
	 
}
