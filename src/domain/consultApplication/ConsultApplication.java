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
	private String customerId;
	private String content; // ��� ���ϴ� ����
	private LocalDateTime applicationDate = LocalDateTime.now(); // ��� ��û�� ��¥
	private LocalDateTime consultationDate; // ��� ���ϴ� ��¥
	private ConsultApplicationState consultApplicationState;

	// constructor
	public ConsultApplication() {
		this.consultApplicationState = ConsultApplicationState.NEW;
	}
	
	// getters & setters
	public String getId() { return id; }
	public String getCustomerId() { return customerId; }
	public void setCustomerId(String customerId) { this.customerId = customerId; }
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	public LocalDateTime getApplicationDate() { return applicationDate; }
	public LocalDateTime getConsultationDate() { return consultationDate; }
	public void setConsultationDate(LocalDateTime consultationDate) { this.consultationDate = consultationDate; }
	public Enum<ConsultApplicationState> getState() { return consultApplicationState; }
	public void setState(ConsultApplicationState consultApplicationState) { this.consultApplicationState = consultApplicationState; }
	
	@Override
	public String toString() {		
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("��û �� ID: " + this.customerId).add("��� ����: " + this.content).add("��û��¥: " + this.applicationDate).add("��㳯¥: " + this.consultationDate).add("���� ����: " + this.consultApplicationState);
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