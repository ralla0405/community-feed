package store.kirinit.communityfeed.post.repository.entity.post;

import jakarta.persistence.AttributeConverter;
import store.kirinit.communityfeed.post.domain.PostState;

public class PostStateConverter implements AttributeConverter<PostState, String> {

    @Override
    public String convertToDatabaseColumn(PostState postState) {
        return postState.name();
    }

    @Override
    public PostState convertToEntityAttribute(String s) {
        return PostState.valueOf(s);
    }
}
