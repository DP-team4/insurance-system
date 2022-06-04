package service.customer;

import java.util.ArrayList;

import domain.cancelApplication.CancelApplication;
import domain.contract.Contract;

public interface MyContractManagementService {
    ArrayList<Contract> getCustomerContracts(String customerId);
    boolean applyCancelApplication(CancelApplication cancelApplication);
    int getMonthlyPremium();
}
