package domain.customer;

import java.util.StringJoiner;

/**
 * << 고객 관련 USECASE >>
 * 보험금을 청구한다 applyClaim
 * 가입 상담 신청을 한다  applyConsultation
 * 사고처리 신청을 한다 applyCoverage
 * 계약 해지 신청을 한다 cancelContract
 * 보험사의 보험상품을 조회한다 ??Insurances
 * 자신의 보험상품을 관리한다 manageContracts
 * 보험료를 납부한다 payPremium
 * 
 * @author bigst
 *
 */

public class Customer {

	private String id = "Customer" + System.currentTimeMillis();
	private String customerName;
	private int age;
	private boolean gender; // M: true, W: false
	private String registrationNo; // 주민등록번호
	private String phoneNo;
	private String email;
	private String accountNo;
	private boolean isMarried;
	private AdditionalInfo additionalInfo;

	public Customer() {
		this.additionalInfo = new AdditionalInfo();
	}

	// getters & setters //
	public String getId() { return id; }
	public String getCustomerName() { return customerName; }
	public void setCustomerName(String customerName) { this.customerName = customerName; }
	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }
	public boolean getGender() { return gender; }
	public void setGender(boolean gender) { this.gender = gender; }
	public String getRegistrationNo() { return registrationNo; }
	public void setRegistrationNo(String registrationNo) { this.registrationNo = registrationNo; }
	public String getPhoneNo() { return phoneNo; }
	public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getAccountNo() { return accountNo; }
	public void setAccountNo(String accountNo) { this.accountNo = accountNo; }
	public boolean isMarried() { return this.isMarried; }
	public void setMarried(boolean isMarried) { this.isMarried = isMarried; }
	public AdditionalInfo getAdditionalInfo() { return additionalInfo; }
	public void setAdditionalInfo(AdditionalInfo additionalInfo) { this.additionalInfo = additionalInfo; }
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("보험ID: " + this.customerName).add("나이: " + this.age)
		.add("성별: " + (this.gender ? "남성" : "여성")).add("주민등록번호: " + this.registrationNo).add("이메일: " + this.email).add("전화번호: " + this.phoneNo)
		.add("계좌번호: " + this.accountNo).add("결혼여부: " + this.isMarried).add(this.additionalInfo.toString());
		return sj.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Customer) {
			return ((Customer) obj).getId().equals(this.id);
		}
		return false;
	}

	
}//end Customer