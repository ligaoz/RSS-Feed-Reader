package com.rss.RSS.feed.controller;

/**
 * Created by liga on 29/09/2018.
 */

import com.rss.RSS.feed.model.Feed;
import com.rss.RSS.feed.repository.feedsRepository;
import com.rss.RSS.feed.repository.itemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemsController {

    private final Logger logger = LoggerFactory.getLogger(FeedsController.class);

    @Autowired
    private itemsRepository itemsRepository;

    @Autowired
    private feedsRepository feedsRepository;

    @GetMapping("/feed/{id}")
    public String getFeedList(@PathVariable(value = "id") Long id, Model model) {
        logger.debug("getFeedList()");

        model.addAttribute("count", itemsRepository.countByFeedId(id));
        model.addAttribute("Feed", feedsRepository.findById(id).orElse(new Feed()));
        model.addAttribute("Item", itemsRepository.findTop5ByFeedIdOrderByPublished(id));

        return "item";
    }
}
