package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Contest;
import domain.CreditCard;
import domain.Organiser;
import domain.PublicSession;
import forms.OrganiserForm;

import repositories.OrganiserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class OrganiserService {

	// Managed repository ---------------------------------

	@Autowired
	private OrganiserRepository organiserRepository;

	// Supporting services ---------------------------------

	@Autowired
	private CreditCardService creditCardService;

	// Constructors ----------------------------------------

	public OrganiserService() {
		super();
	}

	// Simple CRUD methods ----------------------------------
	
	public Organiser create(){
		Organiser result;
		UserAccount userAccount;
		Collection<Authority> authorities;
		Authority authority;
		CreditCard creditCard;
		Collection<Contest> contests;
		Collection<PublicSession> publicSessions;
		
		result=new Organiser();
		userAccount=new UserAccount();
		authorities=new ArrayList<Authority>();
		authority=new Authority();
		creditCard=new CreditCard();
		contests=new ArrayList<Contest>();
		publicSessions = new ArrayList<PublicSession>();
		
		authority.setAuthority("ORGANISER");
		
		authorities.add(authority);
		
		userAccount.setAuthorities(authorities);
		result.setUserAccount(userAccount);
		result.setCreditCard(creditCard);
		result.setContests(contests);
		result.setPublicSessions(publicSessions);
		
		return result;
	}
	
	public Organiser findOne(Integer organiserId){
		Organiser result;
		
		result=organiserRepository.findOne(organiserId);
		
		return result;
	}
	
	public Collection<Organiser> findAll(){
		Collection<Organiser> result;
		
		result=organiserRepository.findAll();
		
		return result;
	}
	
	public void save(Organiser organiser){
		Assert.notNull(organiser);
		
		this.checkCreditCard(organiser);
		this.encodePassword(organiser);
		
		organiserRepository.save(organiser);
		
		CreditCard creditCard;
		
		creditCard=organiser.getCreditCard();
		
		creditCardService.save(creditCard);
	}
	
	// Other business methods --------------------------------------
	
	public Organiser findByPrincipal() {
		Organiser result;
		UserAccount userAccount;
		Integer userAccountId;

		userAccount = LoginService.getPrincipal();

		Assert.notNull(userAccount);

		userAccountId = userAccount.getId();

		result = organiserRepository.findByUserAccountId(userAccountId);

		Assert.notNull(result);

		return result;
	}
	
	public void checkCreditCard(Organiser organiser){
		CreditCard cc = organiser.getCreditCard();
		Calendar d = new GregorianCalendar();

		Assert.isTrue(cc.getExpirationYear() >= d.get(Calendar.YEAR));
		if (cc.getExpirationYear() == d.get(Calendar.YEAR)) Assert.isTrue(cc.getExpirationMonth() > d.get(Calendar.MONTH) + 1);		
	}
	
	public void encodePassword(Organiser organiser){
		UserAccount userAccount;
		String password;
		Md5PasswordEncoder encoder;
		
		encoder= new Md5PasswordEncoder();
		userAccount=organiser.getUserAccount();
		password=userAccount.getPassword();
		password=encoder.encodePassword(password, null);
		userAccount.setPassword(password);
	}

	public Organiser reconstruct(OrganiserForm organiserForm) {
		Organiser organiser = this.create();
		
		Assert.isTrue(organiserForm.isCondition());
		Assert.isTrue(organiserForm.getPassword().equals(organiserForm.getPasswordVerificada()));
		
		organiser.setName(organiserForm.getName());
		organiser.setSurname(organiserForm.getSurname());
		organiser.setEmail(organiserForm.getEmail());
		organiser.setPhone(organiserForm.getPhone());
		organiser.setHomePage(organiserForm.getHomePage());
		organiser.getUserAccount().setPassword(organiserForm.getPassword());
		organiser.getUserAccount().setUsername(organiserForm.getUsername());
		organiser.setCreditCard(organiserForm.getCreditCard());
		organiser.setNationality(organiserForm.getNationality());
		organiser.setBirthDate(organiserForm.getBirthDate());
		
		this.checkCreditCard(organiser);
		
		return organiser;
	}
	
	public Object[] findAllChairmans(){
		Object[] result;
		
		result=organiserRepository.findAllChairmans();
		
		return result;
	}

	public Collection<Organiser> findAllOrganisersChairmen() {
		Collection<Organiser> result;
		
		result= this.organiserRepository.findAllOrganisersChairmen();
		
		return result;
	}

}
