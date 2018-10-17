package org.wecancodeit.reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

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
	private ReviewRepository reviewRepo;
	
	@Resource
	private CategoryRepository categoryRepo;
	
	@Test
	public void shouldSaveAndLoadReview() {
		Review review = reviewRepo.save(new Review(1L,"White Rhino","image url","Nyeri, Kenya"));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		
		assertThat(review.getName(),is("White Rhino"));
		assertThat(review.getImgUrl(),is("image url"));
		assertThat(review.getLocation(),is("Nyeri, Kenya"));
	}

	@Test
	public void shouldGenerateReviewId() {
		Review review = reviewRepo.save(new Review(1L,"title","image url","Nyeri, Kenya"));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(reviewId,is(greaterThan(0L)));
		
	}
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = new Category("Accomodation","5","Room Service");
		category = categoryRepo.save(category);
		long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getTitle(),is("Accomodation"));
		assertThat(category.getRating(),is("5"));
		assertThat(category.getBasis(),is("Room Service"));
	
	}
	
	
	@Test
	public void shouldEstablishReviewToCategoryRelationship() {
		
		Review hotel1 = reviewRepo.save(new Review(1L,"White Rhino","img Url","Nyeri Kenya"));
		Review hotel2 = reviewRepo.save(new Review(1L,"Mara Sopa","img Url","Oololoo Hills Kenya"));
		
		Category category = new Category("Accomodation","***","No of Beds", hotel1, hotel2);
		category = categoryRepo.save(category);
		long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getReviews(),containsInAnyOrder(hotel1,hotel2));
		
	}
	
	@Test
	public void shouldFindCategoriesForReview() {
		Review hotel3 = reviewRepo.save(new Review(1L,"Marriot","img Url","New York, NY"));
		
		Category food = categoryRepo.save(new Category("Food","*****","Variety", hotel3));
		Category room = categoryRepo.save(new Category("Bathrooms","*****","Cleanliness", hotel3));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Category> categoriesForReview = categoryRepo.findByReviewsContains(hotel3);
		assertThat(categoriesForReview, containsInAnyOrder(food,room));
	}
	
	@Test
	public void shouldFindCategoryForReviewId() {
		Review hotel4 = reviewRepo.save(new Review(17L,"Motel 6","img Url","Newark, Ohio"));
		long reviewId = hotel4.getId();
		
		Category food = categoryRepo.save(new Category("Food","*****","Variety", hotel4));
		Category room = categoryRepo.save(new Category("Bathrooms","*****","Cleanliness", hotel4));		

		entityManager.flush();
		entityManager.clear();
		
		Collection<Category> categoriesForReview = categoryRepo.findByReviewsId(reviewId);
		assertThat(categoriesForReview, containsInAnyOrder(food,room));		
		
		
	}
	
}
