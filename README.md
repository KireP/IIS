# IIS
This is the code of the music recommendation system. The recommendations are done by applying collaborative filtering algorithm on dataset obtained by crawling LastFM api. All the similiraties for the songs are precomputed  in advance and stored in similarity table in our database. For each song we keep 50 most similar songs to it. 

This codebase is web app that offers simple interface to the user to search for songs throughout our database, and then for each song we return the 50 most similar songs to it. The web app is done in Spring, for the database we use Hibernate as JPA vendor. The database provider is MySQL. JSP(Java server Pages) is used for view technology
