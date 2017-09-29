package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Repo extends DomainEntity{
	
	// Constructors ---------------------------
	
	public Repo(){
		super();
	}
	
	// Relationships ------------------------
	
	private String name;
	private String remarks;
	
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	// Relationships ------------------------------
	
	private PublicSession publicSession;

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public PublicSession getPublicSession() {
		return publicSession;
	}
	public void setPublicSession(PublicSession publicSession) {
		this.publicSession = publicSession;
	}

}
