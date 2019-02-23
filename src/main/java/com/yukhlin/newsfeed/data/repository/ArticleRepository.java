package com.yukhlin.newsfeed.data.repository;

import com.yukhlin.newsfeed.data.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}