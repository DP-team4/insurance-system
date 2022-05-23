package domain.coverage;

public class CarCoverage extends Coverage{
//	private double benefitClaim;
	private double benefitMedicalExpense;
	private double benefitRepairExpense;

	@Override
	public void createCoverage() {
		this.benefitMedicalExpense = 10;
		this.benefitRepairExpense = 100;
	}
	
	@Override
	public double getTotalBenefit() {
		return Double.sum(this.benefitMedicalExpense,this.benefitRepairExpense);
	}
}
