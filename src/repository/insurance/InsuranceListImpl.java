package repository.insurance;

import domain.Insurance.Insurance;

import java.util.ArrayList;

/**
 * @author wls43
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:26
 */
public class InsuranceListImpl implements InsuranceList {
	private static final ArrayList<Insurance> insurances = new ArrayList<>();
	private static final InsuranceListImpl insuranceList = new InsuranceListImpl();

	// Singleton
	private InsuranceListImpl(){}
	public static InsuranceListImpl getInstance() { return insuranceList; }

	@Override
	public boolean add(Insurance insurance) {
		return insurances.add(insurance);
	}
	@Override
	public boolean delete(String insuranceID) {//¾Þ°£ÇÏ¸é if¶û else¶û °°ÀÌ ²¸ÁÙ °Í...
		Insurance insurance = this.get(insuranceID);
		return insurances.remove(insurance);
	}
	@Override
	public Insurance get(String insuranceID) {
		for(Insurance e : insurances) {
			if(e.getId().equals(insuranceID))	return e;
		}
		return null;
	}
	public ArrayList<Insurance> getAll() { return insurances; }
	public void printAll() {
		insurances.forEach(i -> {
			System.out.println(i);
			System.out.println();
		});
	}
//	public void update(){
//
//	}
}