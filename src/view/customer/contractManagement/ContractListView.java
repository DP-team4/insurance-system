package view.customer.contractManagement;

import java.util.ArrayList;

import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Insurance;
import service.InsuranceManagementService;
import service.InsuranceManagementServiceImpl;
import service.customer.CustomerContractService;
import service.customer.CustomerContractServiceImpl;

public class ContractListView {    
	// Service
    private CustomerContractService customerContractService = CustomerContractServiceImpl.getInstance();
    private InsuranceManagementService insuranceManagementService = InsuranceManagementServiceImpl.getInstance();
	
	public boolean show(CancelApplicationListView cancellationListView, Customer customer) {
		System.out.println("\n[ ���� �����ǰ ��� ]");
		// �α��ε� ȸ���� �����ǰ ������ ��û�Ѵ�
		ArrayList<Contract> contracts = customerContractService.getCustomerContracts(customer.getId());
		// A2. ������ ������ 0���� ���
		if(contracts.size() == 0) { System.out.println("\n������ ������ �����ϴ�. ���� ȭ������ ���ư��ϴ�."); return false; }
		for(Contract contract : contracts) {
			Insurance insurance = insuranceManagementService.getById(contract.getInsuranceId());
			if(insurance == null) {
				System.out.println("Exception : ����� ���� ������ �����Ǿ����ϴ�."); return false;// Exception 3
			}
			// �� ���� ���� ȭ��(�����ǰ��, ��������, �����, ������, �����, ���� ��û ��ư)�� ����Ѵ�.
			System.out.println("��� ID : " + contract.getId());
			System.out.println("����� : " + contract.getContractDateTime());
			System.out.println("�����ǰ�� : " + insurance.getName());
			System.out.println("�������� : " + insurance.getInsuranceCategory().name());
			System.out.println("����� : " + contract.getContractDateTime());
			System.out.println("������ : " + contract.getExpirationDateTime());
			System.out.println("�� ����� : " + customerContractService.getMonthlyPremium(contract, insurance, customer));
		}
		return true;
	}
}
