package view.customer.contractManagement;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Clause;
import domain.insurance.Insurance;
import service.InsuranceManagementService;
import service.InsuranceManagementServiceImpl;
import service.customer.MyContractManagementService;
import service.customer.MyContractManagementServiceImpl;

public class ContractListView {    
	// Service
    private MyContractManagementService myContractManagementService = MyContractManagementServiceImpl.getInstance();
    private InsuranceManagementService insuranceManagementService = InsuranceManagementServiceImpl.getInstance();
	
	public boolean show(CancellationListView cancellationListView, Customer customer) {
		System.out.println("\n[ ���� �����ǰ ��� ]");
		// �α��ε� ȸ���� �����ǰ ������ ��û�Ѵ�
		ArrayList<Contract> contracts = myContractManagementService.getCustomerContracts(customer.getId());
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
			System.out.println("�� ����� : " + calculatePremium(contract, insurance));
		}
		return true;
	}
	
	public double calculatePremium(Contract contract, Insurance insurance) {
		// �� ����� premium ���ϱ� = sum
		ArrayList<Clause> clauses = insurance.getClauses();
		long sum = 0;
		for(Clause clause: clauses) sum += clause.getPremium();
		
		// ���� �� ��
		LocalDateTime contractDateTime = contract.getContractDateTime();
		LocalDateTime expirationDateTime = contract.getExpirationDateTime();
		long month = ChronoUnit.MONTHS.between(contractDateTime, expirationDateTime);
		
		// sum / (���� �� ��)
		return (double) sum / month;
	}
}
