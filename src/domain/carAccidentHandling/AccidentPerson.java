package domain.carAccidentHandling;

public class AccidentPerson {
	private String id;
	private int cost;
	private String name;
	private String phoneNo;
	private String visitedHospitalName;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
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
