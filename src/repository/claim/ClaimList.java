package repository.claim;

import java.util.ArrayList;

import domain.claim.Claim;

public interface ClaimList {
	boolean add(Claim claim);
	boolean delete(String claimID);
	Claim get(String claimID);
//	boolean update();
	ArrayList<Claim> getAll();
}
