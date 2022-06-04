package service.customer;

import java.util.ArrayList;

import domain.consultApplication.ConsultApplication;
import repository.consultApplication.ConsultApplicationRepository;

public class MyConsultApplicationServiceImpl implements MyConsultApplicationService {
	private final static MyConsultApplicationService consultApplicationService = new MyConsultApplicationServiceImpl();
    private static final ConsultApplicationRepository consultApplicationRepository = ConsultApplicationRepository.getInstance();

	// Singleton
	private MyConsultApplicationServiceImpl(){}
	public static MyConsultApplicationService getInstance() { return consultApplicationService; }
	
	@Override
	public boolean applyConsultation(ConsultApplication consultApplication) {
		return consultApplicationRepository.add(consultApplication);
	}
	
	@Override
	public ArrayList<ConsultApplication> getByCustomerId(String customerId) {
		return consultApplicationRepository.getByCustomerId(customerId);
	}
	
	@Override
	public boolean deleteMyConsultation(String id, String customerId) {
		ConsultApplication consultApplication = consultApplicationRepository.get(id);
		if(consultApplication == null) return false;
		else if(consultApplication.getCustomerId().equals(customerId)) return consultApplicationRepository.delete(id);
		return false;
	}
}
