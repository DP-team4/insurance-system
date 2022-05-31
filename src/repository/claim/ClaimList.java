package repository.claim;

import java.util.ArrayList;

import domain.carAccidentHandling.CarAccidentHandling;

public interface ClaimList {
	boolean add(CarAccidentHandling claim);
	boolean delete(String claimID);
	CarAccidentHandling get(String claimID);
//	boolean update();
	ArrayList<CarAccidentHandling> getAll();
}
