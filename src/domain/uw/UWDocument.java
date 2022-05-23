package domain.uw;


/**
 * @author wls43
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:26
 */
public class UWDocument {

	private String id = "UWDocument" + System.currentTimeMillis();
	private String name;
	private String path;
	private UWDocumentState uwDocumentState;

	public UWDocument(){}

	// getters setters
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPath() {
		return path;
	}
	public UWDocumentState getUwDocumentState() {
		return uwDocumentState;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setUwDocumentState(UWDocumentState uwDocumentState) {
		this.uwDocumentState = uwDocumentState;
	}
}