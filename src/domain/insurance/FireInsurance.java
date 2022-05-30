package domain.insurance;

import domain.customer.Customer;

public class FireInsurance extends Insurance {

	public FireInsurance() {
		this.setInsuranceCategory(InsuranceCategory.FIRE);
	}
	@Override
	public double calculateRatio(Customer customer) {
		long housePrice = customer.getAdditionalInfo().getHousePrice();
		if(housePrice < 700000000L) return 1.0;
		else if(housePrice < 700000000L) return 1.2;
		else if(housePrice < 1000000000L) return 1.3;
		else if(housePrice < 5000000000L) return 1.5;
		else return 1.8;
	}
}
