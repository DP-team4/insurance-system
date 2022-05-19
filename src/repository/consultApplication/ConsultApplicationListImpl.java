package repository.consultApplication;

import java.util.ArrayList;

import domain.consultApplication.ConsultApplication;

public class ConsultApplicationListImpl implements ConsultApplicationList {
	private ArrayList<ConsultApplication> consultApplications = new ArrayList<>();
	private static final ConsultApplicationListImpl consultApplicationList = new ConsultApplicationListImpl();

	private ConsultApplicationListImpl(){ }
	public static ConsultApplicationListImpl getInstance() { return consultApplicationList; }
	
	@Override
	public boolean add(ConsultApplication consultApplication) {
		return consultApplicationList.add(consultApplication);
	}

	@Override
	public boolean delete(String consultApplicationID) {
		ConsultApplication consultApplication = this.get(consultApplicationID);
		return consultApplications.remove(consultApplication);
	}

	@Override
	public ConsultApplication get(String consultApplicationID) {
		for(ConsultApplication e : this.consultApplications) {
			if(e.getId().equals(consultApplicationID))	return e;
		}
		return null;
	}

	@Override
	public ArrayList<ConsultApplication> getAll() {
		return consultApplications;
	}
	
	public void printAll() {
		consultApplications.forEach(i -> {
			System.out.println(i);
			System.out.println();
		});
	}
}//end ConsultApplicationListImpl
