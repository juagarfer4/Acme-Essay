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

import domain.Contest;
import domain.Essay;
import domain.Organiser;
import domain.PublicSession;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class PublicSessionServicePositiveTest extends AbstractTest {

	// Services under test ----------------------------------

	@Autowired
	private PublicSessionService publicSessionService;

	@Autowired
	private OrganiserService organiserService;

	@Autowired
	private EssayService essayService;

	// Tests ---------------------------------------------------

	// Create a public session. ----------------------------------

	@Test
	public void testPositiveCreatePublicSession() {
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
		limitOfEssays = 10;

		publicSession.setStartMoment(startMoment);
		publicSession.setEndMoment(endMoment);
		publicSession.setCapacity(capacity);
		publicSession.setLimitOfEssays(limitOfEssays);

		publicSessionService.save(publicSession);

		unauthenticate();
	}

	// Editing a public session. --------------------

	@Test()
	public void testPositiveEditPublicSession() {
		this.authenticate("organiser1");

		PublicSession publicSession;
		Date startMoment;
		Date endMoment;
		Integer capacity;
		Integer limitOfEssays;

		publicSession = publicSessionService.findOne(19);
		startMoment = new Date(2019 - 1900, 5 - 1, 5);
		endMoment = new Date(2020 - 1900, 5 - 1, 5);
		capacity = 100;
		limitOfEssays = 100;

		publicSession.setStartMoment(startMoment);
		publicSession.setEndMoment(endMoment);
		publicSession.setCapacity(capacity);
		publicSession.setLimitOfEssays(limitOfEssays);

		Essay essay;
		Collection<Essay> essays;

		essay = essayService.findOne(21);
		essays = publicSession.getEssays();

		essays.add(essay);
		essay.setPublicSession(publicSession);

		publicSessionService.save(publicSession);

		unauthenticate();
	}

	// The list of contests with their public sessions, in descending order of
	// number of essays that have been submitted. ------

	@Test()
	public void testPositiveDashboardContests() {
		authenticate("administrator1");

		Collection<PublicSession> publicSessions;
		Integer size;

		publicSessions = publicSessionService
				.findAllPublicSessionsDescOrderOfEssays();
		size = publicSessions.size();

		Assert.isTrue(size == 1);

		unauthenticate();
	}

	// Set the chairman of a public session. ------------

	@Test()
	public void testPositiveSetChairman() {
		authenticate("organiser1");

		PublicSession publicSession;
		Organiser chairman;
		
		publicSession=publicSessionService.findOne(19);
		chairman=organiserService.findOne(13);
		
		publicSession.setChairman(chairman);
		
		publicSessionService.save(publicSession);
		
		unauthenticate();
	}

	// The list of organisers who have to be chairmen, including the number of public sessions to chair. ------
	
	@Test()
	public void testPositiveDashboardChairmen(){
		authenticate("administrator1");
		
		Collection<Organiser> chairmen;
		Integer size;
		
		chairmen=organiserService.findAllOrganisersChairmen();
		size=chairmen.size();
		
		Assert.isTrue(size==1);
		
		unauthenticate();
	}
	
	// Show the public sessions with an assigned chairman in descending order of the session capacity. ------
	
	@Test()
	public void testPositiveDashboardAssignedChairman(){
		authenticate("administrator1");
		
		Collection<PublicSession> publicSessions;
		Integer size;
		
		publicSessions=publicSessionService.findAllPublicSessionsWithChairman();
		size=publicSessions.size();
		
		Assert.isTrue(size==1);
		
		unauthenticate();
	}
}
