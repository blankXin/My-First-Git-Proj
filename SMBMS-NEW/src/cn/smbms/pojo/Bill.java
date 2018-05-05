package cn.smbms.pojo;

import java.util.Date;
import java.util.List;

public class Bill {
	private Integer id;							//id
	private String billCode;				//订单编码
	private String productName;			//商品名称
	private String productDesc;			//商品描述
	private String productUnit;			//商品单位
	private double productCount;			//商品数量
	private double totalPrice;			//商品总额
	private Integer isPayment;			//是否支付（1未支付，2已支付）
	private Integer providerId;			//供应商ID
	private Integer createBy;				//创建者userId
	private Date creationDate;			//创建时间
	private Integer modifyBy;				//更新者userId
	private Date modifyDate;				//更新时间
	private String providerName;			//供应商名称
	private Provider provider;			//供应商
	private List<Provider> providerList;		//供应商集合
	
	public List<Provider> getProviderList() {
		return providerList;
	}
	public void setProviderList(List<Provider> providerList) {
		this.providerList = providerList;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bill(Integer id, String billCode, String productName, String productDesc, String productUnit,
			double productCount, double totalPrice, Integer isPayment, Integer providerId, Integer createBy,
			Date creationDate, Integer modifyBy, Date modifyDate) {
		super();
		this.id = id;
		this.billCode = billCode;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productUnit = productUnit;
		this.productCount = productCount;
		this.totalPrice = totalPrice;
		this.isPayment = isPayment;
		this.providerId = providerId;
		this.createBy = createBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public double getProductCount() {
		return productCount;
	}
	public void setProductCount(double productCount) {
		this.productCount = productCount;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getIsPayment() {
		return isPayment;
	}
	public void setIsPayment(Integer isPayment) {
		this.isPayment = isPayment;
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
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
