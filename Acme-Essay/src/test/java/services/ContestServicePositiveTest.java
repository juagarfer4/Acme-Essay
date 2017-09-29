package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.mchange.util.AssertException;

import domain.Administrator;
import domain.Author;
import domain.Contest;
import domain.CreditCard;
import domain.Essay;
import domain.Organiser;

import repositories.ContestRepository;
import repositories.EssayRepository;
import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ContestServicePositiveTest extends AbstractTest {

	// Service under test ------------------------------------------------

	@Autowired
	private ContestService contestService;
	
	@Autowired
	private OrganiserService organiserService;

	@Autowired
	private ContestRepository contestRepository;

	// Tests --------------------------------------------------------------

	// Create An Contest

	@Test
	public void testPositiveCreateContest() {
		this.authenticate("organiser1");

		Contest contest;
		String name;
		String description;
		Date holdingDate;
		Date deadline;
		String results;

		contest = contestService.create();
		name = "contest";
		description = "descriptionContest";
		holdingDate = new Date(2016 - 1900, 5 - 1, 5);
		deadline = new Date(2016 - 1900, 4 - 1, 4);

		contest.setName(name);
		contest.setDescription(description);
		contest.setHoldingDate(holdingDate);
		contest.setDeadline(deadline);

		contestService.save(contest);
		contestRepository.flush();

		this.unauthenticate();

	}

	// Test positivo de la media de concursos por organizador
	@Test
	public void testPositiveAverageNumberContestsByOrganiser() {
		authenticate("administrator1");

		Double result;

		result = contestService.averageNumberContestsByOrganiser();

		Assert.isTrue(result == 1.0);

		unauthenticate();
	}

	// Test positivo de la media de concursos que se celebraron el mes pasado
	@Test
	public void testPositiveFindAllContestsHeldDuringLastMonth() {
		authenticate("administrator1");
		Collection<Contest> result;

		result = contestService.findAllContestsHeldDuringLastMonth();
		Assert.isTrue(result.size() == 0);

		unauthenticate();
	}

	// Test positivo de Listar todos los concursos ordenados por numero
	// descendente de ensayos registrados.
	@Test
	public void testPositiveFindAllContestDescOrderNumberSubmitted() {
		authenticate("administrator1");
		List<Contest> result;

		result = new ArrayList<Contest>(
				contestService.findAllContestDescOrderNumberSubmitted());
		Assert.isTrue(result.get(0).getName().equals("nameContest1"));

		unauthenticate();
	}

	// Test positivo delete
	@Test
	public void testPositiveDelete() {
		authenticate("organiser2");
		Contest contest;

		contest = contestService.findOne(18);
		contestService.delete(contest);

		unauthenticate();
	}

	// Test positivo: añadir los resultados a un certamen pasado. ------

	@Test()
	public void testPositiveEditContestResults() {
		authenticate("organiser1");
		Contest contest;
		String results;

		contest = contestService.findOne(17);
		results = "results";

		contest.setResults(results);

		contestService.save(contest);

		unauthenticate();
	}
	
	// Add another organiser to the contests that he or she organises. ----
	
	@Test()
	public void testPositiveAddOrganiser(){
		authenticate("organiser1");
		
		Contest contest;
		Collection<Organiser> organisers;
		Organiser organiser;
		Collection<Contest> contests;
		
		contest=contestService.findOne(17);
		organisers=contest.getOrganisers();
		organiser=organiserService.findOne(14);
		contests=organiser.getContests();
		
		organisers.add(organiser);
		contests.add(contest);
		
		unauthenticate();
	}
	
	// The list of contests with their public sessions, in descending order of number of essays that have been submitted. ------
	
	@Test()
	public void testPositiveDashboardContests(){
		authenticate("administrator1");
		
		Collection<Contest> contests;
		Integer size;
		
		contests=contestService.findAllContestDescOrderOfEssays();
		size=contests.size();
		
		Assert.isTrue(size==2);
		
		unauthenticate();
	}

}
