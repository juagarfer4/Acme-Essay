package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Contest;
import domain.Organiser;
import domain.PublicSession;
import domain.Repo;

import repositories.RepoRepository;

@Service
@Transactional
public class RepoService {
	
	// Managed repository ---------------------------------
	
	@Autowired
	private RepoRepository repoRepository;
	
	// Supporting services -------------------------------
	
	@Autowired
	private PublicSessionService publicSessionService;
	
	@Autowired
	private OrganiserService organiserService;
	
	// Constructors --------------------------------------
	
	public RepoService(){
		super();
	}
	
	// Simple CRUD methods -------------------------------
	
	public Repo create(Integer publicSessionId){
		PublicSession publicSession;
		
		publicSession=publicSessionService.findOne(publicSessionId);
		
		Assert.notNull(publicSession);
		
		Repo result;
		
		result=new Repo();
		
		result.setPublicSession(publicSession);
		
		return result;
	}
	
	public Repo findOne(Integer repoId){
		Repo result;
		
		result=repoRepository.findOne(repoId);
		
		checkPrincipal(result);
		
		return result;
	}
	
	public void save(Repo repo){
		checkPrincipal(repo);
		
		Date date;
		PublicSession publicSession;
		Date startMoment;
		
		date=new Date(System.currentTimeMillis()-1);
		publicSession=repo.getPublicSession();
		startMoment=publicSession.getStartMoment();
		
		Assert.isTrue(date.before(startMoment));
		
		repoRepository.save(repo);
	}
	
	public void delete(Repo repo){
		checkPrincipal(repo);
		
		Date date;
		PublicSession publicSession;
		Date startMoment;
		
		date=new Date(System.currentTimeMillis()-1);
		publicSession=repo.getPublicSession();
		startMoment=publicSession.getStartMoment();
		
		Assert.isTrue(date.before(startMoment));
		
		repoRepository.delete(repo);
	}
	
	// Other business methods --------------------------------
	
	public void checkPrincipal(Repo repo){
		Assert.notNull(repo);
		
		PublicSession publicSession;
		Contest contest;
		Collection<Organiser> organisers;
		Organiser organiser;
		
		publicSession=repo.getPublicSession();
		contest=publicSession.getContest();
		organisers=contest.getOrganisers();
		organiser=organiserService.findByPrincipal();
		
		Assert.isTrue(organisers.contains(organiser));
	}
	
	public Collection<Repo> findAllReposByPublicSession(Integer publicSessionId){
		PublicSession publicSession;
		Contest contest;
		Collection<Organiser> organisers;
		Organiser organiser;
		
		publicSession=publicSessionService.findOne(publicSessionId);
		contest=publicSession.getContest();
		organisers=contest.getOrganisers();
		organiser=organiserService.findByPrincipal();
		
		Assert.isTrue(organisers.contains(organiser));
		
		Collection<Repo> result;
		
		result=repoRepository.findAllReposByPublicSession(publicSessionId);
		
		return result;
	}

}
