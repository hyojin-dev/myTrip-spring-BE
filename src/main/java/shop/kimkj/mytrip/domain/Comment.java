package shop.kimkj.mytrip.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Comment extends Timestamped {


    @Id
//    회원 식별용 (업데이트 예정 HJ 11.21)
    @Column(nullable = false)
    private long UserIdx;

    @Column(nullable = false)
    private String comment;
}
