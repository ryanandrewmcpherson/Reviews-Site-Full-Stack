package reviewsSiteFullStack;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
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
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@MockBean
	private TagRepository tagRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@Test
	public void shouldBeOkForAllReviews() throws Exception{
		mvc.perform(get("/show-reviews")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllReviewsView() throws Exception{
		mvc.perform(get("/show-reviews")).andExpect(view().name(is("reviews")));
	}
	
	@Test
	public void shouldPutAllReviewsIntoModel() throws Exception{
		Collection<Review>allReviews = asList(review1,review2);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews",allReviews));	
	}
	
	@Test
	public void shouldBeOkForOneReview() throws Exception{
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review1));
		
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldBeNotFoundForOneReview() throws Exception{
		mvc.perform(get("/review?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldRouteToOneReviewView() throws Exception{
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review1));
		
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
	}
	@Test
	public void shouldPutASingleReviewIntoModel() throws Exception{
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review1));
		mvc.perform(get("/review?id=1")).andExpect(model().attribute("reviews",review1));	
	}
	@Test
	public void shouldBeOkForAllTags() throws Exception{
		mvc.perform(get("/show-tags")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllTagsView() throws Exception{
		mvc.perform(get("/show-tags")).andExpect(view().name(is("tags")));
	}
	
	@Test
	public void shouldPutAllTagsIntoModel() throws Exception{
		Collection<Tag> allTags = asList(tag1,tag2);
		when(tagRepo.findAll()).thenReturn(allTags);
		mvc.perform(get("/show-tags")).andExpect(model().attribute("tags",allTags));	
	}
	
	@Test
	public void shouldBeOkForOneTag() throws Exception{
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(tag1));
		
		mvc.perform(get("/tag?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldBeNotFoundForOneTag() throws Exception{
		mvc.perform(get("/tag?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldRouteToOneTagView() throws Exception{
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(tag1));
		
		mvc.perform(get("/tag?id=1")).andExpect(view().name(is("tag")));
	}
	@Test
	public void shouldPutASingleTagIntoModel() throws Exception{
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(tag1));
		mvc.perform(get("/tag?id=1")).andExpect(model().attribute("tags", tag1));	
	}
	@Test
	public void shouldBeOkForAllCategories() throws Exception{
		mvc.perform(get("/show-categories")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllCategoriesView() throws Exception{
		mvc.perform(get("/show-categories")).andExpect(view().name(is("categories")));
	}
	
	@Test
	public void shouldPutAllCategoriesIntoModel() throws Exception{
		Collection<Category> allCategories = asList(category1,category2);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		mvc.perform(get("/show-categories")).andExpect(model().attribute("categories",allCategories));	
	}
	
	@Test
	public void shouldBeOkForOneCategory() throws Exception{
		long categoryId = 1;
		when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category1));
		
		mvc.perform(get("/category?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldBeNotFoundForOneCategory() throws Exception{
		mvc.perform(get("/category?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldRouteToOneCategoryView() throws Exception{
		long categoryId = 1;
		when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category1));
		
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
	}
	@Test
	public void shouldPutASingleCategoryIntoModel() throws Exception{
		long categoryId = 1;
		when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category1));
		mvc.perform(get("/category?id=1")).andExpect(model().attribute("category", category1));	
	}

	

}
