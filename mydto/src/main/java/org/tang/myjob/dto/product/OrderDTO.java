package org.tang.myjob.dto.product;

import org.tang.myjob.utils.page.PageDataTable;

import java.io.Serializable;

public class OrderDTO  extends PageDataTable implements Serializable{

	/**
	 *id` varchar(60) NOT NULL,
	 `user_id` varchar(60) DEFAULT NULL,
	 `state` int(2) DEFAULT '0' COMMENT '0:待处理：1：处理中 2:完成 99:异常',
	 `remark` text,
	 `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	 `start_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	 `end_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	 `total_amount` varchar(20) DEFAULT NULL COMMENT '订单总金额',
	 */

	private int id;
	private String userId;
	private Integer state;
	private String remark;
	private String createTime;
	private String startTime;
	private String endTime;
	private String totalAmount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
}
