package domain.carAccidentHandling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.StringJoiner;

public class CarAccidentHandling {
	private String id =  "Claim"+System.currentTimeMillis();
//	private String insuranceID;
	private String appliedCustomerId; //������ �����ڰ� �ٸ� �� ������, ���⼱ ������ �Ѵٴ� ����.
	private String accidentCause; //������
	private String accidentLocation;
	private LocalDateTime accidentDate;
	private LocalDateTime claimDate;
//	private  AccidentType accidentType;
	private boolean isConfirmed = false;
	private ArrayList<AccidentCar> carInfos = new ArrayList<>();
	private ArrayList<AccidentPerson> peopleInfos = new ArrayList<>();
	
	public void confirm() {
		if(this.validate())
			this.claimDate = LocalDateTime.now();
			this.isConfirmed = true;
	}

	private boolean validate() {
		if(this.id.isEmpty() || this.id == null) return false;
		if(this.appliedCustomerId.isEmpty() || this.appliedCustomerId == null) return false;
		if(this.appliedCustomerId.isEmpty() || this.appliedCustomerId == null) return false;
		if(this.accidentDate == null || this.claimDate == null) return false;
		if(this.carInfos.isEmpty() || this.peopleInfos.isEmpty()) return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("������ID: " + this.appliedCustomerId).add("ó������: " + this.isConfirmed).add("�������: " + this.accidentDate.toString()).add("��������: " + this.claimDate.toString());
		StringJoiner carInfoSj = new StringJoiner(", ");
		this.carInfos.forEach(d -> carInfoSj.add(d.getCarNum()));
		StringJoiner peopleInfoSj = new StringJoiner(", ");
		this.peopleInfos.forEach(d -> peopleInfoSj.add(d.getName()));
		sj.add("���ػ���: " + carInfoSj.toString()+peopleInfoSj.toString());
		return sj.toString();
	}
	 
//	public boolean equals(Claim otherClaim) {
//		return this.getId().equals(otherClaim.getId());
//	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof CarAccidentHandling) && ((CarAccidentHandling)obj).getId().equals(this.getId()); 
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppliedCustomerId() {
		return appliedCustomerId;
	}

	public void setAppliedCustomerId(String appliedCustomerId) {
		this.appliedCustomerId = appliedCustomerId;
	}

	public String getAccidentCause() {
		return accidentCause;
	}

	public void setAccidentCause(String accidentCause) {
		this.accidentCause = accidentCause;
	}

	public String getAccidentLocation() {
		return accidentLocation;
	}

	public void setAccidentLocation(String accidentLocation) {
		this.accidentLocation = accidentLocation;
	}

	public LocalDateTime getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(LocalDateTime accidentDate) {
		this.accidentDate = accidentDate;
	}

	public LocalDateTime getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(LocalDateTime claimDate) {
		this.claimDate = claimDate;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public ArrayList<AccidentCar> getCarInfos() {
		return carInfos;
	}

	public void setCarInfos(ArrayList<AccidentCar> carInfos) {
		this.carInfos = carInfos;
	}

	public ArrayList<AccidentPerson> getPeopleInfos() {
		return peopleInfos;
	}

	public void setPeopleInfos(ArrayList<AccidentPerson> peopleInfos) {
		this.peopleInfos = peopleInfos;
	}
}
