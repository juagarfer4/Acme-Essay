package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	@Query("select a from Author a where a.userAccount.id=?1")
	Author findByUserAccountId(int userAccountId);

	
	@Query("select a from Author a where a.essays.size = (select MAX(a2.essays.size) from Author a2)")
	Collection<Author> findAllAuthorMoreEssaysSubmitted();
	
	@Query("select a from Author a join a.essays e where e.published = true and a.essays.size = ( select max(au.essays.size) from Author au join au.essays es where es.published=true)")
	Collection<Author> findAllAuthorMoreEssaysPublished();
	
	@Query("select a from Author a join a.essays e where e.published = true and a.essays.size = ( select min(au.essays.size) from Author au join au.essays es where es.published=true)")
	Collection<Author> findAllAuthorLessEssaysPublished();
	
	
	
}
