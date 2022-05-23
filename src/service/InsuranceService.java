package service;

import domain.insurance.Insurance;
import repository.insurance.InsuranceList;
import repository.insurance.InsuranceListImpl;

import java.util.ArrayList;

public class InsuranceService {
    private static final InsuranceList insuranceRepository = InsuranceListImpl.getInstance();

    public ArrayList<Insurance> getAllInsurances() {
        ArrayList<Insurance> insurances = insuranceRepository.getAll();
        insurances.forEach(System.out::println);
        return insurances;
        
        
        
    }
}
