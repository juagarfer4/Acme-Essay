package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Contest;
import domain.Essay;
import domain.Organiser;
import domain.PublicSession;
import domain.Repo;
import forms.PublicSessionForm;

import repositories.PublicSessionRepository;

@Service
@Transactional
public class PublicSessionService {

	// Managed repository ---------------------------------

	@Autowired
	private PublicSessionRepository publicSessionRepository;

	// Supporting services ------------------------------
	
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private OrganiserService organiserService;

	// Constructors ----------------------------------

	public PublicSessionService() {
		super();
	}

	// Simple CRUD methods -----------------------------------
	
	public PublicSession create(Integer contestId){
		Contest contest;
		
		contest=contestService.findOne(contestId);
		
		contestService.checkPrincipal(contest);
		
		PublicSession result;
		Organiser chairman;
		Collection<Essay> essays;
		Collection<Repo> repos;
		
		result=new PublicSession();
		chairman=organiserService.findByPrincipal();
		essays=new ArrayList<Essay>();
		repos=new ArrayList<Repo>();
		
		result.setContest(contest);
		result.setChairman(chairman);
		result.setEssays(essays);
		result.setRepos(repos);
		
		return result;
	}
	
	public PublicSession findOne(Integer publicSessionId){
		PublicSession result;
		
		result=publicSessionRepository.findOne(publicSessionId);
		
		return result;
	}
	
	public void save(PublicSession publicSession){
		Assert.notNull(publicSession);
		
		this.checkPrincipal(publicSession);
		
		Contest contest;
		Collection<Essay> essays;
		Integer size;
		Integer limitOfEssays;
		
		contest=publicSession.getContest();
		essays=publicSession.getEssays();
		size=essays.size();
		limitOfEssays=publicSession.getLimitOfEssays();
		
		Assert.isTrue(size<=limitOfEssays);
		
		Integer id;
		Date deadline;
		Date startMoment;
		Date endMoment;
		Date date;
		
		id=publicSession.getId();
		deadline=contest.getDeadline();
		startMoment=publicSession.getStartMoment();
		endMoment=publicSession.getEndMoment();
		date=new Date(System.currentTimeMillis()-1);
		
		if(id==0){
			Assert.isTrue(deadline.before(startMoment));
			Assert.isTrue(date.before(startMoment));
		}else if(id>0){
			this.checkDates(publicSession);
		}
		
		Assert.isTrue(startMoment.before(endMoment));
		
		publicSessionRepository.save(publicSession);
	}
	
	// Other business methods -------------------------------------------
	
	public void checkPrincipal(PublicSession publicSession) {
		Organiser organiser;
		Contest contest;
		Collection<Organiser> organisers;
		
		organiser=organiserService.findByPrincipal();
		contest=publicSession.getContest();
		organisers=contest.getOrganisers();
		
		Assert.isTrue(organisers.contains(organiser));
	}
	
	public void checkDates(PublicSession newPublicSession) {
		Assert.notNull(newPublicSession);

		this.checkPrincipal(newPublicSession);

		PublicSession oldPublicSession;
		Integer id;

		id = newPublicSession.getId();
		oldPublicSession = this.findOne(id);

		Date oldEndMoment;
		Date oldStartMoment;
		Date newEndMoment;
		Date newStartMoment;

		oldEndMoment = oldPublicSession.getEndMoment();
		oldStartMoment = oldPublicSession.getStartMoment();
		newEndMoment = newPublicSession.getEndMoment();
		newStartMoment = newPublicSession.getStartMoment();

		if (!(oldEndMoment.compareTo(newEndMoment) == 0)) {
			Assert.isTrue(oldEndMoment.before(newEndMoment));
		}

		if (!(oldStartMoment.compareTo(newStartMoment) == 0)) {
			Assert.isTrue(oldStartMoment.before(newStartMoment));
		}
	}
	
	public Collection<PublicSession> findAllPublicSessionsByContest(int contestId){
		Contest contest;
		
		contest=contestService.findOne(contestId);
		
		Assert.notNull(contest);
		
		contestService.checkPrincipal(contest);
		
		Collection<PublicSession> result;
		
		result=publicSessionRepository.findAllPublicSessionsByContest(contestId);
		
		return result;
	}
	
	public Collection<PublicSession> findAllPublicSessionsWithChairman(){
		Collection<PublicSession> result;
		
		result=publicSessionRepository.findAllPublicSessionsWithChairman();
		
		return result;
	}
	
	public Collection<PublicSession>findAllPublicSessionsDescOrderOfEssays(){
		Collection<PublicSession> result;
		
		result=publicSessionRepository.findAllPublicSessionsDescOrderOfEssays();
		
		return result;
	}
	
	
	public PublicSession reconstruct(PublicSessionForm publicSessionForm){
		Integer publicSessionId;
		PublicSession publicSession;
		Essay essay;
		
		publicSessionId=publicSessionForm.getPublicSessionId();
		publicSession=this.findOne(publicSessionId);
		essay=publicSessionForm.getEssay();
		
		Collection<Essay> essays;
		essays=publicSession.getEssays();
		
		essays.add(essay);
		essay.setPublicSession(publicSession);
		
		this.save(publicSession);
		
		return publicSession;
	}

}
