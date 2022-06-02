package service;

import domain.insurance.Insurance;
import repository.insurance.InsuranceRepository;
import view.viewUtility.View;

public class InsuranceSalesAuditServiceImpl implements InsuranceSalesAuditService{
    private static final InsuranceSalesAuditServiceImpl instance = new InsuranceSalesAuditServiceImpl();
    private final InsuranceRepository insuranceRepository = InsuranceRepository.getInstance();

    private InsuranceSalesAuditServiceImpl() {}

    public InsuranceSalesAuditServiceImpl getInstance() { return instance; }

    @Override
    public boolean requestAudit(Insurance insurance) {
        return false;
    }
}
