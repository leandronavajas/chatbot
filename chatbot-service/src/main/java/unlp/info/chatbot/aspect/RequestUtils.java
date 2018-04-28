package unlp.info.chatbot.aspect;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

  public static String getCurrentUrl() {
    HttpServletRequest request = getCurrentRequest();

    if (request == null) {
      return null;
    }

    StringBuffer urlBuilder = request.getRequestURL();
    String queryString = request.getQueryString();

    if (queryString != null && queryString.trim().length() > 0) {
      urlBuilder.append("?").append(queryString);
    }

    return urlBuilder.toString();
  }

  public static String getCurrentMethod() {
    HttpServletRequest request = getCurrentRequest();
    if (request == null) {
      return null;
    }
    return request.getMethod();
  }

  public static HttpServletRequest getCurrentRequest() {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    if (attributes == null) {
      return null;
    }

    return attributes.getRequest();
  }

  public static String getUow() {

    HttpServletRequest request = getCurrentRequest();
    if (request == null) {
      return null;
    }

    String uow_header = request.getHeader("X-UOW");
    String uow = StringUtils.isEmpty(uow_header) ? "" : uow_header;

    return uow;
  }

}
