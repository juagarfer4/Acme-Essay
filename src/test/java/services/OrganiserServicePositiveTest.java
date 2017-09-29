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

import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class OrganiserServicePositiveTest extends AbstractTest {

	// Service under test --------------------------------------

	@Autowired
	private OrganiserService organiserService;

	// Tests --------------------------------------------------

	// Register to the system as an organiser. ------------------------

	@Test()
	public void testPositiveCreateOrganiser() {

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
		expirationMonth = 4;
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
	}

}
