package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Contest extends DomainEntity{
	
	// Constructors ----------------------------
	
	public Contest(){
		super();
	}
	
	// Attributes ----------------------------
	
	private String name;
	private String description;
	private Date holdingDate;
	private Date deadline;
	private String results;
	private byte[] picture;
	
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	public Date getHoldingDate() {
		return holdingDate;
	}
	public void setHoldingDate(Date holdingDate) {
		this.holdingDate = holdingDate;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
	
	@Lob
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	@Transient
	public boolean getNotNullPicture(){
		boolean result;
		result =true;
		if (this.getPicture().length == 0){
			result =false;
		}
		else if (this.getPicture() == null ){
			result =false;
		}else{
			result =true;
		}
		return result;
	}
	
	// Relationships ----------------------------------

	private Collection<Organiser> organisers;
	private Collection<Essay> essays;
	private Collection<PublicSession> publicSessions;
	
	@Valid
	@NotNull
	@NotEmpty
	@ManyToMany
	public Collection<Organiser> getOrganisers(){
		return organisers;
	}
	public void setOrganisers(Collection<Organiser> organisers){
		this.organisers=organisers;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "contest")
	public Collection<Essay> getEssays() {
		return essays;
	}

	public void setEssays(Collection<Essay> essays) {
		this.essays = essays;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "contest")
	public Collection<PublicSession> getPublicSessions() {
		return publicSessions;
	}

	public void setPublicSessions(Collection<PublicSession> publicSessions) {
		this.publicSessions = publicSessions;
	}
	
}
