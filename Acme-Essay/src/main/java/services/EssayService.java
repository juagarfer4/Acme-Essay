package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EssayRepository;
import security.UserAccount;

import domain.Author;
import domain.Contest;
import domain.Essay;
import domain.Organiser;

@Service
@Transactional
public class EssayService {
	
	// Managed repository ---------------------------------
	
	@Autowired
	private EssayRepository essayRepository;
	
	// Supporting services ------------------------------
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private OrganiserService organiserService;
	
	// Constructors ----------------------------------
	
	public EssayService(){
		super();
	}
	
	// Simple CRUD methods -----------------------------------
	
	public Essay create(Integer contestId){
		Contest contest;
		
		contest=contestService.findOne(contestId);
		
		Assert.notNull(contest);
		
		Essay result;
		Author author;
		Boolean published;
		Date submissionDate;
		
		result=new Essay();
		author=authorService.findByPrincipal();
		published= false;
		submissionDate =new Date(System.currentTimeMillis()-1);
		
		result.setSubmissionDate(submissionDate);
		result.setAuthor(author);
		result.setContest(contest);
		result.setPublished(published);
		
		return result;
	}
	
	public Essay findOne(Integer essayId){
		Essay result;
		
		result=essayRepository.findOne(essayId);
		
		Assert.notNull(result);
		
		return result;
	}
	
	public Collection<Essay> findAll(){
		Collection<Essay> result;
		
		result=essayRepository.findAll();
		
		return result;
	}
	
	public void saveAuthor(Essay essay){
		Assert.notNull(essay);
		
		this.checkPrincipalAuthor(essay);
		
		Date deadline;
		Date submissionDate;
		Integer id;
		
	
		
		
		deadline=essay.getContest().getDeadline();
		submissionDate =new Date(System.currentTimeMillis()-1);
		id=essay.getId();
		
		Assert.isTrue(submissionDate.before(deadline));
		
		//titulo unico en el contest
		if(id==0){
			Assert.isTrue(this.uniqueTitle(essay.getTitle(),essay.getContest().getId())==0);
		}
		
		essayRepository.save(essay);
	}
	
	public void saveOrganiser(Essay essay){
		Assert.notNull(essay);
		
		this.checkPrincipalOrganiser(essay);
		
		Date holdingDate;
		Date date;
		Contest contest;
		
		contest=essay.getContest();
		holdingDate=contest.getHoldingDate();
		date=new Date(System.currentTimeMillis()-1);
		
		Assert.isTrue(holdingDate.before(date));
		
		essayRepository.save(essay);
	}
	
	// Other business methods ------------------------------
	
	public void checkPrincipalAuthor(Essay essay) {
		Author author;
		Author principal;

		author=essay.getAuthor();
		principal = authorService.findByPrincipal();
		
		Assert.isTrue(author.getUserAccount().getId()==principal.getUserAccount().getId());
	}
	
	public void checkPrincipalOrganiser(Essay essay){
//		Assert.notNull(essay);
//		
//		Organiser organiser;
//		UserAccount organiserUserAccount;
//		Integer organiserUserAccountId;
//		Organiser principal;
//		UserAccount principalUserAccount;
//		Integer principalUserAccountId;
//		Contest contest;
//		
//		contest=essay.getContest();
//		organiser=contest.getOrganiser();
//		organiserUserAccount=organiser.getUserAccount();
//		organiserUserAccountId=organiserUserAccount.getId();
//		principal=organiserService.findByPrincipal();
//		principalUserAccount=principal.getUserAccount();
//		principalUserAccountId=principalUserAccount.getId();
//		
//		Assert.isTrue(organiserUserAccountId==principalUserAccountId);
		Assert.notNull(essay);
		
		Organiser organiser;
		Contest contest;
		Collection<Organiser> organisers;
		
		organiser=organiserService.findByPrincipal();
		contest=essay.getContest();
		organisers=contest.getOrganisers();
		
		Assert.isTrue(organisers.contains(organiser));
	}
	
	public void publish(Essay essay){
		Assert.notNull(essay);
		
		this.checkPrincipalOrganiser(essay);
		
		Date holdingDate;
		Date date;
		Contest contest;
		
		contest=essay.getContest();
		holdingDate=contest.getHoldingDate();
		date=new Date(System.currentTimeMillis()-1);
		
		Assert.isTrue(holdingDate.before(date));
		
		essay.setPublished(true);
		
		this.saveOrganiser(essay);
	}
	
	public Collection<Essay> findAllEssaysByContest(Integer contestId){
		Collection<Essay> result;
		
		result=essayRepository.findAllEssaysByContest(contestId);
		
		return result;
	}
	
	public Collection<Essay> findAllEssaysByAuthor(Integer contestId){
		Author author;
		author=authorService.findByPrincipal();
		
		Assert.notNull(author);
		
		Collection<Essay> result;
		UserAccount userAccount;
		Integer userAccountId;
		
		userAccount=author.getUserAccount();
		userAccountId=userAccount.getId();
		result=essayRepository.findAllEssaysByAuthor(userAccountId, contestId);
		
		return result;
	}
	
	public Double averageNumberEssaysByAuthor() {
		Double result;
		Double totalNumberOfEssays;
		Double totalNumberOfAuthors;
		
		totalNumberOfEssays=(double) this.findAll().size();
		totalNumberOfAuthors=(double) authorService.findAll().size();
		
		result= totalNumberOfEssays / totalNumberOfAuthors ;
		

		return result;
	}
	
	public Integer uniqueTitle(String title,int contestId){
		Integer result;
		Collection<Essay> list;
		
		list=essayRepository.uniqueTitle(title,contestId);
		
		result= list.size();
		
		return result;
	}

	public Collection<Essay> findAllEssaysToAddToAPublicSession(int publicSessionId){
		Collection<Essay> result;
		Collection<Essay> essays;
		
		result=essayRepository.findAllEssaysByContestOfPublicSession(publicSessionId);
		essays=essayRepository.findAllEssaysWithPublicSession();
		
		result.removeAll(essays);
		
		return result;
	}

}
