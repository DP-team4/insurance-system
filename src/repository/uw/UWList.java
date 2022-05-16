package repository.uw;


import domain.UW.UW;

public interface UWList {
	boolean add(UW uw);
	boolean delete(String uwID);
	UW get(String uwID);
//	void update();
}