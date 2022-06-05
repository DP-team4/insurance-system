package repository.consultApplication;

import java.util.ArrayList;

import dao.ConsultApplicationDao;
import domain.consultApplication.ConsultApplication;

public class ConsultApplicationRepository {
	private static final ConsultApplicationRepository instance = new ConsultApplicationRepository();
	private static final ConsultApplicationDao consultApplicationDao = new ConsultApplicationDao();

	private ConsultApplicationRepository(){ }
	public static ConsultApplicationRepository getInstance() { return instance; }

	public boolean add(ConsultApplication consultApplication) {
		return consultApplicationDao.create(consultApplication);
	}
	
	public boolean update(ConsultApplication consultApplication) {
		return consultApplicationDao.update(consultApplication);
	}
	
	public boolean delete(String id) {
		return consultApplicationDao.delete(id);
	}

	public ArrayList<ConsultApplication> getAll() {
		 return consultApplicationDao.retrieveAll();
	}

	public ConsultApplication get(String id) {
		return consultApplicationDao.retrieveById(id);
	}

	public ArrayList<ConsultApplication> getByCustomerId(String customerId) {
		return consultApplicationDao.retrieveByCustomerId(customerId);
	}
}//end ConsultApplicationListImpl