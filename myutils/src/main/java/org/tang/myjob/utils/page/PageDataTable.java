package org.tang.myjob.utils.page;

import java.io.Serializable;
import java.util.List;

/**
 * 对分页的基本数据进行一个简单的封装
 */
public class PageDataTable<T>  implements Serializable {

	public int iTotalDisplayRecords;
	public int iTotalRecords;
	public String sEcho;
	public Integer iDisplayStart;
	public Integer iDisplayLength;

	public List<T> aaData;// 对应的当前页记录

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public Integer getiDisplayStart() {
		return iDisplayStart == null ? 0 : iDisplayStart;
	}


	public void setiDisplayStart(Integer iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public Integer getiDisplayLength() {
		return iDisplayLength == null ? 10 : iDisplayLength;
	}

	public void setiDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}
}

