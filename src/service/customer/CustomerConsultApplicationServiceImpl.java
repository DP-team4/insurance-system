package service.customer;

import java.util.ArrayList;

import domain.consultApplication.ConsultApplication;
import repository.consultApplication.ConsultApplicationRepository;

public class CustomerConsultApplicationServiceImpl implements CustomerConsultApplicationService {
	private final static CustomerConsultApplicationService customerConsultApplicationService = new CustomerConsultApplicationServiceImpl();
    private static final ConsultApplicationRepository consultApplicationRepository = ConsultApplicationRepository.getInstance();

	// Singleton
	private CustomerConsultApplicationServiceImpl(){}
	public static CustomerConsultApplicationService getInstance() { return customerConsultApplicationService; }
	
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
