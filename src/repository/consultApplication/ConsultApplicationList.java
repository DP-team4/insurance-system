package repository.consultApplication;

import java.util.ArrayList;

import domain.consultApplication.ConsultApplication;

public interface ConsultApplicationList {
	boolean add(ConsultApplication consultApplication);
	boolean delete(String consultApplicationID);
	ConsultApplication get(String consultApplicationID);
//	void update();
	ArrayList<ConsultApplication> getAll();
}
