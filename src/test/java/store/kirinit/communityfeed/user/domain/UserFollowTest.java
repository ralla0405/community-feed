package store.kirinit.communityfeed.user.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserFollowTest {

    @Test
    void 유저_팔로우_테스트() throws Exception {
        //given
        User user = new User(1L, new UserInfo("test", "test"));
        User user2 = new User(2L, new UserInfo("test2", "test2"));
        //when
        user.follow(user2);
        //then
        assertEquals(1, user.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
    }

    @Test
    void 팔로우_언팔로우() throws Exception {
        //given
        User user = new User(1L, new UserInfo("test", "test"));
        User user2 = new User(2L, new UserInfo("test2", "test2"));
        //when
        user.follow(user2);
        user.unfollow(user2);
        //then
        assertEquals(0, user.getFollowingCount());
    }

    @Test
    void 자신을_팔로우() throws Exception {
        //given
        User user = new User(1L, new UserInfo("test", "test"));
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> user.follow(user));
    }

    @Test
    void 자신을_언팔로우() throws Exception {
        //given
        User user = new User(1L, new UserInfo("test", "test"));
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> user.unfollow(user));
    }
}