package domain.insurance;

import domain.customer.Customer;

public class DriverInsurance extends Insurance {

	public DriverInsurance() {
		this.setInsuranceCategory(InsuranceCategory.DRIVER);
	}
	@Override
	public double calculateRatio(Customer customer) {
		// customer로부터 재산 정보 요청.
		return 1.3;
	}
}
