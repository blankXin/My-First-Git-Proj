package cn.smbms.pojo;

import java.util.Date;

public class Address {
	private Integer id;						//id
	private String contact;				//联系人姓名
	private String addressDesc;		//收货地址明细
	private String postCode;			//邮编
	private String tel;						//联系人电话
	private Integer createdBy;		//创建者（userId）
	private Date creationDate;		//创建时间
	private Integer modifyBy;			//更新者userId
	private Date modefyDate;			//更新时间
	public Address() {
		super();
	}
	public Address(Integer id, String contact, String addressDesc, String postCode, String tel, Integer createdBy,
			Date creationDate, Integer modifyBy, Date modefyDate) {
		super();
		this.id = id;
		this.contact = contact;
		this.addressDesc = addressDesc;
		this.postCode = postCode;
		this.tel = tel;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modefyDate = modefyDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddressDesc() {
		return addressDesc;
	}
	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public Date getModefyDate() {
		return modefyDate;
	}
	public void setModefyDate(Date modefyDate) {
		this.modefyDate = modefyDate;
	}
	
}
