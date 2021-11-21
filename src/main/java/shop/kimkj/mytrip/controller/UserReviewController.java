package shop.kimkj.mytrip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.kimkj.mytrip.domain.UserReview;
import shop.kimkj.mytrip.dto.UserReviewRequestDto;
import shop.kimkj.mytrip.service.UserReviewService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserReviewController {

    private final UserReviewService userReviewService;

    @PostMapping("/userReview")
    public UserReview PostUserReview(@RequestBody UserReviewRequestDto userReviewRequestDto) {
        return userReviewService.postUserReview(userReviewRequestDto);
    }

    @GetMapping("/userReview/{id}")
    public UserReview GetUserReview(@PathVariable Long id) {
        return userReviewService.getUserReview(id);
    }

    @GetMapping("/userReviews")
    public List<UserReview> GetUserReviews() {
        return userReviewService.getUserReviews();
    }


}
