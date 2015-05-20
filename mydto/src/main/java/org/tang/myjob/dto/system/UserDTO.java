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

public class UserDTO  extends PageDataTable {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private String userId;
    private String username;
    private String password;
    private String idCard;
    private Integer sex;
    private Integer age;
    private String email;
    private String address;
    private String job;
    private String registerTime;
    private Integer isAdmin;
    private Integer isNormal;
    private String birthday;
    private String qq;
    private String weixin;
    private String weibo;
	private Integer isLocked;

	/**
	 * 权限
	 */
	private Set<PermissionDTO> permissions = new HashSet<PermissionDTO>();

	/**
	 * 角色
	 */
	private Set<RoleDTO> roles =  new HashSet<RoleDTO>();

	public Set<PermissionDTO> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<PermissionDTO> permissions) {
		this.permissions = permissions;
	}

	public Integer getIsLocked() {
		return isLocked;
	}



	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}



	public Set<RoleDTO> getRoles() {
		return roles;
	}



	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdCard() {
		return this.idCard;
	}
	
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Integer getSex() {
		return this.sex;
	}
	
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return this.age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJob() {
		return this.job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	public String getRegisterTime() {
		return this.registerTime;
	}
	
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public Integer getIsAdmin() {
		return this.isAdmin;
	}
	
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Integer getIsNormal() {
		return this.isNormal;
	}
	
	public void setIsNormal(Integer isNormal) {
		this.isNormal = isNormal;
	}
	public String getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getQq() {
		return this.qq;
	}
	
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeixin() {
		return this.weixin;
	}
	
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public String getWeibo() {
		return this.weibo;
	}
	
	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}


	/**
	 * @return
	 */
	public Set<String> getRolesAsString() {
		Set<String> roles = new HashSet<String>();
		for (RoleDTO role : getRoles()) {
			roles.add(role.getRoleCode());
		}
		return roles;
	}

	/**
	 *
	 * @return
	 */
	public Set<String> getPermissionsAsString() {
		Set<String> auths = new HashSet<String>();
		for (PermissionDTO auth : getPermissions()) {
			auths.add(auth.getUrl());
		}
		return auths;
	}

	public boolean hasRoles() {
		return !getRoles().isEmpty();
	}

	public boolean hasPermissions() {
		return !getPermissions().isEmpty();
	}

}
