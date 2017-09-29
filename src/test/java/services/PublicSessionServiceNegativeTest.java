package services;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PublicSessionRepository;

import domain.Essay;
import domain.Organiser;
import domain.PublicSession;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PublicSessionServiceNegativeTest extends AbstractTest {

	// Services under test --------------------------------

	@Autowired
	private PublicSessionService publicSessionService;

	@Autowired
	private EssayService essayService;

	@Autowired
	private OrganiserService organiserService;

	@Autowired
	private PublicSessionRepository publicSessionRepository;

	// Test ----------------------------------------------

	// Create a public session with end moment before start moment. ----

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeCreatePublicSessionDates() {
		this.authenticate("organiser1");

		PublicSession publicSession;
		Date startMoment;
		Date endMoment;
		Integer capacity;
		Integer limitOfEssays;

		publicSession = publicSessionService.create(17);
		startMoment = new Date(2018 - 1900, 5 - 1, 5);
		endMoment = new Date(2017 - 1900, 5 - 1, 5);
		capacity = 10;
		limitOfEssays = 10;

		publicSession.setStartMoment(startMoment);
		publicSession.setEndMoment(endMoment);
		publicSession.setCapacity(capacity);
		publicSession.setLimitOfEssays(limitOfEssays);

		publicSessionService.save(publicSession);

		unauthenticate();
	}

	// Create a public session with negative capacity. ----

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreatePublicSessionNegativeCapacity() {
		this.authenticate("organiser1");

		PublicSession publicSession;
		Date startMoment;
		Date endMoment;
		Integer capacity;
		Integer limitOfEssays;

		publicSession = publicSessionService.create(17);
		startMoment = new Date(2017 - 1900, 5 - 1, 5);
		endMoment = new Date(2018 - 1900, 5 - 1, 5);
		capacity = -1;
		limitOfEssays = 10;

		publicSession.setStartMoment(startMoment);
		publicSession.setEndMoment(endMoment);
		publicSession.setCapacity(capacity);
		publicSession.setLimitOfEssays(limitOfEssays);

		publicSessionService.save(publicSession);
		publicSessionRepository.flush();

		unauthenticate();
	}

	// Create a public session with zero capacity. ----

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreatePublicSessionZeroCapacity() {
		this.authenticate("organiser1");

		PublicSession publicSession;
		Date startMoment;
		Date endMoment;
		Integer capacity;
		Integer limitOfEssays;

		publicSession = publicSessionService.create(17);
		startMoment = new Date(2017 - 1900, 5 - 1, 5);
		endMoment = new Date(2018 - 1900, 5 - 1, 5);
		capacity = 0;
		limitOfEssays = 10;

		publicSession.setStartMoment(startMoment);
		publicSession.setEndMoment(endMoment);
		publicSession.setCapacity(capacity);
		publicSession.setLimitOfEssays(limitOfEssays);

		publicSessionService.save(publicSession);
		publicSessionRepository.flush();

		unauthenticate();
	}

	// Create a public session with negative limit of essays. ----

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeCreatePublicSessionNegativeLimitOfEssays() {
		this.authenticate("organiser1");

		PublicSession publicSession;
		Date startMoment;
		Date endMoment;
		Integer capacity;
		Integer limitOfEssays;

		publicSession = publicSessionService.create(17);
		startMoment = new Date(2017 - 1900, 5 - 1, 5);
		endMoment = new Date(2018 - 1900, 5 - 1, 5);
		capacity = 10;
		limitOfEssays = -1;

		publicSession.setStartMoment(startMoment);
		publicSession.setEndMoment(endMoment);
		publicSession.setCapacity(capacity);
		publicSession.setLimitOfEssays(limitOfEssays);

		publicSessionService.save(publicSession);
		publicSessionRepository.flush();

		unauthenticate();
	}

	// Create a public session with zero limit of essays. ----

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreatePublicSessionZeroLimitOfEssays() {
		this.authenticate("organiser1");

		PublicSession publicSession;
		Date startMoment;
		Date endMoment;
		Integer capacity;
		Integer limitOfEssays;

		publicSession = publicSessionService.create(17);
		startMoment = new Date(2017 - 1900, 5 - 1, 5);
		endMoment = new Date(2018 - 1900, 5 - 1, 5);
		capacity = 10;
		limitOfEssays = 0;

		publicSession.setStartMoment(startMoment);
		publicSession.setEndMoment(endMoment);
		publicSession.setCapacity(capacity);
		publicSession.setLimitOfEssays(limitOfEssays);

		publicSessionService.save(publicSession);
		publicSessionRepository.flush();

		unauthenticate();
	}

	// Adding an essay to a public session exceding the limit of essays. --

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testPositiveEditPublicSession() {
		this.authenticate("organiser1");

		PublicSession publicSession;
		Collection<Essay> essays;
		Essay essay;

		publicSession = publicSessionService.findOne(19);
		essays = publicSession.getEssays();
		essay = essayService.findOne(21);

		essays.add(essay);
		essay.setPublicSession(publicSession);

		publicSessionService.save(publicSession);

		unauthenticate();
	}

	// The list of contests with their public sessions, in descending order of
	// number of essays that have been submitted: wrong size. ------

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeDashboardContestsWrongSize() {
		authenticate("administrator1");

		Collection<PublicSession> publicSessions;
		Integer size;

		publicSessions = publicSessionService
				.findAllPublicSessionsDescOrderOfEssays();
		size = publicSessions.size();

		Assert.isTrue(size == 54);

		unauthenticate();
	}

	// The chairman is not an organiser of the contest. ---

	@Test(expected=java.lang.NullPointerException.class)
	public void testNegativeSetChairmanWrongOrganiser() {
		authenticate("organiser1");

		PublicSession publicSession;
		Organiser chairman;

		publicSession = publicSessionService.findOne(20);
		chairman = organiserService.findOne(14);

		publicSession.setChairman(chairman);

		publicSessionService.save(publicSession);

		unauthenticate();
	}

	// The list of organisers who have to be chairmen, including the number of
	// public sessions to chair: wrong size. ------

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeDashboardChairmenWrongSize() {
		authenticate("administrator1");

		Collection<Organiser> chairmen;
		Integer size;

		chairmen = organiserService.findAllOrganisersChairmen();
		size = chairmen.size();

		Assert.isTrue(size == 34);

		unauthenticate();
	}

	// Show the public sessions with an assigned chairman in descending order of
	// the session capacity: wrong size. ------

	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testNegativeDashboardAssignedChairmanWrongSize() {
		authenticate("administrator1");

		Collection<PublicSession> publicSessions;
		Integer size;

		publicSessions = publicSessionService.findAllPublicSessionsWithChairman();
		size = publicSessions.size();

		Assert.isTrue(size == 19);

		unauthenticate();
	}

}
