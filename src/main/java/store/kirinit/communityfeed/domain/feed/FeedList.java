package store.kirinit.communityfeed.domain.feed;


import java.util.List;

public class FeedList {
    private final List<Feed> feeds;

    public FeedList() {
        this(List.of());
    }

    public FeedList(List<Feed> feeds) {
        this.feeds = feeds;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void addFeed(Feed feed) {
        feeds.add(feed);
    }

    public void removeFeed(Feed feed) {
        feeds.remove(feed);
    }

    public int getFeedCount() {
        return feeds.size();
    }

}
