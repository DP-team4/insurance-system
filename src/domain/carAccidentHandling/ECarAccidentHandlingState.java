package domain.carAccidentHandling;

public enum ECarAccidentHandlingState {
	ONREVIEW("처리 중"), HANDLED("처리 완료"), REFUSED("거절됨");

	private String title;
	private ECarAccidentHandlingState(String title){
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
