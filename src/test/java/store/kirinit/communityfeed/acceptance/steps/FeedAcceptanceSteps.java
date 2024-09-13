package store.kirinit.communityfeed.acceptance.steps;

import io.restassured.RestAssured;
import java.util.List;
import org.springframework.http.MediaType;
import store.kirinit.communityfeed.post.application.dto.CreatePostRequestDto;
import store.kirinit.communityfeed.post.ui.dto.GetPostContentResponseDto;

public class FeedAcceptanceSteps {

    public static Long requestCreatePost(CreatePostRequestDto dto) {
        return RestAssured
            .given().log().all()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/posts")
            .then().log().all()
            .extract()
            .jsonPath()
            .getObject("value", Long.class);
    }

    public static List<GetPostContentResponseDto> requestFeed(Long userId) {
        return RestAssured
            .given().log().all()
            .when()
            .get("/feeds/{userId}", userId)
            .then().log().all()
            .extract()
            .jsonPath()
            .getList("value", GetPostContentResponseDto.class);
    }
}
