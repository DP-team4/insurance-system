package domain.coverage;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public abstract class Coverage {
	private String id;
	private String appliedCustomerId; //통보자(접수자) = 피보험자로 간주
	private CoverageState state = CoverageState.ONPROGRESS;
	private enum CoverageState {
		ONPROGRESS, UNDERPAYING, PAID, REFUSED 
		//내부에 int를 넣어서 저장예정
	}
//	private double benefitClaim;
	private String accidentCause;
	private LocalDateTime accidentDate;
	private LocalDateTime applianceDate;
	
	/*
	 * 어차피 보험은 중복보장 해주는 사례가 거의 없으므로 요율이 제일 큰 것만 보장해준다.
	 * customerId로 접수고객을 찾은 뒤, 해당 고객의 보험을 가져와서 요율 비교 후, 가장 큰 것을 비교하여 제일 큰 것을 비교해준다.
	 *  => 이러면 보험 종류와 맞춰야겠다.  
	 * 그리고, 보장란에서는 보험
	 */
	public abstract void createCoverage();
	public abstract double getTotalBenefit();

	public CoverageState getState() {
		return state;
	}

	public void setState(CoverageState state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("접수고객ID: " + this.appliedCustomerId).add("처리현황: " + this.state.toString())
		.add("사고/발병일자: " + this.accidentDate.toString()).add("접수일자: " + this.applianceDate.toString())
		.add("총 예상 보장액: "+this.getTotalBenefit());
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

	public String getAccidentCause() {
		return accidentCause;
	}
	public void setAccidentCause(String accidentCause) {
		this.accidentCause = accidentCause;
	}
	public LocalDateTime getApplianceDate() {
		return applianceDate;
	}
	public void setApplianceDate(LocalDateTime applianceDate) {
		this.applianceDate = applianceDate;
	}
	public LocalDateTime getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(LocalDateTime accidentDate) {
		this.accidentDate = accidentDate;
	}

	public LocalDateTime getCoverageDate() {
		return applianceDate;
	}

	public void setCoverageDate(LocalDateTime coverageDate) {
		this.applianceDate = coverageDate;
	}
}
