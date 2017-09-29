package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Organiser extends Customer{
	
	// Constructors ------------------------
	
	public Organiser(){
		super();
	}
	
	// Attributes -----------------------
	
	// Relationships -------------------
	
	private Collection<Contest> contests;
	private Collection<PublicSession> publicSessions;

	@Valid
	@NotNull
	@ManyToMany(mappedBy="organisers")
	public Collection<Contest> getContests() {
		return contests;
	}
	public void setContests(Collection<Contest> contests) {
		this.contests = contests;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="chairman")
	public Collection<PublicSession> getPublicSessions() {
		return publicSessions;
	}
	public void setPublicSessions(Collection<PublicSession> publicSessions) {
		this.publicSessions = publicSessions;
	}

}
