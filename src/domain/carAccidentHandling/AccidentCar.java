package domain.carAccidentHandling;

public class AccidentCar {
	private String id;
	private String carAccidentHandlingId;
	private String carNo;
	private long cost;
	private String ownerName;
	private String ownerPhoneNo;
	private String visitedShopName;

	public boolean equalsAttributes(Object obj) {
		if(obj instanceof AccidentCar) {
			AccidentCar other = (AccidentCar) obj;
			if (other.id.equals(this.id) && other.carAccidentHandlingId.equals(this.carAccidentHandlingId) &&
					other.carNo.equals(this.carNo) &&
					(other.cost ==this.cost) &&
					other.ownerName.equals(this.ownerName) &&
					other.ownerPhoneNo.equals(this.ownerPhoneNo) &&
					other.visitedShopName.equals(this.visitedShopName))
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
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public long getCost() {
		return cost;
	}
	public void setCost(long cost) {
		this.cost = cost;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerPhoneNo() {
		return ownerPhoneNo;
	}
	public void setOwnerPhoneNo(String ownerPhoneNo) {
		this.ownerPhoneNo = ownerPhoneNo;
	}
	public String getVisitedShopName() {
		return visitedShopName;
	}
	public void setVisitedShopName(String visitedShopName) {
		this.visitedShopName = visitedShopName;
	}
}
