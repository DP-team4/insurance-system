package domain.Insurance;

import domain.Customer.Customer;

public class MarineInsurance extends Insurance {

	public static MarineInsurance create(String name) {
		MarineInsurance insurance = new MarineInsurance();
		insurance.setDefault(name);
		return insurance;
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
