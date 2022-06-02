package repository;

import java.util.ArrayList;

import dao.CancelApplicationDao;
import domain.cancelApplication.CancelApplication;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:25
 */
public class CancelApplicationListImpl {
	private static final CancelApplicationListImpl cancelApplicationList = new CancelApplicationListImpl();
	private static final CancelApplicationDao cancelApplicationDao = new CancelApplicationDao();

	private CancelApplicationListImpl(){ }
	public static CancelApplicationListImpl getInstance() { return cancelApplicationList; }

	public boolean add(CancelApplication cancelApplication) {
		return cancelApplicationDao.create(cancelApplication);
	}
	
	public boolean update(CancelApplication cancelApplication) {
		return cancelApplicationDao.update(cancelApplication);
	}
	
	public boolean delete(String id) {
		return cancelApplicationDao.delete(id);
	}

	public ArrayList<CancelApplication> getAll() {
		 return cancelApplicationDao.retrieveAll();
	}

	public CancelApplication get(String id) {
		return cancelApplicationDao.retrieveById(id);
	}

	public ArrayList<CancelApplication> getByCustomerId(String customerId) {
		return cancelApplicationDao.retrieveByCustomerId(customerId);
	}
}//end CancelApplicationListImpl