package org.wecancodeit.reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewsController.class)
public class CategoryControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@Mock
	private Category category;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
//	@Mock
//	private Review firstReview;
//	
//	@Mock
//	private Review secondReview;
//	
//	@MockBean
//	private ReviewRepository repository;
	
	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
	}

	
}
