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
		return consultApplications.add(consultApplication);
	}

	@Override
	public boolean delete(String id) {
		ConsultApplication consultApplication = this.get(id);
		return consultApplications.remove(consultApplication);
	}

	@Override
	public ConsultApplication get(String id) {
		for(ConsultApplication e : this.consultApplications) {
			if(e.getId().equals(id))	return e;
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
