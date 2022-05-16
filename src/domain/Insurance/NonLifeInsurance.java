package domain.Insurance;

import domain.Customer.Customer;

public class NonLifeInsurance extends Insurance {

	public static NonLifeInsurance create(String name) {
		NonLifeInsurance insurance = new NonLifeInsurance();
		insurance.setDefault(name);
		return insurance;
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
