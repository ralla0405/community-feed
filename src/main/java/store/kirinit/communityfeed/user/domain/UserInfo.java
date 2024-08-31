package store.kirinit.communityfeed.user.domain;

// VO 활용
public class UserInfo {
    private final String name;
    private final String profileImageUrl;

    public UserInfo(String name, String profileImageUrl) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름은 필수 입력값입니다.");
        }
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
