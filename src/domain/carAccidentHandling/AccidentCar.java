package domain.carAccidentHandling;

public class AccidentCar {
	private String id;
	private int carNo;
	private int cost;
	private String ownerName;
	private String ownerPhoneNo;
	private String visitedShopName;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCarNo() {
		return carNo;
	}
	public void setCarNo(int carNo) {
		this.carNo = carNo;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
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
