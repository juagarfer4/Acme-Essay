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
import domain.Essay;

import repositories.EssayRepository;
import security.LoginService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class EssayServiceNegativeTest extends AbstractTest {

	// Service under test ------------------------------------------------

	
	@Autowired
	private EssayService essayService;
	
	
	@Autowired
	private EssayRepository essayRepository;

	// Tests --------------------------------------------------------------
	
	//Crear Ensayo sin estar autenticado
	@Test( expected  =  java.lang.IllegalArgumentException.class)
	public void testNegativeCreateEssay() {
		
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
	
	//Crear Ensayo en un concurso con fecha pasada
		@Test( expected  =  java.lang.IllegalArgumentException.class)
		public void testNegativeCreateEssayRepeatedTitle() {
			this.authenticate("author1");
			
			Essay essay;
			String title;
			String optionalAbstract;
			String contents;

			
			
			essay = essayService.create(17);
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
		//Test negativo de encontrar todos los ensayos por autor
		//El tamaño de la lista devuelta es incorrecto
		@Test( expected  =  java.lang.IllegalArgumentException.class)
		public void testPositiveFindAllEssaysByAuthor(){
			authenticate("author1");
			
			Collection<Essay> essay;
			Integer size;
			
			essay=essayService.findAllEssaysByAuthor(17);
			size=essay.size();
			
			Assert.isTrue(size==3);
			
			unauthenticate();
		}
		
		
		//Test negativo del metodo de la media de ensayos por autor
		//comprobando que la media es incorrecta
		@Test( expected  =  java.lang.IllegalArgumentException.class)
		public void testNegativeAverageNumberEssaysByAuthor(){
			authenticate("administrator1");
			
			Double result;
			
			result=essayService.averageNumberEssaysByAuthor();
			
			Assert.isTrue(result == 3.0);
			
			unauthenticate();
		}
}
