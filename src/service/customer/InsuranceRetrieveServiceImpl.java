package service.customer;

import java.util.ArrayList;
import java.util.stream.Collectors;

import dao.ContractDao;
import domain.cancelApplication.CancelApplication;
import domain.contract.Contract;
import domain.insurance.Insurance;
import domain.insurance.InsuranceCategory;
import domain.insurance.InsuranceState;
import repository.insurance.InsuranceRepository;

public class InsuranceRetrieveServiceImpl implements InsuranceRetrieveService {
	private final static InsuranceRetrieveService insuranceRetrieveService = new InsuranceRetrieveServiceImpl();
	private static final InsuranceRepository insuranceRepository = InsuranceRepository.getInstance();

	// Singleton
	private InsuranceRetrieveServiceImpl(){}
	public static InsuranceRetrieveService getInstance() { return insuranceRetrieveService; }

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
}
