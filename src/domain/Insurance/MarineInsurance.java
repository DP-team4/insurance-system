package domain.Insurance;

import domain.Customer.Customer;

public class MarineInsurance extends Insurance {

	public MarineInsurance() {
		this.setInsuranceCategory(InsuranceCategory.MARINE);
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
