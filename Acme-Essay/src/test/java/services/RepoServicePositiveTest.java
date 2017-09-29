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
import domain.Repo;

import repositories.RepoRepository;
import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class RepoServicePositiveTest extends AbstractTest {

	// Service under test ------------------------------------------------

	
	@Autowired
	private RepoService repoService;
	
	@Autowired
	private AuthorService organiserService;
	
	@Autowired
	private RepoRepository repoRepository;
	
	
	// Tests --------------------------------------------------------------

	// Create a repo --------------
	
	@Test
	public void testPositiveCreateRepo() {
		authenticate("organiser1");
		
		Repo repo;
		
		repo=repoService.create(19);
		repo.setName("NAME");
		
		repoService.save(repo);
		
		unauthenticate();
	}
	
	 
	
	
	
}
