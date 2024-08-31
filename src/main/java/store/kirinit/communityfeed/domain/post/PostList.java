package store.kirinit.communityfeed.domain.post;


import java.util.ArrayList;
import java.util.List;

public class PostList {
    private final List<Post> posts;

    public PostList() {
        this(new ArrayList<>());
    }

    public PostList(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        if (posts.contains(post)) {
            throw new IllegalArgumentException("이미 추가된 게시물입니다.");
        }
        posts.add(post);
    }

    public void removePost(Post post) {
        if (!posts.contains(post)) {
            throw new IllegalArgumentException("추가된 게시물이 아닙니다.");
        }
        posts.remove(post);
    }

    public int getPostCount() {
        return posts.size();
    }

}
