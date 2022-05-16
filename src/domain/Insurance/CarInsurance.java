package domain.Insurance;

import domain.Customer.Customer;

public class CarInsurance extends Insurance {

	public static CarInsurance create(String name) {
		CarInsurance insurance = new CarInsurance();
		insurance.setDefault(name);
		return insurance;
	}


	@Override
	public double calculateRatio(Customer customer) {
		// customer�κ��� �ڵ��� ���� ��û.
		return 1.4;
	}

	public void setDefault(String name) {
		super.setDefault(name, InsuranceCategory.CAR);
	}
}
