package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Author;
import domain.CreditCard;

import repositories.AuthorRepository;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AuthorServiceNegativeTest extends AbstractTest {

	// Service under test -------------------------------------------------

	@Autowired
	private AuthorService authorService;

	@Autowired
	private AuthorRepository authorRepository;

	// Tests --------------------------------------------------------------

	// Invalid moment for the credit card. ------------

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeCreateAuthor() {

		Author author;
		String name;
		String surname;
		String email;
		String phone;
		Date birthDate;
		String nationality;
		UserAccount userAccount;
		String username;
		String password;
		CreditCard creditCard;
		String holderName;
		String brandName;
		String cardNumber;
		Integer expirationMonth;
		Integer expirationYear;
		Integer cVVCode;

		author = authorService.create();
		name = "name";
		surname = "surname";
		email = "email@us.es";
		phone = "900000000";
		birthDate = new Date(1980 - 1900, 12 - 1, 12);
		nationality = "Spanish";
		userAccount = author.getUserAccount();
		username = "username";
		password = "username";
		creditCard = author.getCreditCard();
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 1;
		expirationYear = 2015;
		cVVCode = 712;

		author.setName(name);
		author.setSurname(surname);
		author.setEmail(email);
		author.setPhone(phone);
		author.setBirthDate(birthDate);
		author.setNationality(nationality);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		authorService.save(author);
	}

	// Wrong email. -----------------------

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreateAuthorEmail() {

		Author author;
		String name;
		String surname;
		String email;
		String phone;
		Date birthDate;
		String nationality;
		UserAccount userAccount;
		String username;
		String password;
		String homePage;
		CreditCard creditCard;
		String holderName;
		String brandName;
		String cardNumber;
		Integer expirationMonth;
		Integer expirationYear;
		Integer cVVCode;

		author = authorService.create();
		name = "name";
		surname = "surname";
		email = "emailuses";
		phone = "900000000";
		birthDate = new Date(1980 - 1900, 12 - 1, 12);
		nationality = "Spanish";
		userAccount = author.getUserAccount();
		username = "username";
		password = "username";
		creditCard = author.getCreditCard();
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 1;
		expirationYear = 2019;
		cVVCode = 712;

		author.setName(name);
		author.setSurname(surname);
		author.setEmail(email);
		author.setPhone(phone);
		author.setBirthDate(birthDate);
		author.setNationality(nationality);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		authorService.save(author);
		authorRepository.flush();
	}

	// Wrong homepage. -----------------------

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreateAuthorHomepage() {

		Author author;
		String name;
		String surname;
		String email;
		String phone;
		Date birthDate;
		String nationality;
		UserAccount userAccount;
		String username;
		String password;
		String homePage;
		CreditCard creditCard;
		String holderName;
		String brandName;
		String cardNumber;
		Integer expirationMonth;
		Integer expirationYear;
		Integer cVVCode;

		author = authorService.create();
		name = "name";
		surname = "surname";
		email = "email@us.es";
		phone = "900000000";
		homePage = "sndbdfmbdfjdfb";
		birthDate = new Date(1980 - 1900, 12 - 1, 12);
		nationality = "Spanish";
		userAccount = author.getUserAccount();
		username = "username";
		password = "username";
		creditCard = author.getCreditCard();
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 1;
		expirationYear = 2019;
		cVVCode = 712;

		author.setName(name);
		author.setSurname(surname);
		author.setEmail(email);
		author.setPhone(phone);
		author.setBirthDate(birthDate);
		author.setNationality(nationality);
		author.setHomePage(homePage);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		authorService.save(author);
		authorRepository.flush();
	}

	// Repeated username. -----------------------

	@Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
	public void testNegativeCreatePilgrimUsername() {

		Author author;
		String name;
		String surname;
		String email;
		String phone;
		Date birthDate;
		String nationality;
		UserAccount userAccount;
		String username;
		String password;
		CreditCard creditCard;
		String holderName;
		String brandName;
		String cardNumber;
		Integer expirationMonth;
		Integer expirationYear;
		Integer cVVCode;

		author = authorService.create();
		name = "name";
		surname = "surname";
		email = "email@us.es";
		phone = "900000000";
		birthDate = new Date(1980 - 1900, 12 - 1, 12);
		nationality = "Spanish";
		userAccount = author.getUserAccount();
		username = "author1";
		password = "username";
		creditCard = author.getCreditCard();
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 1;
		expirationYear = 2019;
		cVVCode = 712;

		author.setName(name);
		author.setSurname(surname);
		author.setEmail(email);
		author.setPhone(phone);
		author.setBirthDate(birthDate);
		author.setNationality(nationality);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		authorService.save(author);
		authorRepository.flush();
	
	}
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeListAuthors(){
		Collection<Author> authors;
		Integer size;
		
		authors=authorService.findAll();
		size=authors.size();
		
		Assert.isTrue(size==3);
	}
	
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeFindAllAuthorMoreEssaysSubmitted(){
		List<Author> authors;
		
		authors=new ArrayList<Author>(authorService.findAllAuthorMoreEssaysSubmitted());
		
		
		Assert.isTrue(authors.get(0).getName().equals("nameAuthor2"));
	}
	
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeFindAllAuthorMoreEssaysPublished(){
		List<Author> authors;
		
		authors=new ArrayList<Author>(authorService.findAllAuthorMoreEssaysPublished());
		
		
		Assert.isTrue(authors.get(0).getName().equals("nameAuthor2"));
	}
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeFindAllAuthorLessEssaysPublished(){
		List<Author> authors;
		
		authors=new ArrayList<Author>(authorService.findAllAuthorLessEssaysPublished());
		
		
		Assert.isTrue(authors.get(0).getName().equals("nameAuthor2"));
	}

}
