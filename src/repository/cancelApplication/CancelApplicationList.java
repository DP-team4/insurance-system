package repository.cancelApplication;

import java.util.ArrayList;

import domain.cancelApplication.CancelApplication;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:25
 */
public interface CancelApplicationList {
	boolean add(CancelApplication cancelApplication);
	boolean delete(String id);
	CancelApplication get(String id);
//	void update();
	ArrayList<CancelApplication> getAll();
}