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
	private Contract contract; // ��� ���� �� Contract
	private LocalDateTime applicationDate = LocalDateTime.now();
	private Enum<EState> state;
	
	private enum EState{NEW, ACCEPTED, REJECTED}; // ��û���, ��û����, ��û����

	// constructor
	public CancelApplication(){
		this.state = EState.NEW;
	}
	
	public CancelApplication(Contract contract){
		this.state = EState.NEW;
	}
	
	// getters & setters
	public String getId() { return id; }
	public Contract getContract() { return contract; }
	public void setContract(Contract contract) { this.contract = contract; }
	public LocalDateTime getApplicationDate() { return applicationDate; }
	public Enum<EState> getState() { return state; }

	public void accept() {
		this.state = EState.ACCEPTED;
	}
	public void reject() {
		this.state = EState.REJECTED;
	}
	
	@Override
	public String toString() {		
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("������ ���ID: " + this.contract.getId()).add("��û��¥: " + this.applicationDate).add("���� ����: " + this.state);
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