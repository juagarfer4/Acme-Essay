package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table( indexes = {@Index (columnList = "contest_id")})
public class Essay extends DomainEntity {

	// Constructors ----------------------------

	public Essay() {
		super();
	}

	// Attributes ----------------------------

	private String title;
	private String optionalAbstract;
	private String contents;
	private Date submissionDate;
	private Boolean published;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getOptionalAbstract() {
		return optionalAbstract;
	}
	public void setOptionalAbstract(String optionalAbstract) {
		this.optionalAbstract = optionalAbstract;
	}
	
	@NotBlank
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	@Past
	public Date getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	
	@NotNull
	public Boolean getPublished() {
		return published;
	}
	public void setPublished(Boolean published) {
		this.published = published;
	}
	
	// Relationships ------------------------

	private Author author;
	private Contest contest;
	private PublicSession publicSession;

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
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
	public PublicSession getPublicSession() {
		return publicSession;
	}
	public void setPublicSession(PublicSession publicSession) {
		this.publicSession = publicSession;
	}
	
}
