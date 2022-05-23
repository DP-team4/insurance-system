package domain.contract;

import java.time.LocalDateTime;
import java.util.StringJoiner;
import domain.customer.Customer;
import domain.insurance.Insurance;

public class Contract {
	private String id = "Contract" + System.currentTimeMillis();
	private LocalDateTime contractDateTime;
	private LocalDateTime expirationDateTime;
	private String customerId;
	private String insuranceId;
	private ContractState contractState;
	
	public Contract() {}
	public Contract(Customer customer, Insurance insurance, LocalDateTime contractDateTime, LocalDateTime expirationDateTime) {
		this.customerId = customer.getId();
		this.insuranceId = insurance.getId();
		this.contractDateTime = contractDateTime;
		this.expirationDateTime = expirationDateTime;
		this.setContractState(ContractState.NEW);
	}
	
	public void accept() {
		this.setContractState(ContractState.ACCEPTED);
	}
	
	public void reject() {
		this.setContractState(ContractState.REJECTED);
	}
	
	public void requestUW() {
		this.setContractState(ContractState.UNDER_UW);
	}
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("고객ID: " + this.customerId).add("보험ID: " + this.insuranceId).add("계약일: " + this.contractDateTime.toString()).add("만기일: " + this.expirationDateTime.toString());
		return sj.toString();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Contract) {
			return ((Contract) obj).getId().equals(this.id);
		}
		return false;
	}
	
	// getters and setters
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public LocalDateTime getContractDateTime() {return contractDateTime;}
	public void setContractDateTime(LocalDateTime contractDateTime) {this.contractDateTime = contractDateTime;}
	public LocalDateTime getExpirationDateTime() {return expirationDateTime;}
	public void setExpirationDateTime(LocalDateTime expirationDateTime) {this.expirationDateTime = expirationDateTime;}
	public String getCustomerId() {return customerId;}
	public void setCustomerId(String customerId) {this.customerId = customerId;}
	public String getInsuranceId() {return insuranceId;}
	public void setInsuranceId(String insuranceId) {this.insuranceId = insuranceId;}
	public ContractState getContractState() {return contractState;}
	public void setContractState(ContractState contractState) {this.contractState = contractState;}
	
}
