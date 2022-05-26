package domain.consultApplication;

import java.time.LocalDateTime;
import java.util.StringJoiner;

import domain.customer.Customer;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ���� 4:48:25
 */
public class ConsultApplication {

	private String id = "Consult" + System.currentTimeMillis();
	private Customer customer;
	private String content; // ��� ���ϴ� ����
	private LocalDateTime applicationDate = LocalDateTime.now(); // ��� ��û�� ��¥
	private LocalDateTime consultationDate; // ��� ���ϴ� ��¥
	private Enum<ConsultApplicationState> state;

	// constructor
	public ConsultApplication() {
	}
	
	public ConsultApplication(Customer customer, String content, LocalDateTime consultationDate){
		this.customer = customer;
		this.content = content;
		this.consultationDate = consultationDate;
		this.state = ConsultApplicationState.NEW;
	}
	
	// getters & setters
	public String getId() { return id; }
	public Customer getCustomer() { return customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	public LocalDateTime getApplicationDate() { return applicationDate; }
	public void setApplicationDate(LocalDateTime applicationDate) { this.applicationDate = applicationDate; }
	public LocalDateTime getConsultationDate() { return consultationDate; }
	public void setConsultationDate(LocalDateTime consultationDate) { this.consultationDate = consultationDate; }
	public Enum<ConsultApplicationState> getState() { return state; }
	public void setState(Enum<ConsultApplicationState> state) { this.state = state; }
	
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