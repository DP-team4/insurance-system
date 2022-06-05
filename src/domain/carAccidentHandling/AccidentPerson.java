package domain.carAccidentHandling;

public class AccidentPerson {
	private String id;
	private String carAccidentHandlingId;
	private long cost;
	private String name;
	private String phoneNo;
	private String visitedHospitalName;

	public boolean equalsAttributes(Object obj) {
		if(obj instanceof AccidentPerson) {
			AccidentPerson other = (AccidentPerson) obj;
			if (other.id.equals(this.id) && other.carAccidentHandlingId.equals(this.carAccidentHandlingId) &&
					(other.cost ==this.cost) &&
					other.name.equals(this.name) &&
					other.phoneNo.equals(this.phoneNo) &&
					other.visitedHospitalName.equals(this.visitedHospitalName))
				return true;
		}
		return false;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCarAccidentHandlingId() {
		return carAccidentHandlingId;
	}
	public void setCarAccidentHandlingId(String carAccidentHandlingId) {
		this.carAccidentHandlingId = carAccidentHandlingId;
	}
	public long getCost() {
		return cost;
	}
	public void setCost(long cost) {
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getVisitedHospitalName() {
		return visitedHospitalName;
	}
	public void setVisitedHospitalName(String visitedHospitalName) {
		this.visitedHospitalName = visitedHospitalName;
	}
}
