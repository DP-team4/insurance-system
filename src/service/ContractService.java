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
	 
	 //계약 목록 요청
	 public ArrayList<Contract> requestAllContracts() {
		 ArrayList<Contract> contracts = contractDao.retrieveAll();
	     return contracts;
	 }
	
	 // 만기대상계약 목록 요청
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
	 
	 // 계약 삭제 요청
	 public boolean requestDeleteContract(String id) {
		 return contractDao.delete(id);
	 }
	 
	 // 계약 해지 신청 목록 요청
	 public ArrayList<CancelApplication> requestAllCancelApplications(){
		 ArrayList<CancelApplication> cancelApplications = cancelApplicationDao.retrieveAll();
		 return cancelApplications;
	 }
	 
	 // 계약 해지 승인
	 public boolean requestAcceptCancleApplication(String id) {
		 CancelApplication cancelApplication = cancelApplicationDao.retrieveById(id);
		 cancelApplication.setState(CancelApplicationState.ACCEPTED);
		 return cancelApplicationDao.update(cancelApplication);
	 }
	 
	 // 계약 해지 거절
	 public boolean requestRejectCancleApplication(String id) {
		CancelApplication cancelApplication = cancelApplicationDao.retrieveById(id);
		cancelApplication.setState(CancelApplicationState.REJECTED);
		return cancelApplicationDao.update(cancelApplication);
	 }
	 
}
