package org.example.expert.domain.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.dto.request.CommentSaveRequest;
import org.example.expert.domain.comment.dto.response.CommentResponse;
import org.example.expert.domain.comment.dto.response.CommentSaveResponse;
import org.example.expert.domain.comment.service.CommentService;
import org.example.expert.global.common.annotation.Auth;
import org.example.expert.global.common.dto.AuthUser;
import org.example.expert.global.common.dto.CommonResponseDto;
import org.example.expert.global.enums.StatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/todos/{todoId}/comments")
    public ResponseEntity<CommonResponseDto<CommentSaveResponse>> saveComment(
            @Auth AuthUser authUser,
            @PathVariable long todoId,
            @Valid @RequestBody CommentSaveRequest commentSaveRequest
    ) {
        return ResponseEntity.ok(
                CommonResponseDto.of(
                    StatusCode.OK,
                    commentService.saveComment(authUser, todoId, commentSaveRequest)
                ));
    }

    @GetMapping("/todos/{todoId}/comments")
    public ResponseEntity<CommonResponseDto<List<CommentResponse>>> getComments(@PathVariable long todoId) {
        return ResponseEntity.ok(
                CommonResponseDto.of(
                        StatusCode.OK,
                        commentService.getComments(todoId)
                ));
    }
}
