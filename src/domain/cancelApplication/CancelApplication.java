package domain.cancelApplication;

import java.time.LocalDateTime;
import java.util.StringJoiner;

import domain.contract.Contract;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ���� 4:48:25
 */
public class CancelApplication {

	private String id = "Cancel" + System.currentTimeMillis();
	private String contractID; // ��� ���� �� Contract
	private LocalDateTime applicationDate = LocalDateTime.now();
	private Enum<CancelApplicationState> state;

	// constructor
	public CancelApplication(){
		this.state = CancelApplicationState.NEW;
	}
	
	public CancelApplication(Contract contract){
		this.state = CancelApplicationState.NEW;
	}
	
	// getters & setters
	public String getId() { return id; }
	public String getContract() { return contractID; }
	public void setContract(String contractID) { this.contractID = contractID; }
	public LocalDateTime getApplicationDate() { return applicationDate; }
	public Enum<CancelApplicationState> getState() { return state; }
	public void setState(Enum<CancelApplicationState> state) { this.state = state; }
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("������ ��� ID: " + this.contractID).add("��û��¥: " + this.applicationDate).add("���� ����: " + this.state);
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