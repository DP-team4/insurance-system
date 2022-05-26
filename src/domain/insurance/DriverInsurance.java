package domain.insurance;

import domain.customer.Customer;

public class DriverInsurance extends Insurance {

	public DriverInsurance() {
		this.setInsuranceCategory(InsuranceCategory.DRIVER);
	}
	@Override
	public double calculateRatio(Customer customer) {
		// customer�κ��� ��� ���� ��û.
		return 1.3;
	}
}
