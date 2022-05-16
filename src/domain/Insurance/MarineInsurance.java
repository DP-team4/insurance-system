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
		// customer로부터 해상 재산 정보 요청.
		return 1.2;
	}

	public void setDefault(String name) {
		super.setDefault(name, InsuranceCategory.MARINE);
	}
}
