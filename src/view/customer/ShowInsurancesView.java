package view.customer;

import java.util.ArrayList;
import java.util.Scanner;

import domain.insurance.Insurance;
import service.InsuranceManagementService;
import service.InsuranceManagementServiceImpl;
import view.viewUtility.ScannerUtility;

public class ShowInsurancesView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
    // Service
    private final InsuranceManagementService insuranceManagementService = InsuranceManagementServiceImpl.getInstance();
	
	public void show() {
		System.out.println("\n[ �����ǰ ��ȸ ]");
        ArrayList<Insurance> insurances = insuranceManagementService.getAll();
        if(insurances.size() == 0) System.out.println("�����ǰ�� �����ϴ�.");
        else {
            insurances.forEach(i -> {
                System.out.println(i);
                System.out.println();
            });
        }
	}
}
