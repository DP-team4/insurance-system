package service.customer;

import java.util.ArrayList;

import domain.consultApplication.ConsultApplication;

public interface MyConsultApplicationService {
	boolean applyConsultation(ConsultApplication consultApplication);
	ArrayList<ConsultApplication> getByCustomerId(String customerId);
	boolean deleteMyConsultation(String id, String customerId);
}
