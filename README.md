# reviews-site-full-stack


Added the following dependencies to build.gradle (or use Spring Initializr to create a new build.gradle)
  1. JPA (spring-boot-starter-data-jpa)
  2. H2
Create a Category class that is a JPA entity.
Containing an instance variable referencing the Reviews it contains.
Configured an appropriate JPA relationship to its reviews.
Updated the Review class as a JPA entity.
Configured a JPA relationship to its associated category.
Allowed for a description/content/body longer than 255 characters.
Updated your view (templates/html/css) to have a page that lists review categories, each of which links to the (details) page for a specific category.
Created page that lists the reviews for a chosen category, each of which links to the (details) page for a specific review.
each review detail page has a link to the page for its category.
