package domain.Insurance;

import domain.Customer.Customer;

public class MarineInsurance extends Insurance {

	public MarineInsurance() {
		this.setInsuranceCategory(InsuranceCategory.MARINE);
	}
	@Override
	public double calculateRatio(Customer customer) {
		// customer�κ��� �ػ� ��� ���� ��û.
		return 1.2;
	}

	public void setDefault(String name) {
		super.setDefault(name, InsuranceCategory.MARINE);
	}
}
