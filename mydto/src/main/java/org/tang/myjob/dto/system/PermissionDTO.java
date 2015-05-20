/*
 * Powered By tangzezhi
 * Since 2013 - 2015
 */

package org.tang.myjob.dto.system;
import org.apache.log4j.Logger;
import org.tang.myjob.utils.page.PageDataTable;

import java.io.Serializable;

public class PermissionDTO  extends PageDataTable {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private String id;
    private String url;
    private String name;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
