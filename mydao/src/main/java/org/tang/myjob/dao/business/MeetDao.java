
package org.tang.myjob.dao.business;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.myjob.dto.business.MeetDTO;
import org.tang.myjob.utils.page.PageDataTable;

@Repository
public interface MeetDao  {

    public int insertMeet(MeetDTO dto);

    public int updateMeet(MeetDTO dto);

    public int deleteMeet(int meetId);

    public PageDataTable<?> selectMeetPage(PageDataTable<?> page);

}

