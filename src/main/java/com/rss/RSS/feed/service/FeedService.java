package com.rss.RSS.feed.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.rss.RSS.feed.model.Feed;
import com.rss.RSS.feed.model.Item;
import com.rss.RSS.feed.repository.feedsRepository;
import com.rss.RSS.feed.repository.itemsRepository;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by liga on 01/10/2018.
 */
@Service
public class FeedService {

    @Autowired
    private feedsRepository feedsRepository;

    @Autowired
    private itemsRepository itemsRepository;

    @Async
    @Transactional
    public Future postFeed(Feed addFeed) {
        //Initialize new RSS reader
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed sf = null;

        try {
            //HTTP client setup for RSS reader
            CloseableHttpClient client = HttpClients.createMinimal();
            HttpUriRequest request = new HttpGet(addFeed.getUrl());
            CloseableHttpResponse response = client.execute(request);
            InputStream stream = response.getEntity().getContent();
            //Build RSS XML
            sf = input.build(new XmlReader(stream));
            List entries = sf.getEntries();
            Iterator it = entries.iterator();

            //Save Feed PubDate and FeedName to Feeds object
            addFeed.setLastUpdate(sf.getPublishedDate());
            addFeed.setFeedName(sf.getTitle());
            feedsRepository.save(addFeed);

            while (it.hasNext()) {
                Item newItem = new Item();
                SyndEntry entry = (SyndEntry) it.next();

                //Saving input fields to the Item model
                newItem.setFeedId(addFeed);
                newItem.setLink(entry.getLink());
                newItem.setTitle(entry.getTitle());
                newItem.setPublished(entry.getPublishedDate());
                newItem.setDescription(entry.getDescription().getValue());
                itemsRepository.saveAndFlush(newItem);
            }
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
