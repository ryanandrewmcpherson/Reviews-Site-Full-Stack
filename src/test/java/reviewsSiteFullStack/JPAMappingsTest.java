package reviewsSiteFullStack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private TagRepository tagRepo;
	
    @Resource
    private ReviewRepository reviewRepo;
    
    @Resource
    private CategoryRepository categoryRepo;

	@Test
	public void shouldSaveAndLoadTag() {
		Tag tag = tagRepo.save(new Tag("tag"));
		Long tagId = tag.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Tag> result = tagRepo.findById(tagId);
		tag = result.get();
		assertThat(tag.getName(), is("tag"));

	}

	@Test
	public void shouldGenerateTopicId() {
		Tag tag = tagRepo.save(new Tag("tag"));
		Long tagId = tag.getId();

		entityManager.flush();
		entityManager.clear();
		
		assertThat(tagId,is(greaterThan(0L)));
	}

	
	@Test
	public void shouldSaveAndLoadReview(){
		Review review = reviewRepo.save(new Review(categoryRepo.save(new Category()),"title","body","picture"));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getTitle(), is("title"));
	}
	@Test
	public void shouldGenerateReviewId() {
		Review review = reviewRepo.save(new Review());
		Long reviewId = review.getId();

		entityManager.flush();
		entityManager.clear();
		
		assertThat(reviewId,is(greaterThan(0L)));
	}
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getName(), is("category"));
	}
	@Test
	public void shouldGenerateCategoryId() {
		Category category = categoryRepo.save(new Category("category"));
		Long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();
		
		assertThat(categoryId,is(greaterThan(0L)));
	}
	@Test
	public void shouldEstablishReviewToTagRelationship() {
		Tag tag = tagRepo.save(new Tag("tag"));
		Tag tag2 = tagRepo.save(new Tag("tag2"));
		
		Review review = reviewRepo.save(new Review(categoryRepo.save(new Category()),"title","body","picture",tag,tag2));
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getTags(),containsInAnyOrder(tag,tag2));
	}
	
	@Test
	public void shouldFindReviewsForTags() {
		Tag tag = tagRepo.save(new Tag("tag"));
		
		Review review = reviewRepo.save(new Review(categoryRepo.save(new Category()),"title","body","picture",tag));
		Review review2 = reviewRepo.save(new Review(categoryRepo.save(new Category()),"title","body","picture",tag));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsforTag = reviewRepo.findByTagsContains(tag);
		
		assertThat(reviewsforTag,containsInAnyOrder(review,review2));
	}
	
	@Test
	public void shouldFindReviewsForCategoryId(){
		Tag tag = tagRepo.save(new Tag("tag"));
		Long tagId = tag.getId();
		
		Review review = reviewRepo.save(new Review(categoryRepo.save(new Category()),"title","body","picture",tag));
		Review review2 = reviewRepo.save(new Review(categoryRepo.save(new Category()),"title","body","picture",tag));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsforTagId = reviewRepo.findByTagsId(tagId);
		
		assertThat(reviewsforTagId,containsInAnyOrder(review,review2));
		
	}
	@Test 
	public void shouldEstablishReviewToCategoryRelationship(){
		
		Category category = new Category();
		categoryRepo.save(category);
		Long categoryId = category.getId();
		
		Review review = reviewRepo.save(new Review(category,"title","body","picture"));
		Review review2 = reviewRepo.save(new Review(category,"title","body","picture"));
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getReviews(),containsInAnyOrder(review,review2));
	}

}
