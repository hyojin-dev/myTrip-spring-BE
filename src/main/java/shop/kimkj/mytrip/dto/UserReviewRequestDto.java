package shop.kimkj.mytrip.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserReviewRequestDto {

//  id 값이 존재한다면 수정하는 것 (추가 예정)
//    private Long id;


    private String title;
    private String place;
    private String review;

//    private Object photoFile;


}
