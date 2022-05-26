package domain.customer;

import java.util.StringJoiner;

/**
 * ���� ����� ���� �� ����
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
		sj.add("�� ��: " + this.carPrice).add("�� ��: " + this.housePrice).add("���� ���: " + this.drivingCareer).add("���� ����: " + this.shipPrice);
		return sj.toString();
	}
}
