package com.rss.RSS.feed.model;
/**
 * Created by liga on 29/09/2018.
 */
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table (name = "feeds")
public class Feed {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotNull
        @Size(min=2, max = 255, message = "URL needs to be between 2-255 characters")
        private String url;
        @NotNull
        @Size(min=2, max = 45, message= "Title needs to be between 2-45 characters")
        private String title;
        @Column(name="last_update")
        private Date lastUpdate;
        @Size(max = 255)
        @Column(name="feed_name")
        private String feedName;

        //Constructors
        public Feed() {}

        public Feed(Long id, String url, String title, Timestamp lastUpdate, String feedName) {
            this.id = id;
            this.url = url;
            this.title = title;
            this.lastUpdate = lastUpdate;
            this.feedName = feedName;
        }

        //Getters and Setters
        public Long getId() {
            return id;
        }
        public void setId(Long id) { this.id = id; }

        public String getUrl() { return url; }
        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) { this.title = title; }

        public Date getLastUpdate() {
            return this.lastUpdate;
        }
        public void setLastUpdate(Date last_update) { this.lastUpdate = last_update; }

        public String getFeedName(){ return this.feedName; }
        public void setFeedName(String f_name) { this.feedName = f_name; }
}
