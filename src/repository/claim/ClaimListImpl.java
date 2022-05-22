package repository.claim;

import java.util.ArrayList;

import domain.claim.Claim;

public class ClaimListImpl implements ClaimList {
	private ArrayList<Claim> claims = new ArrayList<>();
	private static final ClaimListImpl claimList = new ClaimListImpl();

	private ClaimListImpl(){ }
	public static ClaimListImpl getInstance() { return claimList; }
	
	@Override
	public boolean add(Claim claim) {
		return claimList.add(claim);
	}
	@Override
	public boolean delete(String claimID) {
		Claim claim = this.get(claimID);
		return claims.remove(claim);
	}
	@Override
	public Claim get(String claimID) {
		for(Claim e : this.claims) {
			if(e.getId().equals(claimID)) return e;
		}
		return null;
	}
	@Override
	public ArrayList<Claim> getAll() {
		return claims;
	}
	public void printAll() {
		claims.forEach(i -> {
			System.out.println(i);
			System.out.println();
		});
	}

}
