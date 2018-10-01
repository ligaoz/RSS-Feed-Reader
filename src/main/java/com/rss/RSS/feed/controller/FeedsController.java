package com.rss.RSS.feed.controller;

import com.rss.RSS.feed.model.Feed;
import com.rss.RSS.feed.repository.feedsRepository;
import com.rss.RSS.feed.repository.itemsRepository;
import com.rss.RSS.feed.service.FeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by liga on 29/09/2018.
 */
@Controller
public class FeedsController {

    private final Logger logger = LoggerFactory.getLogger(FeedsController.class);

    @Autowired
    private feedsRepository feedsRepository;

    @Autowired
    private itemsRepository itemsRepository;

    @Autowired
    private  FeedService feedService;

    @GetMapping("/feed")
    public String getFeed(Model model) {
        logger.debug("getFeed()");
        model.addAttribute("Feed", new Feed());
        return "feed";
    }
    @GetMapping("/feed/list")
    public String getFeedList(Model model) {
        logger.debug("getFeedList()");
        model.addAttribute("items", feedsRepository.findAll());
        return "list";
    }
    @PostMapping("/feed/add")
    public String submit(@ModelAttribute("Feed") @Valid Feed addFeed, BindingResult bindingResult) {
        logger.debug("submit()");

        if (bindingResult.hasErrors()) {
            return "feed";
        }
        feedService.postFeed(addFeed);
        return "redirect:/feed/";
    }
}
