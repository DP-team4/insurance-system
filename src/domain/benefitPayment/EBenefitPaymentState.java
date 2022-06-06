package domain.benefitPayment;

public enum EBenefitPaymentState {
		ONREVIEW("처리 중"), READY("지급 대기 중"), REFUSED("지급 거절"), PAID("지급완료");
		//DB에서는숫자로 저장 예정
	
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
