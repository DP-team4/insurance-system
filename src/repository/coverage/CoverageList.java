package repository.coverage;

import java.util.ArrayList;

import domain.coverage.Coverage;

public interface CoverageList {
	boolean add(Coverage coverage);
	boolean delete(String coverageID);
	Coverage get(String coverageID);
//	boolean update();
	ArrayList<Coverage> getAll();

}
