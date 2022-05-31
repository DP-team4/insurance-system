package repository.claim;

import java.util.ArrayList;

import domain.carAccidentHandling.CarAccidentHandling;

public class ClaimListImpl implements ClaimList {
	private ArrayList<CarAccidentHandling> claims = new ArrayList<>();
	private static final ClaimListImpl claimList = new ClaimListImpl();

	private ClaimListImpl(){ }
	public static ClaimListImpl getInstance() { return claimList; }
	
	@Override
	public boolean add(CarAccidentHandling claim) {
		return claimList.add(claim);
	}
	@Override
	public boolean delete(String claimID) {
		CarAccidentHandling claim = this.get(claimID);
		return claims.remove(claim);
	}
	@Override
	public CarAccidentHandling get(String claimID) {
		for(CarAccidentHandling e : this.claims) {
			if(e.getId().equals(claimID)) return e;
		}
		return null;
	}
	@Override
	public ArrayList<CarAccidentHandling> getAll() {
		return claims;
	}
	public void printAll() {
		claims.forEach(i -> {
			System.out.println(i);
			System.out.println();
		});
	}

}
