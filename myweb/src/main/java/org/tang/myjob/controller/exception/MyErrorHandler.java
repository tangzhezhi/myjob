package org.tang.myjob.controller.exception;

import org.apache.log4j.Logger;
import org.springframework.util.ErrorHandler;

/**
 * Created by Administrator on 2015/3/18.
 */
public class MyErrorHandler implements ErrorHandler {
    private Logger logger  = Logger.getLogger("MyErrorHandler");

    @Override
    public void handleError(Throwable throwable) {
        logger.error("事件失败了, error message : " + throwable.getMessage());
    }
}
