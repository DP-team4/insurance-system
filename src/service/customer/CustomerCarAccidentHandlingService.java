package service.customer;

import java.util.ArrayList;

import domain.carAccidentHandling.CarAccidentHandling;

public interface CustomerCarAccidentHandlingService {
	boolean applyCarAccidentHandling(CarAccidentHandling carAccidentHandling);
	boolean revokeMyCarAccidentHandling(String id, String customerId);
	ArrayList<CarAccidentHandling> getByCustomerId(String customerId);

}
