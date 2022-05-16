package repository.uw;


import domain.UW.UW;

import java.util.ArrayList;

public class UWListImpl implements UWList {
	private static final ArrayList<UW> uws = new ArrayList<>();
	private static final UWListImpl uwList = new UWListImpl();

	private UWListImpl(){
	}
	public static UWListImpl getInstance() { return uwList; }

	public boolean add(UW uw){
		return this.uws.add(uw);
	}
	public boolean delete(String uwID){
		UW uw = this.get(uwID);
		return uws.remove(uw);
	}
	public UW get(String uwID){
		for (UW uw : uws) {
			if(uw.getId().equals(uwID)) return uw;
		}
		return null;
	}

	public void printAll() {
		uws.forEach(i -> {
			System.out.println(i);
			System.out.println();
		});
	}
//	public void update(){
//		System.out.println("¹Ì±¸Çö");
//	}
}