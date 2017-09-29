package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Repo;

@Repository
public interface RepoRepository extends JpaRepository<Repo, Integer>{
	
	@Query("select r from Repo r where r.publicSession.id=?1")
	Collection<Repo> findAllReposByPublicSession(int publicSessionId);

}
