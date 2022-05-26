package domain.customer;

import java.util.StringJoiner;

/**
 * 요율 계산을 위한 고객 정보
 * @author bigst
 *
 */
public class AdditionalInfo {
	
	private long carPrice;
	public long housePrice;
	public int drivingCareer;
	public long shipPrice;

	public long getCarPrice() { return carPrice; }
	public void setCarPrice(long carPrice) { this.carPrice = carPrice; }
	public long getHousePrice() { return housePrice; }
	public void setHousePrice(long housePrice) { this.housePrice = housePrice; }
	public int getDrivingCareer() { return drivingCareer; }
	public void setDrivingCareer(int drivingCareer) { this.drivingCareer = drivingCareer; }
	public long getShipPrice() { return shipPrice; }
	public void setShipPrice(long shipPrice) { this.shipPrice = shipPrice; }
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("차 값: " + this.carPrice).add("집 값: " + this.housePrice).add("운전 경력: " + this.drivingCareer).add("선박 가격: " + this.shipPrice);
		return sj.toString();
	}
}
