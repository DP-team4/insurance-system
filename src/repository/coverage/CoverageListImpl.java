package repository.coverage;

import java.util.ArrayList;

import domain.coverage.Coverage;

public class CoverageListImpl implements CoverageList {
	private ArrayList<Coverage> coverages = new ArrayList<>();
	private static final CoverageListImpl coverageList = new CoverageListImpl();

	private CoverageListImpl(){ }
	public static CoverageListImpl getInstance() { return coverageList; }

	@Override
	public boolean add(Coverage coverage) {
		return coverageList.add(coverage);
	}
	@Override
	public boolean delete(String coverageID) {
		Coverage coverage = this.get(coverageID);
		return coverages.remove(coverage);
	}
	@Override
	public Coverage get(String coverageID) {
		for(Coverage e : this.coverages) {
			if(e.getId().equals(coverageID)) return e;
		}
		return null;
	}
	@Override
	public ArrayList<Coverage> getAll() {
		return coverages;
	}
	public void printAll() {
		coverages.forEach(i -> {
			System.out.println(i);
			System.out.println();
		});
	}
}
