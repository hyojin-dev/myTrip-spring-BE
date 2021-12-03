package shop.kimkj.mytrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.kimkj.mytrip.domain.Comment;
import shop.kimkj.mytrip.domain.UserReview;
import shop.kimkj.mytrip.dto.CommentDto;
import shop.kimkj.mytrip.repository.CommentRepository;
import shop.kimkj.mytrip.repository.UserReviewRepository;
import shop.kimkj.mytrip.security.UserDetailsImpl;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserReviewRepository userReviewRepository;

    @Transactional
    public Comment postComment(Long reviewId, CommentDto commentDto, UserDetailsImpl nowUser) {
        UserReview userReview = userReviewRepository.findById(reviewId).orElseThrow(
                () -> new NullPointerException("해당 리뷰가 존재하지 않습니다."));

        Comment comment = new Comment(commentDto, userReview, nowUser.getUser());
        commentRepository.save(comment);
        return comment;
    }

    public List<Comment> getComment(Long reviewId) {
        return commentRepository.findAllByUserReviewId(reviewId);
    }

    @Transactional
    public ResponseEntity<?> deleteComment(Long commentId, UserDetailsImpl nowUser) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("해당 댓글이 존재하지 않습니다."));
        if (!nowUser.getId().equals(comment.getUser().getId())) {
            return new ResponseEntity<>("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN); // 403(FORBIDDEN)에러 - 권한없음
        }
        commentRepository.delete(comment);
        return new ResponseEntity<>(HttpStatus.OK); // 200 성공
    }
}
