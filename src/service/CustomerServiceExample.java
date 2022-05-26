package service;

import domain.contract.Contract;
import domain.coverage.CarCoverage;
import domain.coverage.Coverage;
import domain.coverage.FireCoverage;
import domain.insurance.Insurance;
import repository.coverage.CoverageListImpl;
import repository.insurance.InsuranceListImpl;

public class CustomerServiceExample {
    private static final CoverageListImpl coverageRepository = CoverageListImpl.getInstance();
    private static final InsuranceListImpl insuranceRepository = InsuranceListImpl.getInstance();

    public void requestClaim(Contract contract) {
        Insurance insurance = insuranceRepository.get(contract.getInsuranceId());
        Coverage coverage = null;
        switch (insurance.getInsuranceCategory()) {
            case FIRE -> coverage = new FireCoverage();

            // set
            case CAR -> coverage = new CarCoverage();
            default -> System.out.println("error");

            // throw
        }
        coverageRepository.add(coverage);
    }
}
