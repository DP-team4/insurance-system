package service.customer;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import dao.ContractDao;
import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Clause;
import domain.insurance.Insurance;

public class CustomerContractServiceImpl implements CustomerContractService {
	private final static CustomerContractService myContractManagementService = new CustomerContractServiceImpl();
    private static final ContractDao contractDao = new ContractDao(); // Repository

	// Singleton
	private CustomerContractServiceImpl(){}
	public static CustomerContractService getInstance() { return myContractManagementService; }
	
	@Override
	public ArrayList<Contract> getCustomerContracts(String customerId) {
		return contractDao.retrieveByCustomerId(customerId);
	}

	@Override
	public long getMonthlyPremium(Contract contract, Insurance insurance, Customer customer) {
		// 각 약관들 premium 합하기 = sum
		ArrayList<Clause> clauses = insurance.getClauses();
		long premiumSum = 0;
		for(Clause clause: clauses) premiumSum += clause.getPremium();
		// 요율
		double ratio = insurance.calculateRatio(customer);
		// 납입 월 수
		LocalDateTime contractDateTime = contract.getContractDateTime();
		LocalDateTime expirationDateTime = contract.getExpirationDateTime();
		long month = ChronoUnit.MONTHS.between(contractDateTime, expirationDateTime);
		
		return (long) ((premiumSum * ratio) / month);
	}

}
