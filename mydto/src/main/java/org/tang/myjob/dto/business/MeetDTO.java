/*
 * Powered By tangzezhi
 * Since 2013 - 2015
 */

package org.tang.myjob.dto.business;
import org.apache.log4j.Logger;
import org.tang.myjob.utils.page.PageDataTable;

import java.io.Serializable;

public class MeetDTO   extends PageDataTable {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());

	private java.lang.Integer id;
	private java.lang.String meetName;
	private java.lang.Integer roomId;
	private java.lang.String startTime;
	private java.lang.String endTime;
	private java.lang.Integer joinNumber;

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.String getMeetName() {
		return this.meetName;
	}

	public void setMeetName(java.lang.String meetName) {
		this.meetName = meetName;
	}
	public java.lang.Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(java.lang.Integer roomId) {
		this.roomId = roomId;
	}
	public java.lang.String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(java.lang.String startTime) {
		this.startTime = startTime;
	}
	public java.lang.String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}
	public java.lang.Integer getJoinNumber() {
		return this.joinNumber;
	}

	public void setJoinNumber(java.lang.Integer joinNumber) {
		this.joinNumber = joinNumber;
	}

}
