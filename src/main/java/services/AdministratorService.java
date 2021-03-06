package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import forms.AdministratorForm;

import repositories.AdministratorRepository;
import security.Authority;
import security.UserAccount;

@Service
@Transactional
public class AdministratorService {

	// Managed repository ---------------------------------

	@Autowired
	private AdministratorRepository administratorRepository;

	// Supporting services ---------------------------------

	// Constructors ----------------------------------------

	public AdministratorService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------
	
	public Administrator create(){
		Administrator result;
		UserAccount userAccount;
		Collection<Authority> authorities;
		Authority authority;
		
		result=new Administrator();
		userAccount=new UserAccount();
		authorities=new ArrayList<Authority>();
		authority=new Authority();
		
		authority.setAuthority("ADMINISTRATOR");
		
		authorities.add(authority);
		
		userAccount.setAuthorities(authorities);
		result.setUserAccount(userAccount);
		
		return result;
	}
	
	public void save(Administrator administrator){
		Assert.notNull(administrator);
		
		this.encodePassword(administrator);
		
		administratorRepository.save(administrator);
	}
	
	// Other business methods ---------------------------------------------
	
	public void encodePassword(Administrator administrator){
		UserAccount userAccount;
		String password;
		Md5PasswordEncoder encoder;
		
		encoder= new Md5PasswordEncoder();
		userAccount=administrator.getUserAccount();
		password=userAccount.getPassword();
		password=encoder.encodePassword(password, null);
		userAccount.setPassword(password);
	}
	
	public Administrator reconstruct(AdministratorForm administratorForm) {
		Administrator administrator = this.create();
		
		Assert.isTrue(administratorForm.isCondition());
		Assert.isTrue(administratorForm.getPassword().equals(administratorForm.getPasswordVerificada()));
		
		administrator.setName(administratorForm.getName());
		administrator.setSurname(administratorForm.getSurname());
		administrator.setEmail(administratorForm.getEmail());
		administrator.setPhone(administratorForm.getPhone());
		administrator.setHomePage(administratorForm.getHomePage());
		administrator.getUserAccount().setPassword(administratorForm.getPassword());
		administrator.getUserAccount().setUsername(administratorForm.getUsername());

		return administrator;
	}

}
