package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.PublicSession;

@Repository
public interface PublicSessionRepository extends JpaRepository<PublicSession, Integer>{
	
	@Query("select ps from PublicSession ps where ps.chairman is not null order by ps.capacity desc")
	Collection<PublicSession> findAllPublicSessionsWithChairman();

	@Query("select ps from PublicSession ps where ps.contest.id=?1")
	Collection<PublicSession> findAllPublicSessionsByContest(int contestId);

	@Query("select p from PublicSession p order by p.essays.size desc")
	Collection<PublicSession> findAllPublicSessionsDescOrderOfEssays();
	
}
