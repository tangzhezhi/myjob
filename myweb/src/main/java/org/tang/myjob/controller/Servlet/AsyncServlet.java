package org.tang.myjob.controller.Servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2015/4/18.
 */
public class AsyncServlet extends HttpServlet {

    protected void doGet(final HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.isAsyncStarted()) {
            response.getWriter().write("asyncResult=" + request.getAttribute("asyncResult"));
        } else {
            final AsyncContext asyncContext = request.startAsync(request, response);

            asyncContext.addListener(new AsyncListener() {
                public void onTimeout(AsyncEvent event) throws IOException {
                    request.setAttribute("asyncResult", "timeout\n");
                    asyncContext.dispatch();
                }

                public void onStartAsync(AsyncEvent event) throws IOException {
                }

                public void onError(AsyncEvent event) throws IOException {
                }

                public void onComplete(AsyncEvent event) throws IOException {
                }
            });

            asyncContext.setTimeout(50000L);
        }
    }
}
