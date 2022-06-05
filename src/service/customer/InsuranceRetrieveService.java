package service.customer;

import java.util.ArrayList;

import domain.insurance.Insurance;
import domain.insurance.InsuranceCategory;

public interface InsuranceRetrieveService {
    ArrayList<Insurance> getInsurancesOnSale();
    ArrayList<Insurance> filterInsuranceByCategory(ArrayList<Insurance> insurances, InsuranceCategory insuranceCategory);
}
