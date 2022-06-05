package service;

import domain.uw.UW;
import domain.uw.UWDocument;

import java.util.ArrayList;

public interface UwManagementService {
    ArrayList<UW> getAll();
    ArrayList<UW> getAllUnderAudit();
    UW getById(String id);
    boolean acceptUw(UW uw);
    boolean rejectUw(UW uw);
    boolean requestAdditionalDocument(String uwId, String documentName);
    boolean updateUw(UW uw);
    boolean updateUwDocument(UWDocument uwDocument);
    boolean addUw(UW uw);
}
