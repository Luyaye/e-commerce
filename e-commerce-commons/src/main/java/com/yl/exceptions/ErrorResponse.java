package com.yl.exceptions;

import com.yl.base.ResponseBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorResponse extends ResponseBase {

  public ErrorResponse(final Integer code, final String msg) {
    super(code, msg);
  }

  public ErrorResponse(final Integer code, final String msg, final WebRequest request) {
    super(code, msg, extractRequestURI(request));
  }

  public ErrorResponse(final HttpStatus status, final Exception e) {
    super(status.value(), status.getReasonPhrase() + "：" + e.getMessage());
  }

  public ErrorResponse(final HttpStatus status, final Exception e, final WebRequest request) {
    super(status.value(), status.getReasonPhrase() + ": " + e.getMessage(), extractRequestURI(request));
  }

  private static String extractRequestURI(WebRequest request) {
    final String requestURI;
    if (request instanceof ServletWebRequest) {
      ServletWebRequest servletWebRequest = (ServletWebRequest) request;
      requestURI = servletWebRequest.getRequest().getRequestURI();
    } else {
      requestURI = request.getDescription(false);
    }
    return requestURI;
  }

}
