package domain.Insurance;

import domain.Customer.Customer;

public class FireInsurance extends Insurance {

	@Override
	public double calculateRatio(Customer customer) {
		if(customer.getHousePrice() > 1000000000) return 1.1;
		else if(customer.getHousePrice() > 2000000000) return 1.0;
		else return 0.9;
	}

	public void setDefault(String name) {
		super.setDefault(name, InsuranceCategory.FIRE);
	}
}
