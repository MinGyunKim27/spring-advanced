package org.example.expert.global.common.exception.domain.manage;

import org.example.expert.global.common.exception.CustomException;
import org.example.expert.global.enums.StatusCode;

public class SelfAssignNotAllowedException extends CustomException {
    public SelfAssignNotAllowedException() {
        super(StatusCode.BAD_REQUEST, "일정 작성자는 본인을 담당자로 등록할 수 없습니다.");
    }
}
