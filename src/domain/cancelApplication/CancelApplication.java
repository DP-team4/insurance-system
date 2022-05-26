package domain.cancelApplication;

import java.time.LocalDateTime;
import java.util.StringJoiner;

import domain.contract.Contract;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 오후 4:48:25
 */
public class CancelApplication {

	private String id = "Cancel" + System.currentTimeMillis();
	private Contract contract; // 계약 해지 할 Contract
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
	public Contract getContract() { return contract; }
	public void setContract(Contract contract) { this.contract = contract; }
	public LocalDateTime getApplicationDate() { return applicationDate; }
	public Enum<CancelApplicationState> getState() { return state; }
	public void setState(Enum<CancelApplicationState> state) { this.state = state; }
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("해지할 계약ID: " + this.contract.getId()).add("신청날짜: " + this.applicationDate).add("진행 상태: " + this.state);
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