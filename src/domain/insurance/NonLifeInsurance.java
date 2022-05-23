package domain.insurance;

import domain.customer.Customer;

public class NonLifeInsurance extends Insurance {

	public NonLifeInsurance() {
		this.setInsuranceCategory(InsuranceCategory.NON_LIFE);
	}
	@Override
	public double calculateRatio(Customer customer) {
		// customer�κ��� ��� ���� ��û.
		return 1.3;
	}

	public void setDefault(String name) {
		super.setDefault(name, InsuranceCategory.NON_LIFE);
	}
}
