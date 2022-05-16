package domain.UW;


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

	private UWDocument(String name, String path, UWDocumentState uwDocumentState) {
		this.name = name; this.path = path; this.uwDocumentState = uwDocumentState;
	}
	public static UWDocument createRequested(String name) {
		return new UWDocument(name, null, UWDocumentState.REQUESTED);
	}
	public static UWDocument createSubmitted(String name, String path) {
		return new UWDocument(name, path, UWDocumentState.SUBMITTED);
	}

	// getters
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
}