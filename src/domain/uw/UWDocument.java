package domain.uw;


import java.util.StringJoiner;

/**
 * @author wls43
 * @version 1.0
 * @created 09-5-2022 오후 4:48:26
 */
public class UWDocument {

	private String id;
	private String name;
	private String path;
	private UWDocumentState uwDocumentState;
	private String uwId;

	public UWDocument(){
		this.uwDocumentState = UWDocumentState.UNDEFINED;
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("UW ID: " + this.uwId).add("이름: " + this.name).add("저장 위치: " + this.path).add("상태: " + this.uwDocumentState.name());
		return sj.toString();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UWDocument) {
			return ((UWDocument) obj).getId().equals(this.id);
		}
		return false;
	}
	public boolean equalsAttributes(Object obj) {
		if(obj instanceof UWDocument other &&
				other.id.equals(this.id) &&
				other.name.equals(this.name) &&
				other.path.equals(this.path) &&
				other.uwDocumentState.equals(this.uwDocumentState) &&
				other.uwId.equals(this.uwId)
		) return true;
		else return false;
	}

	// getters setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public UWDocumentState getUwDocumentState() {
		return uwDocumentState;
	}
	public void setUwDocumentState(UWDocumentState uwDocumentState) {
		this.uwDocumentState = uwDocumentState;
	}
	public String getUwId() {
		return uwId;
	}
	public void setUwId(String uwId) {
		this.uwId = uwId;
	}
}