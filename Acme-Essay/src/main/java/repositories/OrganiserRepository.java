package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Organiser;

@Repository
public interface OrganiserRepository extends JpaRepository<Organiser, Integer>{
	
	@Query("select o from Organiser o where o.userAccount.id=?1")
	Organiser findByUserAccountId(int userAccountId);

	@Query("select o, o.publicSessions.size from Organiser o where o.publicSessions.size>0")
	Object[] findAllChairmans();

	
	@Query("select o from Organiser o where o.publicSessions.size>0")
	Collection<Organiser> findAllOrganisersChairmen();
}
