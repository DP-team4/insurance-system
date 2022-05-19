package domain.consultApplication;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 오후 4:48:25
 */
public class ConsultApplication {

	private String id = "Consult" + System.currentTimeMillis();
	// private String customerID;
	private String content; // 상담 원하는 내용
	private LocalDateTime applicationDate = LocalDateTime.now(); // 상담 신청한 날짜
	private LocalDateTime consultationDate; // 상담 원하는 날짜
	private Enum<EState> state;
	
	private enum EState{eWait, eAccept, eReject, eComplete};
	// 현재 진행 상태 - 요청대기, 요청수락, 요청거절, 상담완료

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