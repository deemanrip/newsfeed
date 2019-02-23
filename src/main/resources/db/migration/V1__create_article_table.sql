CREATE TABLE IF NOT EXISTS article (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(1000),
  publication_date DATE NOT NULL,
  extraction_link VARCHAR(2000) NOT NULL,
  main_image_link VARCHAR(2000) NOT NULL,
  source_link VARCHAR(2000) NOT NULL,

  CONSTRAINT pk_article_id PRIMARY KEY (id)
)