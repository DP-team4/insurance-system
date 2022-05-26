package domain.cancelApplication;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 오후 4:48:25
 */
public class CancelApplication {

	private String id = "Cancel" + System.currentTimeMillis();
	private String contractId; // 계약 해지 할 Contract
	private LocalDateTime applicationDate = LocalDateTime.now();
	private CancelApplicationState cancelApplicationState;

	// constructor
	public CancelApplication(){
		this.cancelApplicationState = CancelApplicationState.NEW;
	}
	
	// getters & setters
	public String getId() { return id; }
	public String getContractId() { return contractId; }
	public void setContractId(String contractId) { this.contractId = contractId; }
	public LocalDateTime getApplicationDate() { return applicationDate; }
	public CancelApplicationState getState() { return cancelApplicationState; }
	public void setState(CancelApplicationState cancelApplicationState) { this.cancelApplicationState = cancelApplicationState; }
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("해지할 계약 ID: " + this.contractId).add("신청날짜: " + this.applicationDate).add("진행 상태: " + this.cancelApplicationState);
		return sj.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CancelApplication) {
			return ((CancelApplication) obj).getId().equals(this.id);
		}
		return false;
	}

}//end Cancellation