package domain.Customer;

import domain.Customer.CancelApplication.Cancellation;

/**
 * @author bigst
 * @version 1.0
 * @created 09-5-2022 오후 4:48:26
 */
public class Customer {

	private String accountNo;
	private int age;
	private String customerID = "Customer"+System.currentTimeMillis();
	private String customerName;
	private String email;
	private boolean gender;
	private boolean isMarried;
	private String password;
	private String phoneNo;
	private String registrationNo;
	public Cancellation m_Cancellation;
	
	//교수님 수업 임의 추가
	public int housePrice;

	private Customer(){
	}
	public Customer(String name, int age) {
		this.customerName=name; this.age=age;
	}
	public static Customer create(String name, int age) {
		return new Customer(name, age);
	}

	public String getCustomerID() {
		return customerID;
	}

	public void finalize() throws Throwable {

	}
	public void applyClaim(){

	}

	public void applyConsultation(){

	}

	public void applyCoverage(){

	}

	public void cancelContract(){

	}

	public void getCancellationList(){

	}

	public void getContracts(){

	}

	public void getCounselingList(){

	}

	public void getCoverage(){

	}

	public void getCoverageList(){

	}

	public void payPremium(){

	}

	public int getHousePrice() {
		return housePrice;
	}

	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}
	
}//end Customer