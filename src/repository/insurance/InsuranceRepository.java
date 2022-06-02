package repository.insurance;

import dao.ClauseDao;
import dao.InsuranceDao;
import domain.insurance.Clause;
import domain.insurance.Insurance;

import java.util.ArrayList;

/**
 * @author wls43
 * @version 1.0
 * @created 09-5-2022 오후 4:48:26
 */
public class InsuranceRepository {
	private static final InsuranceRepository instance = new InsuranceRepository();
	private final InsuranceDao insuranceDao = new InsuranceDao();
	private final ClauseDao clauseDao = new ClauseDao();

	// Singleton
	private InsuranceRepository(){}
	public static InsuranceRepository getInstance() { return instance; }

	public boolean add(Insurance insurance) {
		String id = insuranceDao.createAndGetId(insurance);
		if(id==null) return false;
		insurance.setId(id);
		for (Clause clause : insurance.getClauses()) {
			clause.setInsuranceId(id);
			String clauseId = clauseDao.createAndGetId(clause);
			clause.setId(clauseId);
		}
		return true;
	}
	public boolean delete(String id) {
		boolean clausesDeleted = clauseDao.deleteAllByInsuranceId(id);
		if(!clausesDeleted) return false;
		return insuranceDao.delete(id);
	}
	public Insurance get(String id) {
		Insurance insurance = insuranceDao.retrieveById(id);
		ArrayList<Clause> clauses = clauseDao.retrieveAllByInsuranceId(id);
		insurance.setClauses(clauses);
		return insurance;
	}
	public ArrayList<Insurance> getAll() {
		ArrayList<Insurance> insurances = insuranceDao.retrieveAll();
		for (Insurance insurance : insurances) {
			ArrayList<Clause> clauses = clauseDao.retrieveAllByInsuranceId(insurance.getId());
			insurance.setClauses(clauses);
		}
		return insurances;
	}
	public Insurance getByName(String name) {
		Insurance insurance = insuranceDao.retrieveByName(name);
		ArrayList<Clause> clauses = clauseDao.retrieveAllByInsuranceId(insurance.getId());
		insurance.setClauses(clauses);
		return insurance;
	}
	public boolean update(Insurance insurance) {
		try {
			Insurance before = this.get(insurance.getId());
			if(!before.equalsAttributes(insurance)) if(!insuranceDao.update(insurance)) return false;
			ArrayList<Clause> beforeClauses = before.getClauses();
			ArrayList<Clause> tobeClauses = insurance.getClauses();

			// clause 가 모두 삭제되는 경우 = 전부 삭제
			if(tobeClauses.size() < 1) { beforeClauses.forEach(c -> clauseDao.delete(c.getId())); return true;}
			// 기존에 clause 가 하나도 없는 경우 = 전부 추가
			if(beforeClauses.size() < 1) {tobeClauses.forEach(clauseDao::create); return true; }

			for (Clause tobeClause : tobeClauses) {
				System.out.println("tobe in");
				//추가
				if(tobeClause.getId() == null) clauseDao.create(tobeClause);
				for (Clause beforeClause : beforeClauses) {
					System.out.println("before in");

					//삭제
					if(!tobeClauses.contains(beforeClause)) {
						System.out.println("delete in");
						clauseDao.delete(beforeClause.getId());
					}
					// 내용 수정
					if (tobeClause.equals(beforeClause) && !tobeClause.equalsAttributes(beforeClause)) clauseDao.update(tobeClause);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public Clause getClause(String clauseId) {
		return clauseDao.retrieveById(clauseId);
	}

	public boolean updateClause(Clause clause) {
		return clauseDao.update(clause);
	}
}