package domain.coverage;

public class FireCoverage extends Coverage{
	private double benefitOtherExpense;
	
	@Override
	public void createCoverage() {
		this.benefitOtherExpense = 10;
	}
	
	@Override
	public double getTotalBenefit() {
		return this.benefitOtherExpense;
	}
}
