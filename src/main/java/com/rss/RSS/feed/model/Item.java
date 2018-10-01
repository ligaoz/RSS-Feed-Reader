package com.rss.RSS.feed.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by liga on 29/09/2018.
 */

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String link;
    @Size(max = 255)
    private String title;
    private Date published;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL, optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable=false, updatable=false)
    private Feed feedId;

    // Constructors
    public Item(){}

    public Item(Long id, Feed feed, String link, String title, Date published, String description) {
        this.id = id;
        this.link =link;
        this.title = title;
        this.published = published;
        this.description = description;
        this.feedId = feed;
    }

    //Getter and Setter methods
    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }

    public void setLink(String link){
        this.link = link;
    }
    public String getLink(){
        return link;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setPublished (Date published){
        this.published = published;
    }
    public Date getPublished(){
        return published;
    }

    public void setDescription (String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    public void setFeedId(Feed Feed) {
        this.feedId = Feed;
    }
    public Feed getFeedId() { return feedId; }
}
