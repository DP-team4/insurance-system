package repository.coverage;

import java.util.ArrayList;

import domain.benefitPayment.BenefitPayment;

public class CoverageListImpl implements CoverageList {
	private ArrayList<BenefitPayment> coverages = new ArrayList<>();
	private static final CoverageListImpl coverageList = new CoverageListImpl();

	private CoverageListImpl(){ }
	public static CoverageListImpl getInstance() { return coverageList; }

	@Override
	public boolean add(BenefitPayment coverage) {
		return coverageList.add(coverage);
	}
	@Override
	public boolean delete(String coverageID) {
		BenefitPayment coverage = this.get(coverageID);
		return coverages.remove(coverage);
	}
	@Override
	public BenefitPayment get(String coverageID) {
		for(BenefitPayment e : this.coverages) {
			if(e.getId().equals(coverageID)) return e;
		}
		return null;
	}
	@Override
	public ArrayList<BenefitPayment> getAll() {
		return coverages;
	}
	public void printAll() {
		coverages.forEach(i -> {
			System.out.println(i);
			System.out.println();
		});
	}
}
