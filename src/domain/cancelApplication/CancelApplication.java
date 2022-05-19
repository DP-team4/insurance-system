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
	private String insuranceID;
	private LocalDateTime applicationDate = LocalDateTime.now();
	private Enum<EState> state;
	
	private enum EState{eWait, eAccept, eReject}; // 요청대기, 요청수락, 요청거절

	// constructor
	public CancelApplication(){
		this.state = EState.eWait;
	}
	
	// getters & setters
	public String getId() { return id; }
	public String getInsuranceID() { return insuranceID; }
	public void setInsuranceID(String insuranceID) { this.insuranceID = insuranceID; }
	public LocalDateTime getApplicationDate() { return applicationDate; }
	public Enum<EState> getState() { return state; }
	public void changeState(Enum<EState> state){ this.state = state; }
	
	@Override
	public String toString() {		
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("보험ID: " + this.insuranceID).add("신청날짜: " + this.applicationDate).add("진행 상태: " + this.state);
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