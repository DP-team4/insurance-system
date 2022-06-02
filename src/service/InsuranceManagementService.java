package service;

import domain.insurance.Clause;
import domain.insurance.Insurance;

import java.util.ArrayList;

public interface InsuranceManagementService {
    ArrayList<Insurance> getAll();
    Insurance getById(String id);
    Insurance getByName(String name);
    boolean addInsurance(Insurance insurance);
    boolean deleteInsurance(String id);
    boolean updateInsurance(Insurance insurance);
    Clause getClauseById(String clauseId);
    boolean updateClause(Clause clause);
}
