package domain.carAccidentHandling;

import domain.benefitPayment.EBenefitPaymentState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.StringJoiner;

public class CarAccidentHandling {
	private String id;
	private String contractId;
	private LocalDateTime requestDate;
	private LocalDateTime accidentDate;
	private String accidentContent;
	private String accidentLocation;
	private ArrayList<AccidentCar> accidentCars;
	private ArrayList<AccidentPerson> accidentPeople;
	private EBenefitPaymentState state;

	//GTRSTR
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public LocalDateTime getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}
	public LocalDateTime getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(LocalDateTime accidentDate) {
		this.accidentDate = accidentDate;
	}
	public String getAccidentContent() {
		return accidentContent;
	}
	public void setAccidentContent(String accidentContent) {
		this.accidentContent = accidentContent;
	}
	public String getAccidentLocation() {
		return accidentLocation;
	}
	public void setAccidentLocation(String accidentLocation) {
		this.accidentLocation = accidentLocation;
	}
	public EBenefitPaymentState getState() {
		return state;
	}
	public void setState(EBenefitPaymentState state) {
		this.state = state;
	}
	public ArrayList<AccidentCar> getAccidentCars() {
		return accidentCars;
	}
	public void setAccidentCars(ArrayList<AccidentCar> accidentCars) {
		this.accidentCars = accidentCars;
	}
	public ArrayList<AccidentPerson> getAccidentPeople() {
		return accidentPeople;
	}
	public void setAccidentPeople(ArrayList<AccidentPerson> accidentPeople) {
		this.accidentPeople = accidentPeople;
	}
}
