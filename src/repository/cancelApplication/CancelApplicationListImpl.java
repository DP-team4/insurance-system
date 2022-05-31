package repository.cancelApplication;

import java.util.ArrayList;

import domain.cancelApplication.CancelApplication;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:25
 */
public class CancelApplicationListImpl implements CancelApplicationList {
	private ArrayList<CancelApplication> cancelApplications = new ArrayList<>();
	private static final CancelApplicationListImpl cancelApplicationList = new CancelApplicationListImpl();

	private CancelApplicationListImpl(){ }
	public static CancelApplicationListImpl getInstance() { return cancelApplicationList; }

	@Override
	public boolean add(CancelApplication cancelApplication) {
		return cancelApplications.add(cancelApplication);
	}

	@Override
	public boolean delete(String id) {
		CancelApplication cancelApplciation = this.get(id);
		return cancelApplications.remove(cancelApplciation);
	}

	@Override
	public CancelApplication get(String id) {
		for(CancelApplication e : this.cancelApplications) {
			if(e.getId().equals(id))	return e;
		}
		return null;
	}

	@Override
	public ArrayList<CancelApplication> getAll() {
		return cancelApplications;
	}
	
	public void printAll() {
		cancelApplications.forEach(i -> {
			System.out.println(i);
			System.out.println();
		});
	}
}//end CancelApplicationListImpl