package store.kirinit.communityfeed.domain.user;

public record ProfileImageVo(String profileImageUrl) {

    public ProfileImageVo {
        checkProfileImageUrl(profileImageUrl);
    }

    public void changeProfileImageUrl(String profileImageUrl) {
        checkProfileImageUrl(profileImageUrl);
    }

    private void checkProfileImageUrl(String profileImageUrl) {
        if (profileImageUrl == null || profileImageUrl.isEmpty()) {
            throw new IllegalArgumentException("프로필 이미지 URL은 필수 입력값입니다.");
        }
    }
}
