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

import domain.Author;
import domain.CreditCard;
import domain.Essay;
import forms.AuthorForm;

import repositories.AuthorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AuthorService {

	// Managed repository ---------------------------------

	@Autowired
	private AuthorRepository authorRepository;

	// Supporting services ---------------------------------

	@Autowired
	private CreditCardService creditCardService;
	
	// Constructors ----------------------------------------
	
	public AuthorService(){
		super();
	}
	
	// Simple CRUD methods ----------------------------------
	
	public Author create(){
		Author result;
		UserAccount userAccount;
		Collection<Authority> authorities;
		Authority authority;
		CreditCard creditCard;
		Collection<Essay> essays;
		
		result=new Author();
		userAccount=new UserAccount();
		authorities=new ArrayList<Authority>();
		authority=new Authority();
		creditCard=new CreditCard();
		essays=new ArrayList<Essay>();
		
		authority.setAuthority("AUTHOR");
		
		authorities.add(authority);
		
		userAccount.setAuthorities(authorities);
		result.setUserAccount(userAccount);
		result.setCreditCard(creditCard);
		result.setEssays(essays);
		
		return result;
	}
	
	public Collection<Author> findAll(){
		Collection<Author> result;
		
		result=authorRepository.findAll();
		
		return result;
	}
	
	public void save(Author author){
		Assert.notNull(author);
		
		this.checkCreditCard(author);
		this.encodePassword(author);
		
		authorRepository.save(author);
		
		CreditCard creditCard;
		
		creditCard=author.getCreditCard();
		
		creditCardService.save(creditCard);
	}
	
	// Other business methods ---------------------------------------------
	
	public Author findByPrincipal() {
		Author result;
		UserAccount userAccount;
		Integer userAccountId;

		userAccount = LoginService.getPrincipal();

		Assert.notNull(userAccount);

		userAccountId = userAccount.getId();

		result = authorRepository.findByUserAccountId(userAccountId);

		Assert.notNull(result);

		return result;
	}
	
	public void checkCreditCard(Author author){
		CreditCard cc = author.getCreditCard();
		Calendar d = new GregorianCalendar();

		Assert.isTrue(cc.getExpirationYear() >= d.get(Calendar.YEAR));
		if (cc.getExpirationYear() == d.get(Calendar.YEAR)) Assert.isTrue(cc.getExpirationMonth() > d.get(Calendar.MONTH) + 1);		
	}
	
	public void encodePassword(Author Author){
		UserAccount userAccount;
		String password;
		Md5PasswordEncoder encoder;
		
		encoder= new Md5PasswordEncoder();
		userAccount=Author.getUserAccount();
		password=userAccount.getPassword();
		password=encoder.encodePassword(password, null);
		userAccount.setPassword(password);
	}

	public Author reconstruct(AuthorForm authorForm) {
		Author Author = this.create();
		
		Assert.isTrue(authorForm.isCondition());
		Assert.isTrue(authorForm.getPassword().equals(authorForm.getPasswordVerificada()));
		
		Author.setName(authorForm.getName());
		Author.setSurname(authorForm.getSurname());
		Author.setEmail(authorForm.getEmail());
		Author.setPhone(authorForm.getPhone());
		Author.setHomePage(authorForm.getHomePage());
		Author.getUserAccount().setPassword(authorForm.getPassword());
		Author.getUserAccount().setUsername(authorForm.getUsername());
		Author.setCreditCard(authorForm.getCreditCard());
		Author.setNationality(authorForm.getNationality());
		Author.setBirthDate(authorForm.getBirthDate());
		
		this.checkCreditCard(Author);
		
		return Author;
	}
	
	public Collection<Author> findAllAuthorMoreEssaysSubmitted() {
		Collection<Author> result;

		result = authorRepository.findAllAuthorMoreEssaysSubmitted();

		Assert.notNull(result);

		return result;
	}
	
	public Collection<Author> findAllAuthorMoreEssaysPublished() {
		Collection<Author> result;

		result = authorRepository.findAllAuthorMoreEssaysPublished();

		Assert.notNull(result);

		return result;
	}
	public Collection<Author> findAllAuthorLessEssaysPublished() {
		Collection<Author> result;

		result = authorRepository.findAllAuthorLessEssaysPublished();

		Assert.notNull(result);

		return result;
	}
}
