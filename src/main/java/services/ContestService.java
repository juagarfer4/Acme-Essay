package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ContestRepository;
import security.UserAccount;

import domain.Contest;
import domain.Essay;
import domain.Organiser;
import domain.PublicSession;
import forms.ContestForm;

@Service
@Transactional
public class ContestService {

	// Managed repository ---------------------------------

	@Autowired
	private ContestRepository contestRepository;

	// Supporting services ------------------------------

	@Autowired
	private OrganiserService organiserService;

	// Constructors ----------------------------------

	public ContestService() {
		super();
	}

	// Simple CRUD methods -----------------------------------

	public Contest create() {
		Contest result;
		Collection<Organiser> organisers;
		Collection<Essay> essays;
		Collection<PublicSession> publicSessions;
		Organiser organiser;
		
		result = new Contest();
		organisers = new ArrayList<Organiser>();
		essays = new ArrayList<Essay>();
		publicSessions = new ArrayList<PublicSession>();
		organiser = organiserService.findByPrincipal();

		result.setOrganisers(organisers);
		result.setEssays(essays);
		result.setPublicSessions(publicSessions);
		
		organisers.add(organiser);
		
		return result;
	}

	public Contest findOne(Integer contestId) {
		Contest result;

		result = contestRepository.findOne(contestId);

		Assert.notNull(result);

		return result;
	}

	public Collection<Contest> findAll() {
		Collection<Contest> result;

		result = contestRepository.findAll();

		return result;
	}

	public void save(Contest contest) {
		Assert.notNull(contest);

		this.checkPrincipal(contest);

		Integer id;
		Date holdingDate;
		Date deadline;
		String results;
		Date date;

		id = contest.getId();
		holdingDate = contest.getHoldingDate();
		deadline = contest.getDeadline();
		results = contest.getResults();
		date = new Date(System.currentTimeMillis() - 1);

		if(id==0){
			Assert.isTrue(date.before(deadline));
		}else if (id > 0) {
			if (!(results.isEmpty())) {
				Assert.isTrue(holdingDate.before(date));
			}
			this.checkDates(contest);
		}

		Assert.isTrue(deadline.before(holdingDate));

		contestRepository.save(contest);
	}

	public void delete(Contest contest) {
		Assert.notNull(contest);

		this.checkPrincipal(contest);

		Collection<Essay> essays;
		Integer size;

		essays = contest.getEssays();
		size = essays.size();

		Assert.isTrue(size == 0);

		contestRepository.delete(contest);
	}

	// Other business methods ------------------------------

	public void checkPrincipal(Contest contest) {
		Assert.notNull(contest);
		
		Organiser organiser;
		Collection<Organiser> organisers;
		
		organiser=organiserService.findByPrincipal();
		organisers=contest.getOrganisers();
		
		Assert.isTrue(organisers.contains(organiser));
	}

	public Collection<Contest> findAllContestsByOrganiser() {
		Collection<Contest> result;
		Organiser organiser;
		UserAccount userAccount;
		Integer organiserId;

		organiser = organiserService.findByPrincipal();

		Assert.notNull(organiser);

		result = new ArrayList<Contest>();
		userAccount = organiser.getUserAccount();
		organiserId = userAccount.getId();
		result = contestRepository.findAllContestsByOrganiser(organiserId);

		return result;
	}

	public void checkDates(Contest newContest) {
		Assert.notNull(newContest);

		this.checkPrincipal(newContest);

		Contest oldContest;
		Integer id;

		id = newContest.getId();
		oldContest = this.findOne(id);

		Date oldHoldingDate;
		Date oldDeadline;
		Date newHoldingDate;
		Date newDeadline;

		oldHoldingDate = oldContest.getHoldingDate();
		oldDeadline = oldContest.getDeadline();
		newHoldingDate = newContest.getHoldingDate();
		newDeadline = newContest.getDeadline();

		if (!(oldHoldingDate.compareTo(newHoldingDate) == 0)) {
			Assert.isTrue(oldHoldingDate.before(newHoldingDate));
		}

		if (!(oldDeadline.compareTo(newDeadline) == 0)) {
			Assert.isTrue(oldDeadline.before(newDeadline));
		}
	}

	public Collection<Contest> findAllContestDescOrderNumberSubmitted() {
		Collection<Contest> result;

		result = new ArrayList<Contest>();
		result = contestRepository.findAllContestDescOrderNumberSubmitted();

		return result;
	}

	public Collection<Contest> findAllContestsHeldDuringLastMonth() {
		Collection<Contest> result;

		result = contestRepository.findAllContestsHeldDuringLastMonth();

		return result;
	}
	
	public Double averageNumberContestsByOrganiser() {
		Double result;
		Double totalNumberOfContests;
		Double totalNumberOfOrganisers;
		
		totalNumberOfContests=(double) this.findAll().size();
		totalNumberOfOrganisers=(double) organiserService.findAll().size();
		
		result= totalNumberOfContests / totalNumberOfOrganisers ;
		

		return result;
	}
	

	public Collection<Organiser> findAllOrganiserNotInThisContest(Integer contestId) {
		Collection<Organiser> result;
		Collection<Organiser> organiserInContest;
		
		
		organiserInContest = this.contestRepository.findAllOrganiserInThisContest(contestId);
		result = organiserService.findAll();
		result.removeAll(organiserInContest);
		
		
		return result;
	}

	public Collection<Organiser> findAllOrganisersByContest(int contestId){
	Collection<Organiser> result;
	
	result=contestRepository.findAllOrganisersByContest(contestId);
	
	return result;
	}
	
	public Collection<Contest> findAllContestDescOrderOfEssays(){
	Collection<Contest> result;
	
	result=contestRepository.findAllContestDescOrderNumberSubmitted();
	
	return result;
	}
	
	
	public Contest reconstruct(ContestForm contestForm){
		Integer contestId;
		Contest contest;
		Organiser organiser;
		
		contestId=contestForm.getContestId();
		contest=this.findOne(contestId);
		organiser=contestForm.getOrganiser();
		
		Collection<Organiser> organisers;
		organisers=contest.getOrganisers();
		organisers.add(organiser);
		
		return contest;
	}

}
