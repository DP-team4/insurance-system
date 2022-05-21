package domain.consultApplication;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ���� 4:48:25
 */
public class ConsultApplication {

	private String id = "Consult" + System.currentTimeMillis();
	private String customerID;
	private String content; // ��� ���ϴ� ����
	private LocalDateTime applicationDate = LocalDateTime.now(); // ��� ��û�� ��¥
	private LocalDateTime consultationDate; // ��� ���ϴ� ��¥
	private Enum<EState> state;

	private enum EState{NEW, ACCEPTED, REJECTED, COMPLETED};
	// ���� ���� ���� - ��û���, ��û����, ��û����, ���Ϸ�

	// constructor
	public ConsultApplication() {
	}
	public ConsultApplication(String customerID, String content, LocalDateTime consultationDate){
		this.customerID = customerID;
		this.content = content;
		this.consultationDate = consultationDate;
		this.state = EState.NEW;
	}
	
	// getters & setters
	public String getId() { return id; }
	public String getCustomerID() { return customerID; }
	public void setCustomerID(String customerID) { this.customerID = customerID; }
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	public LocalDateTime getApplicationDate() { return applicationDate; }
	public void setApplicationDate(LocalDateTime applicationDate) { this.applicationDate = applicationDate; }
	public LocalDateTime getConsultationDate() { return consultationDate; }
	public void setConsultationDate(LocalDateTime consultationDate) { this.consultationDate = consultationDate; }
	public Enum<EState> getState() { return state; }

	public void accept() {
		this.state = EState.ACCEPTED;
	}
	public void reject() {
		this.state = EState.REJECTED;
	}
	public void complete() {
		this.state = EState.COMPLETED;
	}
	
	@Override
	public String toString() {		
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("��� ����: " + this.content).add("��û��¥: " + this.applicationDate).add("��㳯¥: " + this.consultationDate).add("���� ����: " + this.state);
		return sj.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ConsultApplication) {
			return ((ConsultApplication) obj).getId().equals(this.id);
		}
		return false;
	}
	
}//end ConsultApplication