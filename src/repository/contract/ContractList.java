package repository.contract;

import java.util.ArrayList;
import domain.contract.Contract;

public interface ContractList {
	boolean add(Contract contract);
	boolean delete(String contractId);
	Contract get(String contractId);
//	boolean update();
	ArrayList<Contract> getAll();
}
