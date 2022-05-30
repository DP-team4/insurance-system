package domain.insurance;

import domain.customer.Customer;

public class DriverInsurance extends Insurance {

	public DriverInsurance() {
		this.setInsuranceCategory(InsuranceCategory.DRIVER);
	}
	@Override
	public double calculateRatio(Customer customer) {
		long drivingCareer = customer.getAdditionalInfo().getDrivingCareer();
		if(drivingCareer > 20) return 0.7;
		else if(drivingCareer > 10) return 1.0;
		else if(drivingCareer > 5) return 1.2;
		else if(drivingCareer > 3) return 1.4;
		else if(drivingCareer > 1) return 1.7;
		else return 2;
	}
}
