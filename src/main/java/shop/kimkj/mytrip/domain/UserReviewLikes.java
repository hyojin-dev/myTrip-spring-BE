package shop.kimkj.mytrip.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("L")
@Getter
@Setter
public class UserReviewLikes extends UserReview {

//    좋아요 기능 구현 예정
    private int likes;

}
