package cn.smbms.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class Role {
	
	private Integer id;   //id
	private String roleCode; //角色编码
	private String roleName; //角色名称
	private Integer createdBy; //创建者
//	@DateTimeFormat(pattern="yyyy-MM-dd")	//解决Spring MVC中无法自动绑定时间的错误
	@JSONField(format="yyyy-MM-dd")				//解决JSON数据传递日期格式问题，以及满足特殊的输出日期格式。第一种办法，第二种办法是配置Springmvc-servlet.xml文件
	private Date creationDate; //创建时间
	private Integer modifyBy; //更新者
//	@DateTimeFormat(pattern="yyyy-MM-dd")	//解决Spring MVC中无法自动绑定时间的错误
	@JSONField(format="yyyy-MM-dd")				//解决JSON数据传递日期格式问题，以及满足特殊的输出日期格式。第一种办法，第二种办法是配置Springmvc-servlet.xml文件
	private Date modifyDate;//更新时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
