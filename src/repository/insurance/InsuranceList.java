package repository.insurance;

import domain.Insurance.Insurance;

import java.util.ArrayList;

/**
 * @author wls43
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:26
 */

public interface InsuranceList {
	boolean add(Insurance insurance);
	boolean delete(String insuranceID);
	Insurance get(String insuranceID);
//	boolean update();
	ArrayList<Insurance> getAll();

}