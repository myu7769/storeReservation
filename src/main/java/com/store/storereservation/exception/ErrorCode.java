package com.store.storereservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    WRONG_VERIFICATION(HttpStatus.BAD_REQUEST, "잘못된 인증입니다."),
    EXPIRE_CODE(HttpStatus.BAD_REQUEST, "기한이 만료된 코드입니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "일치하는 회원이 없습니다."),


    // login
    LOGIN_CHECK_FAIL(HttpStatus.BAD_REQUEST, "아이디나 패스워드를 확인해주세요."),
    ALREADY_VERIFY(HttpStatus.BAD_REQUEST, "이미 인증이 완료되었습니다."),

    //Store

    ALREADY_NAME_STORE(HttpStatus.BAD_REQUEST, "이미 등록된 이름의 매장입니다."),
    NOT_FOUND_STORE(HttpStatus.BAD_REQUEST, "등록되지 않은 이름의 매장입니다."),


    // Reservation
    NOT_DateOrTime_INVAILD(HttpStatus.BAD_REQUEST, "예약 시간이 현재 시간보다 이전입니다."),
    ALREADY_CUSTOMER_RESERVATION(HttpStatus.BAD_REQUEST, "예약 시간에 이미 사용자가 있습니다."),
    NOT_FOUNT_RESERVATION(HttpStatus.BAD_REQUEST, "입력한 예약을 찾을 수 없습니다."),
    NOT_FINISHED_SERVICE(HttpStatus.BAD_REQUEST, "서비스 종료되지 않아 리뷰를 작성할 수 없습니다."),
    LATE_RESERVATION(HttpStatus.BAD_REQUEST, "예약 시간에 늦어 사용할 수 없습니다.");


    private final HttpStatus httpStatus;
    private final String detail;
}
