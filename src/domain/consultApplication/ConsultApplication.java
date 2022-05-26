package domain.consultApplication;

import java.time.LocalDateTime;
import java.util.StringJoiner;

import domain.customer.Customer;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 오후 4:48:25
 */
public class ConsultApplication {

	private String id = "Consult" + System.currentTimeMillis();
	private Customer customer;
	private String content; // 상담 원하는 내용
	private LocalDateTime applicationDate = LocalDateTime.now(); // 상담 신청한 날짜
	private LocalDateTime consultationDate; // 상담 원하는 날짜
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
		sj.add("ID: " + this.id).add("상담 내용: " + this.content).add("신청날짜: " + this.applicationDate).add("상담날짜: " + this.consultationDate).add("진행 상태: " + this.state);
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