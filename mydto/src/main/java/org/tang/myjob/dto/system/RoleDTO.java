/*
 * Powered By tangzezhi
 * Since 2013 - 2015
 */

package org.tang.myjob.dto.system;
import org.apache.log4j.Logger;
import org.tang.myjob.utils.page.PageDataTable;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RoleDTO  extends PageDataTable {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private String id;
    private String roleName;
    private String roleCode;

	private Set<PermissionDTO> permissions = new HashSet<PermissionDTO>();

	public Set<PermissionDTO> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<PermissionDTO> permissions) {
		this.permissions = permissions;
	}

	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return this.roleCode;
	}
	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * @return
	 */
	public Set<String> getsPermissionsAsString() {
		Set<String> auths = new HashSet<String>();
		for (PermissionDTO auth : getPermissions()) {
			auths.add(auth.getUrl());
		}
		return auths;
	}

	public boolean hasPermissions() {
		return !getPermissions().isEmpty();
	}

}
