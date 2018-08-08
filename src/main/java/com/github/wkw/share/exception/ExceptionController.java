package com.github.wkw.share.exception;

import com.github.wkw.share.Constants;
import com.github.wkw.share.utils.ObjectUtils;
import com.github.wkw.share.vo.ShareResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static com.github.wkw.share.Constants.STATUS_CODE;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@RequestMapping("/error")
@EnableWebMvc
@ControllerAdvice
public class ExceptionController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {
        // Nothing to do
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    @ResponseBody
    public ShareResponse databaseError(HttpServletRequest request, Exception exception) {
        logger.error("Request: " + request.getRequestURL() + " raised " + exception, exception);
        return ShareResponse.fail(Constants.STATUS_CODE.ERROR_COMMON, "databaseError");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ShareResponse handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        StringBuilder stringBuilder = new StringBuilder();
        final List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        if (errors.size() > 0) {
            for (int i = 0; i < errors.size(); i++) {
                stringBuilder.append(errors.get(i).getDefaultMessage());
                if (i != errors.size() - 1) {
                    stringBuilder.append(",");
                }
            }
        }
        logger.error("Request: " + request.getRequestURL() + " raised " + exception, stringBuilder.toString());
        return ShareResponse.fail(STATUS_CODE.ERROR_DATA_DECODE, stringBuilder.toString());
    }


    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public ShareResponse handleCommonException(HttpServletRequest request, Exception exception) {
        logger.error("Request: " + request.getRequestURL() + " raised " + exception, exception);
        return ShareResponse.fail(STATUS_CODE.ERROR_COMMON, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ShareResponse handleUnknownException(HttpServletRequest request, Exception exception) {
        logger.error("Request: " + request.getRequestURL() + " raised " + exception, exception);
        return ShareResponse.fail(STATUS_CODE.ERROR_UNKNOWN, exception.getMessage());
    }

    @RequestMapping(value = "/403")
    @ResponseBody
    public ShareResponse handleAuthenticationException() {
        return ShareResponse.fail(STATUS_CODE.ERROR_RE_LOGIN);
    }
}
