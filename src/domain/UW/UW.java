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

	public UW() {}

	public void accept(){
		this.uwState = UWState.ACCEPTED;
	}
	public void addDocument(String name, String path) {
		UWDocument submitted = new UWDocument();
		submitted.setName(name);
		submitted.setPath(path);
		submitted.setUwDocumentState(UWDocumentState.SUBMITTED);
		this.documents.add(submitted);
	}
	public void reject(){
		this.uwState = UWState.REJECTED;
	}
	public void requestDocument(String name){
		UWDocument requested = new UWDocument();
		requested.setName(name);
		requested.setUwDocumentState(UWDocumentState.REQUESTED);
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

	// getters setters
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
	public void setId(String id) {
		this.id = id;
	}
	public void setUwState(UWState uwState) {
		this.uwState = uwState;
	}
	public void setDocuments(ArrayList<UWDocument> documents) {
		this.documents = documents;
	}
	public void setRequestDateTime(LocalDateTime requestDateTime) {
		this.requestDateTime = requestDateTime;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}
}