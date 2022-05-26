package domain.insurance;

import domain.customer.Customer;

public class CarInsurance extends Insurance {

	public CarInsurance() {
		this.setInsuranceCategory(InsuranceCategory.CAR);
	}
	@Override
	public double calculateRatio(Customer customer) {
		// customer로부터 자동차 정보 요청.
		return 1.4;
	}
}
