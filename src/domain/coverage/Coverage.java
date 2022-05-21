package domain.coverage;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Coverage {
	private String id;
	private String appliedCustomerId;
	private CoverageType type;
	private enum CoverageType {
		CARACCIDENT, NONLIFE, 
	}
	private CoverageState state = CoverageState.ONPROGRESS;
	private enum CoverageState {
		ONPROGRESS, UNDERPAYING, PAID, REFUSED 
		//내부에 int를 넣어서 저장예정
	}
//	private double benefitClaim;
	private double benefitMedicalExpense;
	private double benefitRepairExpense;
	private double benefitOtherExpense;
	private LocalDateTime accidentDate;
	private LocalDateTime coverageDate;
	
	public void createCarCoverage() {
		this.type = CoverageType.CARACCIDENT;
		this.benefitMedicalExpense = 10;
		this.benefitRepairExpense = 100;
	}
	
	public void createNonLifeCoverage() {
		this.type = CoverageType.NONLIFE;
		this.benefitOtherExpense = 90;
	}
	
	public double getTotalBenefit() {
		return Double.sum(Double.sum(this.benefitMedicalExpense,this.benefitRepairExpense), this.benefitOtherExpense);
	}
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("접수고객ID: " + this.appliedCustomerId).add("처리현황: " + this.state.toString()).add("사고/발병일자: " + this.accidentDate.toString()).add("접수일자: " + this.coverageDate.toString());
		return sj.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Coverage) && ((Coverage)obj).getId().equals(this.getId()); 
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppliedCustomerId() {
		return appliedCustomerId;
	}

	public void setAppliedCustomerId(String appliedCustomerId) {
		this.appliedCustomerId = appliedCustomerId;
	}

	public CoverageType getType() {
		return type;
	}

	public void setType(CoverageType type) {
		this.type = type;
	}

	public CoverageState getState() {
		return state;
	}

	public void setState(CoverageState state) {
		this.state = state;
	}

	public double getBenefitMedicalExpense() {
		return benefitMedicalExpense;
	}

	public void setBenefitMedicalExpense(double benefitMedicalExpense) {
		this.benefitMedicalExpense = benefitMedicalExpense;
	}

	public double getBenefitRepairExpense() {
		return benefitRepairExpense;
	}

	public void setBenefitRepairExpense(double benefitRepairExpense) {
		this.benefitRepairExpense = benefitRepairExpense;
	}

	public double getBenefitOtherExpense() {
		return benefitOtherExpense;
	}

	public void setBenefitOtherExpense(double benefitOtherExpense) {
		this.benefitOtherExpense = benefitOtherExpense;
	}

	public LocalDateTime getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(LocalDateTime accidentDate) {
		this.accidentDate = accidentDate;
	}

	public LocalDateTime getCoverageDate() {
		return coverageDate;
	}

	public void setCoverageDate(LocalDateTime coverageDate) {
		this.coverageDate = coverageDate;
	}
}
