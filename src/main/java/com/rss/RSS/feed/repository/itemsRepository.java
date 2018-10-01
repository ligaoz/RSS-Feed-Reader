package com.rss.RSS.feed.repository;

import com.rss.RSS.feed.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liga on 29/09/2018.
 */

@Repository
public interface itemsRepository extends JpaRepository<Item, Long> {

    @Query(value="SELECT * FROM items i WHERE feed_id = ?1 order by i.published desc LIMIT 5", nativeQuery = true)
    List<Item> findTop5ByFeedIdOrderByPublished(Long feed_id);

    @Query(value="SELECT COUNT(*) FROM items i WHERE feed_id = ?1", nativeQuery = true)
    Long countByFeedId(Long id);
}
