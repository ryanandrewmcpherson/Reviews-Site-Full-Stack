# Reviews-Site

	This is a full stack reviews website.... it has three main pages (/show-reviews, /show-categories, /show-tags) which each show a list of what they describe... each main page has a link to the other two main pages... the categories and tags have links to the reviews they describe...the reviews have non-link headings describing their category on the top and hyperlink tags associated with them on the bottom... the categories have a list of reviews associated with that category and the tags have a list of reviews associated with that tag. It's all done with JPA, Spring, and Thymeleaf. Reviews, Tags, and Categories are JPA entities and they take advantage of repository interfaces implementing CrudRepository. 