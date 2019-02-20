CREATE TABLE IF NOT EXISTS article (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255),
  description VARCHAR(1000),
  publication_date DATE,
  extraction_url TEXT,
  main_image_url TEXT,
  source_url TEXT,

  PRIMARY KEY ( id )
)