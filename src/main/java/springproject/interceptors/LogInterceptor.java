package springproject.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class presents the logging a web application.
 *
 * @author Evgeniy Presnov
 */
@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {

    /**
     * The method performs action before handling of a request
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        if (request.getMethod().equals("GET")) {
            log.info("preHandle : GET request");
        } else if (request.getMethod().equals("POST")) {
            log.info("preHandle : POST request");
        }

        log.info("Request URL: " + request.getRequestURI());
        log.info("Start Time: " + System.currentTimeMillis() + " ms" + "\n");

        return true;
    }

    /**
     * The method performs action after handling of a request
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getMethod().equals("GET")) {
            log.info("postHandle : GET request");
        } else if (request.getMethod().equals("POST")) {
            log.info("postHandle : POST request");
        }

        log.info("Request URL: " + request.getRequestURI());
        log.info("Current Time: " + System.currentTimeMillis() + " ms" + "\n");
    }

    /**
     * The method performs action after completion of a request
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long executeTime = System.currentTimeMillis() - startTime;

        log.info("Request URL: " + request.getRequestURI());
        log.info("End Time: " + System.currentTimeMillis() + " ms");
        log.info("Execute Time: " + executeTime + " ms" + "\n");
    }
}
