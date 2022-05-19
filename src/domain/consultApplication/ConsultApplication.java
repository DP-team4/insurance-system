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
	// private String customerID;
	private String content; // ��� ���ϴ� ����
	private LocalDateTime applicationDate = LocalDateTime.now(); // ��� ��û�� ��¥
	private LocalDateTime consultationDate; // ��� ���ϴ� ��¥
	private Enum<EState> state;
	
	private enum EState{eWait, eAccept, eReject, eComplete};
	// ���� ���� ���� - ��û���, ��û����, ��û����, ���Ϸ�

	// constructor
	public ConsultApplication(){
		this.state = EState.eWait;
	}
	
	// getters & setters
	public LocalDateTime getApplicationDate() { return applicationDate; }
	public void setApplicationDate(LocalDateTime applicationDate) { this.applicationDate = applicationDate; }
	public LocalDateTime getConsultationDate() { return consultationDate; }
	public void setConsultationDate(LocalDateTime consultationDate) { this.consultationDate = consultationDate; }
	public String getId() { return id; }
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
//	public String getCustomerID() { return customerID; }
//	public void setCustomerID(String customerID) { this.customerID = customerID; }
	public Enum<EState> getState() { return state; }
	public void changeState(Enum<EState> state){ this.state = state; }
	
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