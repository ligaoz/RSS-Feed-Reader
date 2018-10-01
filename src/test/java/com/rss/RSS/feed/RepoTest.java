package com.rss.RSS.feed;

/**
 * Created by liga on 27/09/2018.
 */

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.rss.RSS.feed.repository.feedsRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepoTest {


        @Autowired
        private TestEntityManager entityManager;

        @Autowired
        private feedsRepository feedsRepository;

        // write test cases here



}
