package service.customer;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import dao.ContractDao;
import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Clause;
import domain.insurance.Insurance;
import domain.insurance.InsuranceCategory;
import repository.insurance.InsuranceRepository;

public class CustomerContractServiceImpl implements CustomerContractService {
	private final static CustomerContractService myContractManagementService = new CustomerContractServiceImpl();
    private static final ContractDao contractDao = new ContractDao(); // Repository
    private static final InsuranceRepository insuranceRepository = InsuranceRepository.getInstance();

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
	
	@Override
	public Contract getUnmaturedContractByCategory(String customerId, InsuranceCategory insuranceCategory) {
		ArrayList<Contract> customerContracts = contractDao.retrieveByCustomerId(customerId);
		for(Contract contract : customerContracts) {
			Insurance insurance = insuranceRepository.get(contract.getInsuranceId());
			if(insurance.getInsuranceCategory() == insuranceCategory) {
				if(!isMatured(contract)) return contract;
			}
		}
		return null;
	}
	
	private boolean isMatured(Contract contract) {
		return contract.getExpirationDateTime().isBefore(LocalDateTime.now());
	}
}
