package shop.kimkj.mytrip.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserReviewCommentRequestDto {
    private Long idx;
//    private Long likes;
    private String comment;
}