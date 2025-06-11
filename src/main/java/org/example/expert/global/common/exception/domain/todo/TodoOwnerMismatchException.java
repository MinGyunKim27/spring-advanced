package org.example.expert.global.common.exception.domain.todo;

import org.example.expert.global.common.exception.CustomException;
import org.example.expert.global.enums.StatusCode;

public class TodoOwnerMismatchException extends CustomException {
  public TodoOwnerMismatchException() {
    super(StatusCode.FORBIDDEN, "해당 일정의 작성자가 아닌 사용자는 담당자를 등록할 수 없습니다.");
  }
}

