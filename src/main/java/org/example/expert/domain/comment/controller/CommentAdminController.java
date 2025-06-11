package org.example.expert.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.service.CommentAdminService;
import org.example.expert.global.common.annotation.AdminApi;
import org.example.expert.global.common.dto.CommonResponseDto;
import org.example.expert.global.enums.StatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentAdminController {

    private final CommentAdminService commentAdminService;

    @AdminApi
    @DeleteMapping("/admin/comments/{commentId}")
    public ResponseEntity<CommonResponseDto<Void>> deleteComment(@PathVariable long commentId) {
        commentAdminService.deleteComment(commentId);

        return ResponseEntity.ok(CommonResponseDto.of(StatusCode.OK));
    }
}
