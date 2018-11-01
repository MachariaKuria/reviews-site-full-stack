package org.wecancodeit.reviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
	
    Collection<Hotel> findByReviewsContains(Review review);

    Collection<Hotel> findByReviewsId(Long id);

}
