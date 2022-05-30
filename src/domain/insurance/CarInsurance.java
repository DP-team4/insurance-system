package domain.insurance;

import domain.customer.Customer;

public class CarInsurance extends Insurance {

	public CarInsurance() {
		this.setInsuranceCategory(InsuranceCategory.CAR);
	}
	@Override
	public double calculateRatio(Customer customer) {
		long carPrice = customer.getAdditionalInfo().getCarPrice();
		if(carPrice < 100000000L) return 0.9;
		else if(carPrice < 100000000L) return 1.0;
		else if(carPrice < 200000000L) return 1.1;
		else return 1.3;
	}
}
