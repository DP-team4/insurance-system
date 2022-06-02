package service;

import domain.consultApplication.ConsultApplication;
import repository.ConsultApplicationListImpl;

public class ConsultApplicationServiceImpl implements ConsultApplicationService {
	private final static ConsultApplicationService consultApplicationService = new ConsultApplicationServiceImpl();
    private static final ConsultApplicationListImpl ConsultApplicationRepository = ConsultApplicationListImpl.getInstance();

	// Singleton
	private ConsultApplicationServiceImpl(){}
	public static ConsultApplicationService getInstance() { return consultApplicationService; }
	
	@Override
	public boolean applyConsultation(ConsultApplication consultApplication) {
		return (ConsultApplicationRepository.add(consultApplication));
	}
}
