package domain.insurance;

import domain.customer.Customer;

public class FireInsurance extends Insurance {

	public FireInsurance() {
		this.setInsuranceCategory(InsuranceCategory.FIRE);
	}
	@Override
	public double calculateRatio(Customer customer) {
		long housePrice = customer.getAdditionalInfo().getHousePrice();
		if(housePrice > 1000000000) return 1.1;
		else if(housePrice > 2000000000) return 1.0;
		else return 0.9;
	}
}
