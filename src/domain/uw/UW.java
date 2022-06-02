package domain.uw;

import domain.insurance.Insurance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.StringJoiner;

public class UW {
	private String id = "UW"+System.currentTimeMillis();
	private UWState uwState;
	private ArrayList<UWDocument> documents = new ArrayList<>();
	private LocalDateTime requestDateTime = LocalDateTime.now();
	private String contractId;

	public UW() {
		this.uwState = UWState.UNDEFINED;
	}

	public void addDocument(UWDocument uwDocument) {
		this.documents.add(uwDocument);
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("계약 ID: " + this.contractId).add("상태: " + this.uwState).add("요청일: " + this.requestDateTime.toString());
		StringJoiner documentSj = new StringJoiner(", ");
		documents.forEach(d -> documentSj.add(d.getName()));
		sj.add("서류: " + documentSj);
		return sj.toString();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UW) {
			return ((UW) obj).getId().equals(this.id);
		}
		return false;
	}
	public boolean equalsAttributes(Object obj) {
		if(obj instanceof UW other &&
				other.id.equals(this.id) &&
				other.requestDateTime.equals(this.requestDateTime) &&
				other.uwState.equals(this.uwState) &&
				other.contractId.equals(this.contractId)
		) return true;
		else return false;
	}

	// getters setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UWState getUwState() {
		return uwState;
	}
	public void setUwState(UWState uwState) {
		this.uwState = uwState;
	}
	public ArrayList<UWDocument> getDocuments() {
		return documents;
	}
	public void setDocuments(ArrayList<UWDocument> documents) {
		this.documents = documents;
	}
	public LocalDateTime getRequestDateTime() {
		return requestDateTime;
	}
	public void setRequestDateTime(LocalDateTime requestDateTime) {
		this.requestDateTime = requestDateTime;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
}