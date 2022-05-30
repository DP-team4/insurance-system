package repository.insurance;

import domain.insurance.Insurance;

import java.util.ArrayList;

/**
 * @author wls43
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:26
 */

public interface InsuranceList {
	boolean add(Insurance insurance);
	boolean delete(String id);
	Insurance get(String id);
	boolean update(Insurance insurance);
	ArrayList<Insurance> getAll();

}