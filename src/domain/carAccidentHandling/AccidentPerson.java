package domain.carAccidentHandling;

public class AccidentPerson {
	private String name;
	private String tel;
	private String hospital;
	//원래 지정 병원이 별도로 있어야함.
	//일단 걍 String으로 해서 수동으로 해석한다 치고
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
}
