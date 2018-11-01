package org.wecancodeit.reviews;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Collection<Category> findByTypeContains(String review);
	
	Optional<Category> findById(Long id);

}
