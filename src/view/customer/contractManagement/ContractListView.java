package view.customer.contractManagement;

import java.util.ArrayList;

import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Insurance;
import service.InsuranceManagementService;
import service.InsuranceManagementServiceImpl;
import service.customer.ContractManagementService;
import service.customer.ContractManagementServiceImpl;

public class ContractListView {    
	// Service
    private ContractManagementService contractManagementService = ContractManagementServiceImpl.getInstance();
    private InsuranceManagementService insuranceManagementService = InsuranceManagementServiceImpl.getInstance();
	
	public boolean show(CancelApplicationListView cancellationListView, Customer customer) {
		System.out.println("\n[ ���� �����ǰ ��� ]");
		// �α��ε� ȸ���� �����ǰ ������ ��û�Ѵ�
		ArrayList<Contract> contracts = contractManagementService.getCustomerContracts(customer.getId());
		// A2. ������ ������ 0���� ���
		if(contracts.size() == 0) { System.out.println("\n������ ������ �����ϴ�. ���� ȭ������ ���ư��ϴ�."); return false; }
		for(Contract contract : contracts) {
			Insurance insurance = insuranceManagementService.getById(contract.getInsuranceId());
			if(insurance == null) {
				System.out.println("Exception : ����� ���� ������ �����Ǿ����ϴ�."); return false;// Exception 3
			}
			// �� ���� ���� ȭ��(�����ǰ��, ��������, �����, ������, �����, ���� ��û ��ư)�� ����Ѵ�.
			System.out.println();
			System.out.println("�����ǰ�� : " + insurance.getName());
			System.out.println("�������� : " + insurance.getInsuranceCategory().name());
			System.out.println("����� : " + contract.getContractDateTime());
			System.out.println("������ : " + contract.getExpirationDateTime());
			System.out.println("�� ����� : " + contractManagementService.getMonthlyPremium(contract, insurance, customer));
		}
		return true;
	}
}
