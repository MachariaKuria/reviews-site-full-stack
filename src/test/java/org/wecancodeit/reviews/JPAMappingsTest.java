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
    ReviewRepository reviewRepo;

    @Resource
    HotelRepository hotelRepo;

    @Resource
    CategoryRepository categoryRepo;

    @Test
    public void shouldSaveAndLoadReview(){

        Review food = reviewRepo.save(new Review("Food"));
        long reviewId = food.getId();

        entityManager.flush();
        entityManager.clear();

        Optional<Review> result = reviewRepo.findById(reviewId);
        food = result.get();
        assertThat(food.getReviewType(), is("Food"));

    }

    @Test
    public void shouldGenerateReviewId(){

        Review food = reviewRepo.save(new Review("Food"));
        long reviewId = food.getId(); 
    
        entityManager.flush();
        entityManager.clear();
    
        assertThat(reviewId, is(greaterThan(0L)));
    
    }

    @Test
    public void shouldSaveAndLoadHotel(){
        Hotel hotel1 = new Hotel("White Rhino","Image Url","Location");
        hotel1 = hotelRepo.save(hotel1);
        long hotelId = hotel1.getId();


        entityManager.flush();
        entityManager.clear();

        Optional<Hotel> result = hotelRepo.findById(hotelId);
        hotel1 = result.get();

        assertThat(hotel1.getName(), is("White Rhino"));
        assertThat(hotel1.getImgUrl(), is("Image Url"));
        assertThat(hotel1.getLocation(), is("Location"));

    }

    @Test
    public void shouldEstablishHotelToReviewRelationships(){
        //review is not the owner so create it first.

        Review food = reviewRepo.save(new Review("Food"));
        Review gym = reviewRepo.save(new Review("Gym"));
        Review pool = reviewRepo.save(new Review("Pool"));
        Review wifi = reviewRepo.save(new Review("WiFi"));


        Hotel hotel1 = new Hotel("Tsavo East","Image Url","Location",food,gym,pool,wifi);
        hotel1 = hotelRepo.save(hotel1);
        long hotelId = hotel1.getId();


        entityManager.flush();
        entityManager.clear();

        Optional<Hotel> result = hotelRepo.findById(hotelId);
        hotel1 = result.get();

        assertThat(hotel1.getReviews(), containsInAnyOrder(gym,food,pool,wifi));

    }


    @Test
    public void shouldFindAHotelForReview(){

        Review food = reviewRepo.save(new Review("Food")); 

        Hotel hotel1 = new Hotel("Tsavo East","Image Url","Location",food);   
        Hotel hotel2 = new Hotel("Tsavo West","Image Url","Location",food); 
        
        entityManager.flush();
        entityManager.clear();

        Collection<Hotel> hotelsForReview = hotelRepo.findByReviewsContains(food);

        assertThat(hotelsForReview, containsInAnyOrder(hotel1,hotel2));

    }

    @Test
    public void shouldFindHotelsForReviewId(){
        Review gym = reviewRepo.save(new Review("Gym")); 
        long reviewId = gym.getId();

        Hotel hotel3 = new Hotel("Treetops","Image Url","Location",gym);   
        Hotel hotel4 = new Hotel("Mara Sopa","Image Url","Location",gym);

        Collection<Hotel> hotelsForReview = hotelRepo.findByReviewsId(reviewId);

        assertThat(hotelsForReview, containsInAnyOrder(hotel4,hotel3));

    }

    @Test
    public void shouldEstablishCategoryToHotelRelationships(){

        Hotel hotel1 = new Hotel("White Rhino","Image Url","Location");
        hotel1 = hotelRepo.save(hotel1);
        long hotelId = hotel1.getId();

        Category beachHotels = new Category("Type", hotel1);
        categoryRepo.save(beachHotels);

        Category cityHotels = new Category("Type 2", hotel1);
        categoryRepo.save(cityHotels);

        entityManager.flush();
        entityManager.clear();

        Optional<Hotel> result = hotelRepo.findById(hotelId);
        hotel1 = result.get();

        assertThat(hotel1.getCategories(),containsInAnyOrder(beachHotels,cityHotels));

    }


	
//	@Test
//	public void shouldHaveTwoCommentsOnOneReview() {
//		
//		Review hotel3 = reviewRepo.save(new Review("Rooms","***","Cleanliness"));
//		Review review = reviewRepo.save(new Review("Rooms","***","Cleanliness"));
//		
//		review = reviewRepo.save(review);
//		
//		long categoryId = review.getId();
//		
//		Comment testComment1 = new Comment("Author", review, "Comment1");
//		testComment1 = commentRepo.save(testComment1);		
//		long testComment1Id = testComment1.getId();
//		
//		Comment testComment2 = new Comment("Author2", review, "Comment2");
//		testComment2 = commentRepo.save(testComment2);
//		long testComment2Id = testComment2.getId();
//		
//		entityManager.flush();
//		entityManager.clear();
//		
//		Iterable<Comment> comments = commentRepo.findAll();
//		assertThat(comments,containsInAnyOrder(testComment2,testComment1));
//		
//		Optional<Comment> testComment1Result = commentRepo.findById(testComment1Id);
//		testComment1 = testComment1Result.get();
//
//		Optional<Comment> testComment2Result = commentRepo.findById(testComment2Id);
//		testComment2 = testComment2Result.get();
//
//		
//		Optional<Review> categoryResult = reviewRepo.findById(categoryId);
//		review = categoryResult.get();
//		
//		assertThat(testComment1.getAuthor(),is("Author"));
//		assertThat(testComment2.getAuthor(),is("Author2"));
//		assertThat(testComment1.getReview(),is(review));
//		assertThat(testComment2.getReview(),is(review));
//		assertThat(review.getComments(),containsInAnyOrder(testComment2,testComment1));
//		
//		
//	}
	
}
