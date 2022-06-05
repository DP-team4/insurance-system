package domain.consultApplication;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ���� 4:48:25
 */
public class ConsultApplication {

	private String id;
	private String customerId;
	private String content; // ��� ���ϴ� ����
	private LocalDateTime applicationDate = LocalDateTime.now(); // ��� ��û�� ��¥
	private LocalDateTime consultationDate; // ��� ���ϴ� ��¥
	private ConsultApplicationState state;

	// constructor
	public ConsultApplication() {
		this.state = ConsultApplicationState.NEW;
	}
	
	// getters & setters
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getCustomerId() { return customerId; }
	public void setCustomerId(String customerId) { this.customerId = customerId; }
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	public LocalDateTime getApplicationDate() { return applicationDate; }
	public void setApplicationDate(LocalDateTime applicationDate) { this.applicationDate = applicationDate; }
	public LocalDateTime getConsultationDate() { return consultationDate; }
	public void setConsultationDate(LocalDateTime consultationDate) { this.consultationDate = consultationDate; }
	public Enum<ConsultApplicationState> getState() { return state; }
	public void setState(ConsultApplicationState state) { this.state = state; }
	
	@Override
	public String toString() {		
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("�� ID: " + this.customerId).add("��� ����: " + this.content).add("��û��¥: " + this.applicationDate).add("��㳯¥: " + this.consultationDate).add("���� ����: " + this.state);
		return sj.toString();
	}
	
	public String toStringForCustomer() {		
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