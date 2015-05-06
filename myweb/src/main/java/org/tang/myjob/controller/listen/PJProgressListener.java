package org.tang.myjob.controller.listen;

/**
 * Created by Administrator on 2015/5/6.
 */

import org.apache.commons.fileupload.ProgressListener;
import org.tang.myjob.dto.message.ProgressDTO;

import javax.servlet.http.HttpSession;

public class PJProgressListener implements ProgressListener{
    private HttpSession session;
    public PJProgressListener() {
    }
    public PJProgressListener(HttpSession _session) {
        session=_session;
        ProgressDTO ps = new ProgressDTO();
        session.setAttribute("upload_ps", ps);
    }
    public void update(long pBytesRead, long pContentLength, int pItems) {
        ProgressDTO ps = (ProgressDTO) session.getAttribute("upload_ps");
        ps.setpBytesRead(pBytesRead);
        ps.setpContentLength(pContentLength);
        ps.setpItems(pItems);
        //更新
        session.setAttribute("upload_ps", ps);
    }

}