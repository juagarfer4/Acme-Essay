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

import domain.Contest;
import domain.CreditCard;
import domain.Essay;
import domain.Organiser;

import repositories.ContestRepository;
import repositories.EssayRepository;
import security.LoginService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ContestServiceNegativeTest extends AbstractTest {

	// Service under test ------------------------------------------------

	@Autowired
	private ContestService contestService;

	@Autowired
	private OrganiserService organiserService;

	@Autowired
	private ContestRepository contestRepository;

	// Tests --------------------------------------------------------------

	// Crear Contest sin estar autenticado
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeCreateContest() {

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

	// Crear concurso con una fecha de organizacion pasada
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeCreateContestWrongHoldingDate() {

		Contest contest;
		String name;
		String description;
		Date holdingDate;
		Date deadline;
		String results;

		contest = contestService.create();
		name = "contest";
		description = "descriptionContest";
		holdingDate = new Date(2014 - 1900, 5 - 1, 5);
		deadline = new Date(2016 - 1900, 4 - 1, 4);

		contest.setName(name);
		contest.setDescription(description);
		contest.setHoldingDate(holdingDate);
		contest.setDeadline(deadline);

		contestService.save(contest);
		contestRepository.flush();

		this.unauthenticate();
	}

	// Crear concurso con una fecha de deadLine anterior al holdingDate
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeCreateContestWrongDeadLine() {

		Contest contest;
		String name;
		String description;
		Date holdingDate;
		Date deadline;
		String results;

		contest = contestService.create();
		name = "contest";
		description = "descriptionContest";
		holdingDate = new Date(2016 - 1900, 4 - 1, 4);
		deadline = new Date(2016 - 1900, 2 - 1, 2);

		contest.setName(name);
		contest.setDescription(description);
		contest.setHoldingDate(holdingDate);
		contest.setDeadline(deadline);

		contestService.save(contest);
		contestRepository.flush();

		this.unauthenticate();
	}

	// Test negativo de la media de concursos por organizador
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeAverageNumberContestsByOrganiser() {
		authenticate("administrator1");

		Double result;

		result = contestService.averageNumberContestsByOrganiser();

		Assert.isTrue(result == 2.0);

		unauthenticate();
	}

	// Test negativo de la media de concursos que se celebraron el mes pasado
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNevativeFindAllContestsHeldDuringLastMonth() {
		authenticate("administrator1");
		Collection<Contest> result;

		result = contestService.findAllContestsHeldDuringLastMonth();
		Assert.isTrue(result.size() == 1);

		unauthenticate();
	}

	// Test negativo de Listar todos los concursos ordenados por numero
	// descendente de ensayos registrados.
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeFindAllContestDescOrderNumberSubmitted() {
		authenticate("administrator1");
		List<Contest> result;

		result = new ArrayList<Contest>(
				contestService.findAllContestDescOrderNumberSubmitted());
		Assert.isTrue(result.get(0).getName().equals("nameContest2"));

		unauthenticate();
	}

	// Test negativo delete borrando un concurso de otro organizador
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeDeleteNoOwner() {
		authenticate("organiser1");
		Contest contest;

		contest = contestService.findOne(18);
		contestService.delete(contest);

		unauthenticate();
	}

	// Test negativo delete borrando un concurso en el que hay ensayos. ------
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeDeletePassDate() {
		authenticate("organiser1");
		Contest contest;

		contest = contestService.findOne(17);
		contestService.delete(contest);

		unauthenticate();
	}

	// Test negativo: añadir los resultados a un certamen abierto. ------

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeEditContestResults() {
		authenticate("organiser2");
		Contest contest;
		String results;

		contest = contestService.findOne(18);
		results = "results";

		contest.setResults(results);

		contestService.save(contest);

		unauthenticate();
	}

	// Test negativo: actualizar las fechas y anteponer el holdingDate al
	// deadline. ------

	@Test(expected = java.lang.NullPointerException.class)
	public void testNegativeEditContestDates() {
		authenticate("organiser2");
		Contest contest;
		Date deadline;
		Date holdingDate;

		contest = contestService.findOne(18);
		deadline = new Date(2019 - 1900, 5 - 1, 5);
		holdingDate = new Date(2018 - 1900, 5 - 1, 5);

		contest.setDeadline(deadline);
		contest.setHoldingDate(holdingDate);

		contestService.save(contest);

		unauthenticate();
	}

	// Test negativo: añadir los resultados del certamen de otro. ------

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeEditContestResults2() {
		authenticate("organiser2");
		Contest contest;
		String results;

		contest = contestService.findOne(17);
		results = "results";

		contest.setResults(results);

		contestService.save(contest);

		unauthenticate();
	}

	// Add a nonexistent organiser to the contests that he or she organises.
	// ----

	@Test(expected = java.lang.NullPointerException.class)
	public void testPositiveAddOrganiser() {
		authenticate("organiser1");

		Contest contest;
		Collection<Organiser> organisers;
		Organiser organiser;
		Collection<Contest> contests;

		contest = contestService.findOne(17);
		organisers = contest.getOrganisers();
		organiser = organiserService.findOne(90);
		contests = organiser.getContests();

		organisers.add(organiser);
		contests.add(contest);

		contestService.save(contest);

		unauthenticate();
	}

	// The list of contests with their public sessions, in descending order of
	// number of essays that have been submitted: wrong size. ------

	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testNegativeDashboardContests() {
		authenticate("administrator1");

		Collection<Contest> contests;
		Integer size;

		contests = contestService.findAllContestDescOrderOfEssays();
		size = contests.size();

		Assert.isTrue(size == 37);

		unauthenticate();
	}
}
