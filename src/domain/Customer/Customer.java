package domain.Customer;

import java.util.StringJoiner;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 오후 4:48:26
 */
public class Customer {

	private String id = "Customer" + System.currentTimeMillis();
	private String password;
	private String customerName;
	private int age;
	private boolean gender; // M: true, W: false
	private String registrationNo; // 주민등록번호
	private String email;
	private String phoneNo;
	private String accountNo;
	// 요율 계산을 위한 고객 정보
	private boolean isMarried;
	private long carPrice;
//	public ArrayList<CancelApplication> cancellations = new ArrayList<>(); // 계약 해지 신청 목록
//	public ArrayList<Consultation> consultations = new ArrayList<>(); // 가입 상담 신청 목록
//	public ArrayList<Claim> claims = new ArrayList<>(); // 보험금 청구 목록
//	public ArrayList<Coverage> coverages = new ArrayList<>(); // 사고처리 신청 목록
//	public ArrayList<Contract> contracts = new ArrayList<>(); // 가입 보험 목록

	public Customer() { }
	
	public Customer(String customerName, int age) {
		this.customerName = customerName; this.age = age;
	}

	// getters & setters //
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getCustomerName() { return customerName; }
	public void setCustomerName(String customerName) { this.customerName = customerName; }
	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }
	public boolean getGender() { return gender; }
	public void setGender(boolean gender) { this.gender = gender; }
	public String getRegistrationNo() { return registrationNo; }
	public void setRegistrationNo(String registrationNo) { this.registrationNo = registrationNo; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getPhoneNo() { return phoneNo; }
	public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }
	public String getAccountNo() { return accountNo; }
	public void setAccountNo(String accountNo) { this.accountNo = accountNo; }
	public boolean isMarried() { return isMarried; }
	public void setMarried(boolean isMarried) { this.isMarried = isMarried; }
	public long getCarPrice() { return carPrice; }
	public void setCarPrice(long carPrice) { this.carPrice = carPrice; }
//	public ArrayList<CancelApplication> getCancellations(){ return this.cancellations; }
//	public ArrayList<Consultation> getConsultations(){ return this.consultations; }
//	public ArrayList<Coverage> getCoverages(){ return this.coverages; }
//	public ArrayList<Claim> getClaims(){ return this.claims; }
//	// 자신의 보험상품을 관리한다
//	public ArrayList<Contract> getContracts(){ return this.contracts; }

	// functions //
//	// 보험금을 청구한다
//	public void applyClaim(){
//		Claim claim = new Claim();
//		
//		claims.add(claim);
//	}
//
//	// 가입 상담 신청을 한다
//	public void applyConsultation(){
//		Consultation consultation = new Consultation();
//		consultation.setContent("입력한 내용");
//		LocalDateTime consultationDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
//		consultation.setConsultationDate(consultationDate);
//		consultation.setCustomerID(this.id);
//		LocalDateTime applicationDate = LocalDateTime.now();
//		consultation.setApplicationDate(applicationDate);
//		this.consultations.add(consultation);
//	}
//
//	// 사고처리 신청을 한다
//	public void applyCoverage(){
//		Coverage coverage = new Coverage();
//		
//		this.coverages.add(coverage);
//	}
//
//	// 계약 해지 신청을 한다
//	public void cancelContract(){
//		CancelApplication cancellation = new CancelApplication();
//		cancellation.setInsuranceID(insuranceID);
//		cancellation.setResult();
//		this.cancellations.add(cancellation);
//	}
//	
//	// getInsurances ? 보험사의 보험상품을 조회한다
//
	// 보험료를 납부한다
	public void payPremium(){
		// 보험료 계산
	}
	
	@Override
	public String toString() {		
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("PW: " + this.password).add("보험ID: " + this.customerName).add("나이: " + this.age)
		.add("성별: " + this.gender).add("주민등록번호: " + this.registrationNo).add("이메일: " + this.email).add("전화번호: " + this.phoneNo)
		.add("계좌번호: " + this.accountNo).add("결혼여부: " + this.isMarried).add("차 값: " + this.carPrice).add("집 값: " + this.housePrice);
//		StringJoiner arrayListSj = new StringJoiner(", ");
//		cancellations.forEach(d -> arrayListSj.add(d.getName()));
//		sj.add("서류: " + arrayListSj.toString());
//		arrayListSj = new StringJoiner(", ");
//		consultations.forEach(d -> arrayListSj.add(d.getName()));
//		sj.add("서류: " + arrayListSj.toString());
//		arrayListSj = new StringJoiner(", ");
//		claims.forEach(d -> arrayListSj.add(d.getName()));
//		sj.add("서류: " + arrayListSj.toString());
//		arrayListSj = new StringJoiner(", ");
//		coverages.forEach(d -> arrayListSj.add(d.getName()));
//		sj.add("서류: " + arrayListSj.toString());
//		arrayListSj = new StringJoiner(", ");
//		contracts.forEach(d -> arrayListSj.add(d.getName()));
//		sj.add("서류: " + arrayListSj.toString());
		return sj.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Customer) {
			return ((Customer) obj).getId().equals(this.id);
		}
		return false;
	}
	
	// 교수님 수업 임의 추가 //
	public long housePrice;
	public long getHousePrice() { return housePrice; }
	public void setHousePrice(long housePrice) { this.housePrice = housePrice; }
	
}//end Customer