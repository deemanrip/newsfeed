# newsfeed
Before application start:
- Create news_feed schema in database
- Specify datasource properties in application.properties (spring.datasource.url, spring.datasource.username, spring.datasource.password)
- Create table in news_feed schema:

 a) Using flyway
 
    To create table using flyway, specify database connection parameters in build.gradle file in flyway section (url, user, password).
    Then, run flyway tasks flywayClean and flywayMigrate (they can be runned via Intellij Idea gradle plugin and Tasks/flyway section).
    Or
    
 b) Create table manually using resources/db/migration/V1__create_article_table.sql script
 
- Directory for html-extraction files should be created and specified in crawler.properties file (extraction.directory property), e.g. C:/extraction

To run application:
- Run main class com.yukhlin.newsfeed.NewsfeedApplication

To run crawler:
- open root url (http://localhost:8080)
The web page should be displayed with 'Run crawler?' message and button. Click button, then, if crawler is completed successfully, the result table will be displayed.
