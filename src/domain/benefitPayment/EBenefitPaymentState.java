package domain.benefitPayment;

public enum EBenefitPaymentState {
		ONREVIEW("ó�� ��"), READY("���� ��� ��"), REFUSED("���� ����"), PAID("���޿Ϸ�");
		//DB�����¼��ڷ� ���� ����
	
	private String title;
	private EBenefitPaymentState(String title){
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
