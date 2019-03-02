package reviewsSiteFullStack;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class ReviewControllerTest {
	
	@InjectMocks
	private ReviewController undertest;
	
	@Mock
	private Review review1;
	
	@Mock
	private Review review2;
	
	@Mock
	private Tag tag1;
	
	@Mock 
	private Tag tag2;
	
	@Mock
	private Category category1;
	
	@Mock
	private Category category2;
	
	@Mock
	ReviewRepository reviewRepo;
	
	@Mock
	TagRepository tagRepo;
	
	@Mock
	CategoryRepository categoryRepo;
	

	
	@Mock 
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review1));
		
		undertest.findOneReview(reviewId, model);
		verify(model).addAttribute("reviews",review1);
	}
	@Test 
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review1,review2);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		undertest.findAllReviews(model);
		verify(model).addAttribute("reviews",allReviews);
			
	}
	@Test
	public void shouldAddSingleTagToModel() throws TagNotFoundException{
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(tag1));
		
		undertest.findOneTag(tagId, model);
		verify(model).addAttribute("tags",tag1);
	}
	@Test
	public void shouldAddAllTagsToModel() {
		Collection<Tag> allTags = Arrays.asList(tag1,tag2);
		when(tagRepo.findAll()).thenReturn(allTags);
		
		undertest.findAllTags(model);
		verify(model).addAttribute("tags",allTags);
		
	}
	@Test
	public void shouldAddSingleCategoryToModel() throws CategoryNotFoundException{
		long categoryId = 1;
		when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category1));
		
		undertest.findOneCategory(categoryId, model);
		verify(model).addAttribute("categories",category1);
	}
	@Test
	public void shouldAddAllCategoriesToModel() {
		Collection<Category> allCategories = Arrays.asList(category1,category2);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		
		undertest.findAllCategories(model);
		verify(model).addAttribute("categories",allCategories);
		
	}

}
