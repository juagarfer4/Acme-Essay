package services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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

import repositories.EssayRepository;
import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class EssayServicePositiveTest extends AbstractTest {

	// Service under test ------------------------------------------------

	
	@Autowired
	private EssayService essayService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private EssayRepository essayRepository;
	
	
	// Tests --------------------------------------------------------------

	//Create An Essay
	
	@Test
	public void testPositiveCreateEssay() {
		this.authenticate("author1");
		
		Essay essay;
		String title;
		String optionalAbstract;
		String contents;

		
		
		essay = essayService.create(18);
		title = "title";
		optionalAbstract = "optionalAbstract";
		contents = "contents";

		essay.setTitle(title);
		essay.setOptionalAbstract(optionalAbstract);
		essay.setContents(contents);

		essayService.saveAuthor(essay);
		essayRepository.flush();
		
		this.unauthenticate();
		
	}
	
	//Test positivo de encontrar todos los ensayos por autor
	@Test
	public void testPositiveFindAllEssaysByAuthor(){
		authenticate("author1");
		
		Collection<Essay> essay;
		Integer size;
		
		essay=essayService.findAllEssaysByAuthor(17);
		size=essay.size();
		
		Assert.isTrue(size==1);
		
		unauthenticate();
	}
	
	//Test positivo de la media de ensayos por autor
	@Test
	public void testPositiveAverageNumberEssaysByAuthor(){
		authenticate("administrator1");
		
		Double result;
		
		result=essayService.averageNumberEssaysByAuthor();
		
		Assert.isTrue(result == 1.0);
		
		unauthenticate();
	}
	
	 
	
	
	
}
