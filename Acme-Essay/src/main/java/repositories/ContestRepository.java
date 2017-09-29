
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Contest;
import domain.Organiser;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Integer>{
	
	@Query("select o.contests from Organiser o where o.userAccount.id=?1")
	Collection<Contest> findAllContestsByOrganiser(int userAccountId);
	
	@Query("select c from Contest c where month(current_date)-1=month(c.holdingDate)")
	Collection<Contest> findAllContestsHeldDuringLastMonth();
	
	@Query("select c from Contest c order by c.essays.size desc")
	Collection<Contest> findAllContestDescOrderNumberSubmitted();

	
	@Query("select c.organisers from Contest c where c.id=?1")
	Collection<Organiser> findAllOrganiserInThisContest(int contestId);
	
	@Query("select c.organisers from Contest c where c.id=?1")
	Collection<Organiser> findAllOrganisersByContest(int contestId);


	
}
