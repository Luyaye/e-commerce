package com.yl.exceptions;

import com.yl.messages.ErrorEnum;
import com.yl.messages.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class UnifiedExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String ENV_PROD = "prod";
  private final MessageSource messageSource;
  private final Boolean isProd;

  public UnifiedExceptionHandler(@Value("${spring.profiles.active:dev}") final String activeProfile, final MessageSource messageSource) {
    this.messageSource = messageSource;
    this.isProd = new HashSet<>(Arrays.asList(activeProfile.split(","))).contains(ENV_PROD);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(final Exception e, final Object body, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    log.info("请求异常：" + e.getMessage(), e);
    if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
      request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, WebRequest.SCOPE_REQUEST);
    }
    return new ResponseEntity<>(new ErrorResponse(status, e), headers, HttpStatus.OK);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    log.info("参数绑定异常", ex);
    final ErrorResponse response = wrapperBindingResult(status, ex.getBindingResult());
    return new ResponseEntity<>(response, headers, HttpStatus.OK);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    log.info("参数校验异常", ex);
    final ErrorResponse response = wrapperBindingResult(status, ex.getBindingResult());
    return new ResponseEntity<>(response, headers, HttpStatus.OK);
  }

  @ExceptionHandler(value = AmaException.class)
  @ResponseBody
  public ErrorResponse handleBusinessException(AmaException e) {
    log.error("业务异常：" + e.getMessage(), e);
    final ResponseEnum anEnum = e.getResponseEnum();
    return new ErrorResponse(anEnum.getCode(), anEnum.getLocaleMessage(e.getArgs()));
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public ErrorResponse handleExceptionInternal(Exception e) {
    log.error("未捕捉异常：" + e.getMessage(), e);
    final Integer code = ErrorEnum.INTERNAL_SERVER_ERROR.getCode();
    return new ErrorResponse(code, getLocaleMessage(code, e.getMessage()));
  }


  /**
   * 包装绑定异常结果
   *
   * @param status        HTTP状态码
   * @param bindingResult 参数校验结果
   * @return 异常对象
   */
  private ErrorResponse wrapperBindingResult(HttpStatus status, BindingResult bindingResult) {
    final List<String> errorDesc = new ArrayList<>();
    for (ObjectError error : bindingResult.getAllErrors()) {
      final StringBuilder msg = new StringBuilder();
      if (error instanceof FieldError) {
        msg.append(((FieldError) error).getField()).append(": ");
      }
      msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
      errorDesc.add(msg.toString());
    }
    final String desc = isProd ? getLocaleMessage(status.value(), status.getReasonPhrase()) : String.join(", ", errorDesc);
    return new ErrorResponse(status.value(), desc);
  }

  private String getLocaleMessage(Integer code, String defaultMsg) {
    try {
      return messageSource.getMessage("" + code, null, defaultMsg, LocaleContextHolder.getLocale());
    } catch (Throwable t) {
      log.warn("本地化异常消息发生异常: {}", code);
      return defaultMsg;
    }
  }
}