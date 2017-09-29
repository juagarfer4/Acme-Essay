package services;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Organiser;
import domain.CreditCard;

import repositories.OrganiserRepository;
import security.UserAccount;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OrganiserServiceNegativeTest extends AbstractTest {

	// Service under test -------------------------------------------------

	@Autowired
	private OrganiserService organiserService;

	@Autowired
	private OrganiserRepository organiserRepository;

	// Tests ----------------------------------------------------------------

	// Invalid moment for the credit card. ------------

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeCreateOrganiser() {

		Organiser organiser;
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

		organiser = organiserService.create();
		name = "name";
		surname = "surname";
		email = "email@us.es";
		phone = "900000000";
		birthDate = new Date(1980 - 1900, 12 - 1, 12);
		nationality = "Spanish";
		userAccount = organiser.getUserAccount();
		username = "username";
		password = "username";
		creditCard = organiser.getCreditCard();
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 1;
		expirationYear = 2015;
		cVVCode = 712;

		organiser.setName(name);
		organiser.setSurname(surname);
		organiser.setEmail(email);
		organiser.setPhone(phone);
		organiser.setBirthDate(birthDate);
		organiser.setNationality(nationality);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		organiserService.save(organiser);
	}

	// Wrong email. -----------------------

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreateOrganiserEmail() {

		Organiser organiser;
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

		organiser = organiserService.create();
		name = "name";
		surname = "surname";
		email = "emailuses";
		phone = "900000000";
		birthDate = new Date(1980 - 1900, 12 - 1, 12);
		nationality = "Spanish";
		userAccount = organiser.getUserAccount();
		username = "username";
		password = "username";
		creditCard = organiser.getCreditCard();
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 1;
		expirationYear = 2019;
		cVVCode = 712;

		organiser.setName(name);
		organiser.setSurname(surname);
		organiser.setEmail(email);
		organiser.setPhone(phone);
		organiser.setBirthDate(birthDate);
		organiser.setNationality(nationality);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		organiserService.save(organiser);
		organiserRepository.flush();
	}

	// Wrong homepage. -----------------------

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreateOrganiserHomepage() {

		Organiser organiser;
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

		organiser = organiserService.create();
		name = "name";
		surname = "surname";
		email = "email@us.es";
		phone = "900000000";
		homePage = "sndbdfmbdfjdfb";
		birthDate = new Date(1980 - 1900, 12 - 1, 12);
		nationality = "Spanish";
		userAccount = organiser.getUserAccount();
		username = "username";
		password = "username";
		creditCard = organiser.getCreditCard();
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 1;
		expirationYear = 2019;
		cVVCode = 712;

		organiser.setName(name);
		organiser.setSurname(surname);
		organiser.setEmail(email);
		organiser.setPhone(phone);
		organiser.setBirthDate(birthDate);
		organiser.setNationality(nationality);
		organiser.setHomePage(homePage);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		organiserService.save(organiser);
		organiserRepository.flush();
	}

	// Repeated username. -----------------------

	@Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
	public void testNegativeCreatePilgrimUsername() {

		Organiser organiser;
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

		organiser = organiserService.create();
		name = "name";
		surname = "surname";
		email = "email@us.es";
		phone = "900000000";
		birthDate = new Date(1980 - 1900, 12 - 1, 12);
		nationality = "Spanish";
		userAccount = organiser.getUserAccount();
		username = "organiser1";
		password = "username";
		creditCard = organiser.getCreditCard();
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 1;
		expirationYear = 2019;
		cVVCode = 712;

		organiser.setName(name);
		organiser.setSurname(surname);
		organiser.setEmail(email);
		organiser.setPhone(phone);
		organiser.setBirthDate(birthDate);
		organiser.setNationality(nationality);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		organiserService.save(organiser);
		organiserRepository.flush();

	}

}
