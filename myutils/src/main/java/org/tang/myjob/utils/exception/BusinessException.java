package org.tang.myjob.utils.exception;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.tang.myjob.utils.enums.CommonStateEnum;
import org.tang.myjob.utils.enums.ExceptionTypeEnum;

/**
 * 
 * 创建人：黄彦军 <br>
 * 创建时间：2013-2-25 <br>
 * 功能描述：业务异常 <br>
 * 版本： <br>
 * 版权拥有：深圳中青宝互动网络股份有限公司 <br>
 * ====================================== <br>
 * 修改记录 <br>
 * ====================================== <br>
 * 序号 姓名 日期 版本 简单描述 <br>
 * 
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 8151877886020885741L;

	private String message;

	private int code;

	private int stateCode;

	public BusinessException(CommonStateEnum type) {
		super(type.getMessage());
		this.code = type.getState();
		this.stateCode = type.getStateCode();
		this.message = type.getMessage();
	}

	public BusinessException(CommonStateEnum type, String message) {
		super(message);
		this.code = type.getState();
		this.message = message;
	}

	public BusinessException(ExceptionTypeEnum type) {
		super(type.getMessage());
		this.code = type.getCode();
		this.message = type.getMessage();
	}

	public BusinessException(ExceptionTypeEnum type, String message) {
		super(message);
		this.code = type.getCode();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public int getStateCode() {
		return stateCode;
	}

}
