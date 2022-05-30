package repository.insurance;

import dao.InsuranceDao;
import domain.insurance.Insurance;

import java.util.ArrayList;

/**
 * @author wls43
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:26
 */
public class InsuranceListImpl implements InsuranceList {
	private static final InsuranceListImpl insuranceList = new InsuranceListImpl();
	private static final InsuranceDao insuranceDao = new InsuranceDao();

	// Singleton
	private InsuranceListImpl(){}
	public static InsuranceListImpl getInstance() { return insuranceList; }

	@Override
	public boolean add(Insurance insurance) {
		return insuranceDao.create(insurance);
	}
	@Override
	public boolean delete(String id) {
		return insuranceDao.delete(id);
	}
	@Override
	public Insurance get(String id) {
		return insuranceDao.retrieveById(id);
	}
	public ArrayList<Insurance> getAll() {
		return insuranceDao.retrieveAll();
	}
	public Insurance getByName(String name) {
		return insuranceDao.retrieveByName(name);
	}
	@Override
	public boolean update(Insurance insurance) {
		return insuranceDao.update(insurance);
	}
}