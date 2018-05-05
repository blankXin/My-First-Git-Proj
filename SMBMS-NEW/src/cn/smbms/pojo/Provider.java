package cn.smbms.pojo;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Provider {	
	private Integer id;							//id
	@NotEmpty(message="供应商编码不能为空")
	private String proCode;					//供应商编码
	@NotEmpty(message="供应商名称不能为空")
	private String proName;					//供应商名称
	private String proDesc;					//供应商详细描述
	@NotEmpty(message="供应商联系人不能为空")
	private String proContact;			//供应商联系人
	@Pattern(regexp="^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$",message="请输入正确格式的手机号")
	private String proPhone;				//供应商电话
	private String proAddress;			//地址
	private String proFax;					//传真
	private Integer createdBy;			//创建者userID
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date creationDate;			//创建时间
	private Integer modifyBy;				//更新者userId
	private Date modifyDate;				//更新时间
	private String companyLicPicPath; //企业营业执照
	private String orgCodePicPath;		//组织机构代码
	public String getCompanyLicPicPath() {
		return companyLicPicPath;
	}
	public void setCompanyLicPicPath(String companyLicPicPath) {
		this.companyLicPicPath = companyLicPicPath;
	}
	public String getOrgCodePicPath() {
		return orgCodePicPath;
	}
	public void setOrgCodePicPath(String orgCodePicPath) {
		this.orgCodePicPath = orgCodePicPath;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Provider() {
	}
	public Provider(int id, String proCode, String proName, String proDesc, String proContact, String proPhone,
			String proAddress, String proFax, int createdBy, Date creationDate, int modifyBy, Date modifyDate) {
		this.id = id;
		this.proCode = proCode;
		this.proName = proName;
		this.proDesc = proDesc;
		this.proContact = proContact;
		this.proPhone = proPhone;
		this.proAddress = proAddress;
		this.proFax = proFax;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public String getProContact() {
		return proContact;
	}
	public void setProContact(String proContact) {
		this.proContact = proContact;
	}
	public String getProPhone() {
		return proPhone;
	}
	public void setProPhone(String proPhone) {
		this.proPhone = proPhone;
	}
	public String getProAddress() {
		return proAddress;
	}
	public void setProAddress(String proAddress) {
		this.proAddress = proAddress;
	}
	public String getProFax() {
		return proFax;
	}
	public void setProFax(String proFax) {
		this.proFax = proFax;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
