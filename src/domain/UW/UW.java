package domain.UW;

import domain.Customer.Customer;
import domain.Insurance.Insurance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * @author wls43
 * @version 1.0
 * @created 09-5-2022 오후 4:48:26
 */
public class UW {
	private String id = "UW"+System.currentTimeMillis();
	private UWState uwState;
	private ArrayList<UWDocument> documents = new ArrayList<>();
	private LocalDateTime requestDateTime = LocalDateTime.now();
	private String customerID;
	private String insuranceID;

	private UW() {}
	private UW(Customer customer, Insurance insurance, UWState uwState,  LocalDateTime dateTime) {
		this.customerID = customer.getCustomerID(); this.insuranceID = insurance.getId();
		this.requestDateTime = dateTime; this.uwState = uwState;
	}
	private UW(Customer customer, Insurance insurance, UWState uwState) {
		this.customerID = customer.getCustomerID(); this.insuranceID = insurance.getId(); this.uwState = uwState;
	}

	public static UW create(Customer customer, Insurance insurance) {
		return new UW(customer, insurance, UWState.NEW);
	}

	public void accept(){
		this.uwState = UWState.ACCEPTED;
	}
	public void addDocument(String name, String path) {
		UWDocument submitted = UWDocument.createSubmitted(name, path);
		this.documents.add(submitted);
	}
	public void reject(){
		this.uwState = UWState.REJECTED;
	}
	public void requestDocument(String name){
		UWDocument requested = UWDocument.createRequested(name);
		this.documents.add(requested);
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("고객ID: " + this.customerID).add("보험ID: " + this.insuranceID).add("상태: " + this.uwState).add("요청일: " + this.requestDateTime.toString());
		StringJoiner documentSj = new StringJoiner(", ");
		documents.forEach(d -> documentSj.add(d.getName()));
		sj.add("서류: " + documentSj.toString());
		return sj.toString();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UW) {
			return ((UW) obj).getId().equals(this.id);
		}
		return false;
	}

	// getters
	public String getCustomerID() {
		return customerID;
	}
	public ArrayList<UWDocument> getDocuments() {
		return documents;
	}
	public String getInsuranceID() {
		return insuranceID;
	}
	public LocalDateTime getRequestDateTime() {
		return requestDateTime;
	}
	public String getId() {
		return id;
	}
	public UWState getUwState() {
		return uwState;
	}
}