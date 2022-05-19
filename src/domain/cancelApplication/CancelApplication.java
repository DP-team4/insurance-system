package domain.cancelApplication;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ���� 4:48:25
 */
public class CancelApplication {

	private String id = "Cancel" + System.currentTimeMillis();
	private String insuranceID;
	private LocalDateTime applicationDate = LocalDateTime.now();
	private Enum<EState> state;
	
	private enum EState{eWait, eAccept, eReject}; // ��û���, ��û����, ��û����

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
		sj.add("ID: " + this.id).add("����ID: " + this.insuranceID).add("��û��¥: " + this.applicationDate).add("���� ����: " + this.state);
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