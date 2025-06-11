package org.example.expert.global.common.exception.domain.manage;

import org.example.expert.global.common.exception.CustomException;
import org.example.expert.global.enums.StatusCode;

public class ManagerNotFoundException extends CustomException {
    public ManagerNotFoundException() {
        super(StatusCode.NOT_FOUND,"매니저가 존재하지 않습니다.");
    }
}
