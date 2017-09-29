package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Essay;

@Repository
public interface EssayRepository extends JpaRepository<Essay, Integer>{

	
	@Query("select e from Essay e where e.title=?1 and e.contest.id=?2")
	Collection<Essay> uniqueTitle(String title, int contestId);
	
	@Query("select e from Essay e where e.contest.id=?1")
	Collection<Essay> findAllEssaysByContest(int contestId);
	
	@Query("select e from Essay e where e.author.userAccount.id=?1 and e.contest.id=?2")
	Collection<Essay> findAllEssaysByAuthor(int userAccountId, int contestId);
	
	@Query("select ps.essays from PublicSession ps where ps.id=?1")
	Collection<Essay> findAllEssaysByPublicSession(int publicSessionId);
	
	@Query("select ps.contest.essays from PublicSession ps where ps.id=?1")
	Collection<Essay> findAllEssaysByContestOfPublicSession(int publicSessionId);
	
	@Query("select e from Essay e where e.publicSession is not null")
	Collection<Essay> findAllEssaysWithPublicSession();
	
}
