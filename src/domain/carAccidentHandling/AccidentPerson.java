package domain.carAccidentHandling;

public class AccidentPerson {
	private String name;
	private String tel;
	private String hospital;
	//���� ���� ������ ������ �־����.
	//�ϴ� �� String���� �ؼ� �������� �ؼ��Ѵ� ġ��
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
