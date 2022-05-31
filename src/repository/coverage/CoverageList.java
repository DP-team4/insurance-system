package repository.coverage;

import java.util.ArrayList;

import domain.benefitPayment.BenefitPayment;

public interface CoverageList {
	boolean add(BenefitPayment coverage);
	boolean delete(String coverageID);
	BenefitPayment get(String coverageID);
//	boolean update();
	ArrayList<BenefitPayment> getAll();

}
