package domain.insurance;

import domain.customer.Customer;

public class MarineInsurance extends Insurance {

	public MarineInsurance() {
		this.setInsuranceCategory(InsuranceCategory.MARINE);
	}
	@Override
	public double calculateRatio(Customer customer) {
		// customer�κ��� �ػ� ��� ���� ��û.
		return 1.2;
	}
}