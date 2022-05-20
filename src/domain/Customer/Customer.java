package domain.Customer;

import java.util.StringJoiner;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 ���� 4:48:26
 */
public class Customer {

	private String id = "Customer" + System.currentTimeMillis();
	private String password;
	private String customerName;
	private int age;
	private boolean gender; // M: true, W: false
	private String registrationNo; // �ֹε�Ϲ�ȣ
	private String email;
	private String phoneNo;
	private String accountNo;
	// ���� ����� ���� �� ����
	private boolean isMarried;
	private long carPrice;
//	public ArrayList<CancelApplication> cancellations = new ArrayList<>(); // ��� ���� ��û ���
//	public ArrayList<Consultation> consultations = new ArrayList<>(); // ���� ��� ��û ���
//	public ArrayList<Claim> claims = new ArrayList<>(); // ����� û�� ���
//	public ArrayList<Coverage> coverages = new ArrayList<>(); // ���ó�� ��û ���
//	public ArrayList<Contract> contracts = new ArrayList<>(); // ���� ���� ���

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
//	// �ڽ��� �����ǰ�� �����Ѵ�
//	public ArrayList<Contract> getContracts(){ return this.contracts; }

	// functions //
//	// ������� û���Ѵ�
//	public void applyClaim(){
//		Claim claim = new Claim();
//		
//		claims.add(claim);
//	}
//
//	// ���� ��� ��û�� �Ѵ�
//	public void applyConsultation(){
//		Consultation consultation = new Consultation();
//		consultation.setContent("�Է��� ����");
//		LocalDateTime consultationDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
//		consultation.setConsultationDate(consultationDate);
//		consultation.setCustomerID(this.id);
//		LocalDateTime applicationDate = LocalDateTime.now();
//		consultation.setApplicationDate(applicationDate);
//		this.consultations.add(consultation);
//	}
//
//	// ���ó�� ��û�� �Ѵ�
//	public void applyCoverage(){
//		Coverage coverage = new Coverage();
//		
//		this.coverages.add(coverage);
//	}
//
//	// ��� ���� ��û�� �Ѵ�
//	public void cancelContract(){
//		CancelApplication cancellation = new CancelApplication();
//		cancellation.setInsuranceID(insuranceID);
//		cancellation.setResult();
//		this.cancellations.add(cancellation);
//	}
//	
//	// getInsurances ? ������� �����ǰ�� ��ȸ�Ѵ�
//
	// ����Ḧ �����Ѵ�
	public void payPremium(){
		// ����� ���
	}
	
	@Override
	public String toString() {		
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("PW: " + this.password).add("����ID: " + this.customerName).add("����: " + this.age)
		.add("����: " + this.gender).add("�ֹε�Ϲ�ȣ: " + this.registrationNo).add("�̸���: " + this.email).add("��ȭ��ȣ: " + this.phoneNo)
		.add("���¹�ȣ: " + this.accountNo).add("��ȥ����: " + this.isMarried).add("�� ��: " + this.carPrice).add("�� ��: " + this.housePrice);
//		StringJoiner arrayListSj = new StringJoiner(", ");
//		cancellations.forEach(d -> arrayListSj.add(d.getName()));
//		sj.add("����: " + arrayListSj.toString());
//		arrayListSj = new StringJoiner(", ");
//		consultations.forEach(d -> arrayListSj.add(d.getName()));
//		sj.add("����: " + arrayListSj.toString());
//		arrayListSj = new StringJoiner(", ");
//		claims.forEach(d -> arrayListSj.add(d.getName()));
//		sj.add("����: " + arrayListSj.toString());
//		arrayListSj = new StringJoiner(", ");
//		coverages.forEach(d -> arrayListSj.add(d.getName()));
//		sj.add("����: " + arrayListSj.toString());
//		arrayListSj = new StringJoiner(", ");
//		contracts.forEach(d -> arrayListSj.add(d.getName()));
//		sj.add("����: " + arrayListSj.toString());
		return sj.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Customer) {
			return ((Customer) obj).getId().equals(this.id);
		}
		return false;
	}
	
	// ������ ���� ���� �߰� //
	public long housePrice;
	public long getHousePrice() { return housePrice; }
	public void setHousePrice(long housePrice) { this.housePrice = housePrice; }
	
}//end Customer