package org.tang.myjob.dao.message;

import org.springframework.stereotype.Repository;
import org.tang.myjob.dto.message.MessageDTO;
import org.tang.myjob.dto.system.UserDTO;

/**
 * Created by Administrator on 2015/3/17.
 */

@Repository
public interface MessageDao {

    public MessageDTO selectMessage();
}
