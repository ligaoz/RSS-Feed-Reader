package com.rss.RSS.feed.repository;
/**
 * Created by liga on 29/09/2018.
 */

import com.rss.RSS.feed.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface feedsRepository extends JpaRepository<Feed, Long>{
}



