package shop.kimkj.mytrip.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.kimkj.mytrip.domain.UserReview;
import shop.kimkj.mytrip.dto.UserReviewRequestDto;
import shop.kimkj.mytrip.repository.UserReviewRepository;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserReviewService {

    private final UserReviewRepository userReviewRepository;

    public UserReview postUserReview(UserReviewRequestDto userReviewRequestDto) {
//        if (userReviewRequestDto.getId() != null) {
//        }

        UserReview userReview = new UserReview(userReviewRequestDto);
        userReviewRepository.save(userReview);
        return userReview;
    }

    public UserReview getUserReview(Long id) {
        return userReviewRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 리뷰가 존재하지 않습니다.")
        );
    }

    public List<UserReview> getUserReviews() {
        return userReviewRepository.findAll();
    }

}
