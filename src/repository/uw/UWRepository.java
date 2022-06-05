package repository.uw;


import dao.UWDao;
import dao.UWDocumentDao;
import domain.insurance.Clause;
import domain.insurance.Insurance;
import domain.uw.UW;
import domain.uw.UWDocument;

import java.util.ArrayList;

public class UWRepository {
	private static final UWRepository instance = new UWRepository();
	private final UWDao uwDao = new UWDao();
	private final UWDocumentDao uwDocumentDao = new UWDocumentDao();

	private UWRepository(){}
	public static UWRepository getInstance() { return instance; }

	public boolean add(UW uw) {
		String id = uwDao.createAndGetId(uw);
		if(id==null) return false;
		uw.setId(id);
		for (UWDocument document : uw.getDocuments()) {
			document.setUwId(id);
			String documentId = uwDocumentDao.createAndGetId(document);
			if(documentId==null) return false;
			document.setId(documentId);
		}
		return true;
	}
	public boolean delete(String id) {
		if(!uwDocumentDao.deleteAllByInsuranceId(id)) return false;
		return uwDao.delete(id);
	}
	public UW get(String id){
		UW uw = uwDao.retrieveById(id);
		ArrayList<UWDocument> uwDocuments = uwDocumentDao.retrieveAllByUwId(id);
		uw.setDocuments(uwDocuments);
		return uw;
	}
	public ArrayList<UW> getAll() {
		ArrayList<UW> uws = uwDao.retrieveAll();
		for (UW uw : uws) {
			ArrayList<UWDocument> uwDocuments = uwDocumentDao.retrieveAllByUwId(uw.getId());
			uw.setDocuments(uwDocuments);
		}
		return uws;
	}
	public boolean update(UW uw) {
		try {
			UW before = this.get(uw.getId());
			if(!before.equalsAttributes(uw)) if(!uwDao.update(uw)) return false;
			ArrayList<UWDocument> beforeDocuments = before.getDocuments();
			ArrayList<UWDocument> tobeDocuments = uw.getDocuments();

			if(tobeDocuments.size() < 1) { beforeDocuments.forEach(c -> uwDocumentDao.delete(c.getId())); return true;}
			if(beforeDocuments.size() < 1) {tobeDocuments.forEach(uwDocumentDao::create); return true; }

			for (UWDocument tobeDocument : tobeDocuments) {
				//추가
				if(tobeDocument.getId() == null) { uwDocumentDao.create(tobeDocument); continue; }
				for (UWDocument beforeDocument : beforeDocuments) {
					//삭제
					if(!tobeDocuments.contains(beforeDocument)) { uwDocumentDao.delete(beforeDocument.getId()); continue; }
					// 내용 수정
					if (tobeDocument.equals(beforeDocument) && !tobeDocument.equalsAttributes(beforeDocument)) { uwDocumentDao.update(tobeDocument); continue; }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateUwDocument(UWDocument uwDocument) {
		return uwDocumentDao.update(uwDocument);
	}
}