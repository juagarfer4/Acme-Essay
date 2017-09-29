package services;


import java.util.ArrayList;
import java.util.Collection;

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

import domain.CreditCard;
import domain.Repo;

import repositories.RepoRepository;
import security.LoginService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RepoServiceNegativeTest extends AbstractTest {

	// Service under test ------------------------------------------------

	@Autowired
	private RepoService repoService;

	// Tests --------------------------------------------------------------
	
	// Delete a repo without being the organiser of the contest of its public session. ------
	@Test( expected  =  java.lang.IllegalArgumentException.class)
	public void testNegativeDeleteRepo() {
		authenticate("organiser2");
		
		Repo repo;
		
		repo=repoService.findOne(22);
		
		repoService.delete(repo);
		
		unauthenticate();
	}
}
