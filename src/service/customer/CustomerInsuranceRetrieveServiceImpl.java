package service.customer;

import java.util.ArrayList;
import java.util.stream.Collectors;

import domain.insurance.Clause;
import domain.insurance.Insurance;
import domain.insurance.InsuranceCategory;
import domain.insurance.InsuranceState;
import repository.insurance.InsuranceRepository;

public class CustomerInsuranceRetrieveServiceImpl implements CustomerInsuranceRetrieveService {
	private final static CustomerInsuranceRetrieveService customerInsuranceRetrieveService = new CustomerInsuranceRetrieveServiceImpl();
	private static final InsuranceRepository insuranceRepository = InsuranceRepository.getInstance();

	// Singleton
	private CustomerInsuranceRetrieveServiceImpl(){}
	public static CustomerInsuranceRetrieveService getInstance() { return customerInsuranceRetrieveService; }

	@Override
	public ArrayList<Insurance> getInsurancesOnSale() {
		ArrayList<Insurance> insurances = insuranceRepository.getAll();
		ArrayList<Insurance> filtered = (ArrayList<Insurance>) insurances.stream()
				.filter(insurance -> insurance.getInsuranceState().equals(InsuranceState.ON_SALE))
				.collect(Collectors.toList());
		return filtered;
	}
	
	@Override
	public ArrayList<Insurance> filterInsuranceByCategory(ArrayList<Insurance> insurances, InsuranceCategory insuranceCategory) {
		if(insuranceCategory == null) return insurances;
		ArrayList<Insurance> filtered = (ArrayList<Insurance>) insurances.stream()
				.filter(insurance -> insurance.getInsuranceCategory().equals(insuranceCategory))
				.collect(Collectors.toList());
		return filtered;
	}
	
	@Override
	public long getInsuredAmountSum(Insurance insurance) {
		ArrayList<Clause> clauses = insurance.getClauses();
		long sum = 0;
        for(Clause clause : clauses) {
            sum += clause.getInsuredAmount();
        }
        return sum;
	}
	
	@Override
	public long getPremiumSum(Insurance insurance) {
		ArrayList<Clause> clauses = insurance.getClauses();
		long sum = 0;
        for(Clause clause : clauses) {
            sum += clause.getPremium();
        }
        return sum;
	}
}
