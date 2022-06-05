package service.customer;

import java.util.ArrayList;

import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Insurance;

public interface CustomerContractService {
    ArrayList<Contract> getCustomerContracts(String customerId);
    long getMonthlyPremium(Contract contract, Insurance insurance, Customer customer);
}
