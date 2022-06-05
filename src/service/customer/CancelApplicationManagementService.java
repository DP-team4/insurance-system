package service.customer;

import java.util.ArrayList;

import domain.cancelApplication.CancelApplication;

public interface CancelApplicationManagementService {
	boolean applyCancellation(CancelApplication cancelApplication);
	ArrayList<CancelApplication> getByCustomerId(String customerId);
	boolean deleteMyCancellation(String id, String customerId);
}
