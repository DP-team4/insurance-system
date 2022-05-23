package repository.uw;


import domain.uw.UW;

import java.util.ArrayList;

public interface UWList {
	boolean add(UW uw);
	boolean delete(String uwID);
	UW get(String uwID);
	ArrayList<UW> getAll();
//	void update();
}