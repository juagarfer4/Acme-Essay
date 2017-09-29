package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class PublicSession extends DomainEntity{
	
	// Constructors ------------------------------

	public PublicSession() {
		super();
	}
	
	// Attributes -------------------------------
	
	private Date startMoment;
	private Date endMoment;
	private int capacity;
	private int limitOfEssays;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	public Date getStartMoment() {
		return startMoment;
	}
	public void setStartMoment(Date startMoment) {
		this.startMoment = startMoment;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	public Date getEndMoment() {
		return endMoment;
	}
	public void setEndMoment(Date endMoment) {
		this.endMoment = endMoment;
	}
	
	@Min(1)
	@NotNull
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Min(1)
	@NotNull
	public int getLimitOfEssays() {
		return limitOfEssays;
	}
	public void setLimitOfEssays(int limitOfEssays) {
		this.limitOfEssays = limitOfEssays;
	}
	
	// Relationships -------------------------------------
	
	private Contest contest;
	private Organiser chairman;
	private Collection<Essay> essays;
	private Collection<Repo> repos;

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Contest getContest() {
		return contest;
	}
	public void setContest(Contest contest) {
		this.contest = contest;
	}
	
	@Valid
	@ManyToOne(optional=true)
	public Organiser getChairman() {
		return chairman;
	}
	public void setChairman(Organiser chairman) {
		this.chairman = chairman;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="publicSession")
	public Collection<Essay> getEssays() {
		return essays;
	}
	public void setEssays(Collection<Essay> essays) {
		this.essays = essays;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="publicSession")
	public Collection<Repo> getRepos() {
		return repos;
	}
	public void setRepos(Collection<Repo> repos) {
		this.repos = repos;
	}
	
}
