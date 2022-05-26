package domain.insurance;

import domain.customer.Customer;

public class CarInsurance extends Insurance {

	public CarInsurance() {
		this.setInsuranceCategory(InsuranceCategory.CAR);
	}
	@Override
	public double calculateRatio(Customer customer) {
		// customer�κ��� �ڵ��� ���� ��û.
		return 1.4;
	}
}
