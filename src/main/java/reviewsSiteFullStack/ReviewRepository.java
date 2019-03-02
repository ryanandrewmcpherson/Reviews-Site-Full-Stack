package reviewsSiteFullStack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review,Long> {

	Collection<Review> findByTagsContains(Tag tag);

	Collection<Review> findByTagsId(Long tagId);
	
	Collection<Review> findByCategory(Category category);

}
