package repository.consultApplication;

import java.util.ArrayList;

import domain.consultApplication.ConsultApplication;

public interface ConsultApplicationList {
	boolean add(ConsultApplication consultApplication);
	boolean delete(String id);
	ConsultApplication get(String id);
//	void update();
	ArrayList<ConsultApplication> getAll();
}
