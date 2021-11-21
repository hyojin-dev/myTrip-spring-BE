package shop.kimkj.mytrip.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.kimkj.mytrip.dto.UserReviewRequestDto;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public class UserReview {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long idx;

    private String title;
    private String place;
    private String review;
//    private Object photoFile;
//    private String photoFile;


    public UserReview(UserReviewRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.place = requestDto.getPlace();
        this.review = requestDto.getReview();
//        this.photoFile = requestDto.getPhotoFile();
    }

}
