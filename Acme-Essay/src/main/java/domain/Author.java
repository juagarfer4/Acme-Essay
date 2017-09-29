package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Author extends Customer {

	// Constructors ------------------------

	public Author() {
		super();
	}

	// Attributes -----------------------

	// Relationships -------------------

	private Collection<Essay> essays;

	@Valid
	@NotNull
	@OneToMany(mappedBy = "author")
	public Collection<Essay> getEssays() {
		return essays;
	}

	public void setEssays(Collection<Essay> essays) {
		this.essays = essays;
	}

}
