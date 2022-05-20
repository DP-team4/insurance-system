package repository.contract;

import java.util.ArrayList;
import domain.contract.Contract;

public class ContractListImpl implements ContractList {
	
	private static final ArrayList<Contract> contracts = new ArrayList<>();
	private static final ContractListImpl contractList = new ContractListImpl();

	// Singleton
	private ContractListImpl(){}
	public static ContractListImpl getInstance() { return contractList; }
	
	@Override
	public boolean add(Contract contract) {
		return contracts.add(contract);
	}

	@Override
	public boolean delete(String contractId) {
		Contract contract = this.get(contractId);
		return contracts.remove(contract);
	}

	@Override
	public Contract get(String contractId) {
		for(Contract contract : contracts) {
			if(contract.getId().equals(contractId))	return contract;
		}
		return null;
	}

	@Override
	public ArrayList<Contract> getAll() {
		return contracts;
	}
	
	public void printAll() {
		contracts.forEach(i -> {
			System.out.println(i);
			System.out.println();
		});
	}
	
//	@Override
//	public boolean update() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
