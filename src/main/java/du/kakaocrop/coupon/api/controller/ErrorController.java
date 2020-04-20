package du.kakaocrop.coupon.api.controller;


import du.kakaocrop.coupon.api.constant.ErrorType;
import du.kakaocrop.coupon.api.domain.CommonException;
import du.kakaocrop.coupon.api.service.CouponManageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 해당부분 4.16일 까지 처리
@ControllerAdvice
public class ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
    // Exception 처리

    // CustomException 처리
    // NotFound, Invalid, DB_INSERT 에러
    @ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody CommonException.ErrorResponse handleApiException(CommonException e, HttpServletRequest request, HttpServletResponse response) {
        logger.error("Defined Exception", e);
        return e.createErrorResponse();
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody CommonException.ErrorResponse handleApiException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        logger.error(" Undefined Exception", e);
        return new CommonException(ErrorType.UNKNOWN_ERROR).createErrorResponse();
    }
}
