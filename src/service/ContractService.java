package service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import domain.contract.Contract;
import repository.contract.ContractList;
import repository.contract.ContractListImpl;

public class ContractService {
	
	 private static final ContractList contracRepository = ContractListImpl.getInstance();

	 public ArrayList<Contract> getAllContracts() {
		 ArrayList<Contract> contracts = contracRepository.getAll();
	     contracts.forEach(System.out::println);
	     return contracts;
	 }
	
	 public ArrayList<Contract> getAllMatureContracts() {
		 ArrayList<Contract> matureContracts = new ArrayList<>();
		 ArrayList<Contract> contracts = contracRepository.getAll();
		 for (Contract contract: contracts) { 
			 long difference = ChronoUnit.DAYS.between(contract.getContractDateTime(), contract.getExpirationDateTime());
			 if (difference < 15) {
				 matureContracts.add(contract);
			 }
		 }
		 System.out.println("//// ������ ��� ��� ////");
		 matureContracts.forEach(System.out::println);
		 return matureContracts;
	 }
	 
	 
}
