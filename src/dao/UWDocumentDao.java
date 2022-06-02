package dao;

import domain.uw.UWDocument;
import domain.uw.UWDocumentState;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UWDocumentDao extends Dao {

    public UWDocumentDao() {
        super.connect();
    }

    public String createAndGetId(UWDocument uwDocument) {
        String query = String.format(
                "insert into uw_document values (0, '%s', '%s', '%s', %s)",
                uwDocument.getName(), uwDocument.getPath(), uwDocument.getUwDocumentState().name(), uwDocument.getUwId()
        );
        return super.createAndGetId(query);
    }

    public boolean create(UWDocument uwDocument) {
        String query = String.format(
                "insert into uw_document values (0, '%s', '%s', '%s', %s)",
                uwDocument.getName(), uwDocument.getPath(), uwDocument.getUwDocumentState().name(), uwDocument.getUwId()
        );
        return super.create(query);
    }

    public boolean update(UWDocument uwDocument) {
        String query = String.format(
                "update uw_document set name='%s', path='%s', uw_document_state='%s' uw_id=%s where id=%s",
                uwDocument.getName(), uwDocument.getPath(), uwDocument.getUwDocumentState().name(), uwDocument.getUwId(), uwDocument.getId()
        );
        return super.update(query);
    }

    public boolean delete(String id) {
        String query = String.format("delete from uw_document where id=%s", id);
        return super.delete(query);
    }

    public boolean deleteAllByInsuranceId(String uwId) {
        String query = String.format("delete from uw_document where uw_id=%s", uwId);
        return super.delete(query);
    }

    public ArrayList<UWDocument> retrieveAll() {
        try {
            String query = "select * from uw_document";
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null) return null;
            ArrayList<UWDocument> uwDocuments = new ArrayList<>();
            while (resultSet.next()) {
                UWDocument uwDocument = getCurrentUwDocument(resultSet);
                uwDocuments.add(uwDocument);
            }
            return uwDocuments;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public UWDocument retrieveById(String id) {
        try {
            String query = String.format("select * from uw_document where id=%s", id);
            ResultSet resultSet = super.retrieve(query);
            if(resultSet==null || !resultSet.next()) return null;
            UWDocument uwDocument = getCurrentUwDocument(resultSet);
            return uwDocument;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private UWDocument getCurrentUwDocument(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        String path = resultSet.getString("path");
        UWDocumentState uwDocumentState= UWDocumentState.valueOf(resultSet.getString("uw_document_state"));
        String uwId = resultSet.getString("uw_id");
        UWDocument uwDocument = new UWDocument();
        uwDocument.setId(id);
        uwDocument.setName(name);
        uwDocument.setPath(path);
        uwDocument.setUwDocumentState(uwDocumentState);
        uwDocument.setUwId(uwId);
        return uwDocument;
    }

    public ArrayList<UWDocument> retrieveAllByUwId(String uwId) {
        try {
            String query = "select * from uw_document where uw_id=" + uwId;
            ResultSet resultSet = super.retrieve(query);
            if (resultSet == null) return null;
            ArrayList<UWDocument> uwDocuments = new ArrayList<>();
            while (resultSet.next()) {
                UWDocument uwDocument = getCurrentUwDocument(resultSet);
                uwDocuments.add(uwDocument);
            }
            return uwDocuments;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
