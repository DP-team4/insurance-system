package domain.coverage;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public abstract class Coverage {
	private String id;
	private String appliedCustomerId; //�뺸��(������) = �Ǻ����ڷ� ����
	private CoverageState state = CoverageState.ONPROGRESS;
	private enum CoverageState {
		ONPROGRESS, UNDERPAYING, PAID, REFUSED 
		//���ο� int�� �־ ���忹��
	}
//	private double benefitClaim;
	private String accidentCause;
	private LocalDateTime accidentDate;
	private LocalDateTime applianceDate;
	
	/*
	 * ������ ������ �ߺ����� ���ִ� ��ʰ� ���� �����Ƿ� ������ ���� ū �͸� �������ش�.
	 * customerId�� �������� ã�� ��, �ش� ���� ������ �����ͼ� ���� �� ��, ���� ū ���� ���Ͽ� ���� ū ���� �����ش�.
	 *  => �̷��� ���� ������ ����߰ڴ�.  
	 * �׸���, ����������� ����
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
		sj.add("ID: " + this.id).add("������ID: " + this.appliedCustomerId).add("ó����Ȳ: " + this.state.toString())
		.add("���/�ߺ�����: " + this.accidentDate.toString()).add("��������: " + this.applianceDate.toString())
		.add("�� ���� �����: "+this.getTotalBenefit());
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
