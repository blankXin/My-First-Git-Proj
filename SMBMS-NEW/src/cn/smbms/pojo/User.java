package cn.smbms.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
	private Integer id; 					// id
	@NotEmpty(message="用户编码不能为空")//JSR 303 校验注解
	private String userCode; 			// 用户编码
	@NotEmpty(message="用户名不能为空")
	private String userName; 			// 用户名称
	@NotNull(message="密码不能为空")		//JSR 303 校验注解
	@Length(min=6,max=10,message="密码长度在6-10之间")
	private String userPassword;		// 用户密码
	private Integer gender; 				// 性别
	@Past(message="必须是一个过去时间")			//JSR 303 校验注解
	@DateTimeFormat(pattern="yyyy-MM-dd")	//解决Spring MVC中无法自动绑定时间的错误
	@JSONField(format="yyyy-MM-dd")				//解决JSON数据传递日期格式问题，以及满足特殊的输出日期格式。第一种办法，第二种办法是配置Springmvc-servlet.xml文件
	private Date birthday;				// 出生日期
	private String phone; 				// 电话
	private String address; 				// 住址
	private Integer userRole; 			// 用户角色
	private Integer createdBy; 		// 创建者
	private Date creationDate;		// 创建时间
	private Integer modifyBy; 			// 更新者
	private Date modifyDate;			// 更新时间
	private String userRoleName; 	// 用户角色名称
	private Integer age;					//年龄
	private String idPicPath;			//存放照片的路径
	private String workPicPath;		//存储上传工作证照片的路径

	public String getWorkPicPath() {
		return workPicPath;
	}

	public void setWorkPicPath(String workPicPath) {
		this.workPicPath = workPicPath;
	}

	public String getIdPicPath() {
		return idPicPath;
	}

	public void setIdPicPath(String idPicPath) {
		this.idPicPath = idPicPath;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public User() {
	}



	

	public User(Integer id, String userCode, String userName, String userPassword, Integer gender, Date birthday,
			String phone, String address, Integer userRole, Integer createdBy, Date creationDate, Integer modifyBy,
			Date modifyDate, String userRoleName, Integer age, String idPicPath, String workPicPath) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.userName = userName;
		this.userPassword = userPassword;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.userRole = userRole;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
		this.userRoleName = userRoleName;
		this.age = age;
		this.idPicPath = idPicPath;
		this.workPicPath = workPicPath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		//创建时间对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		//获取当前时间
        Date date = new Date();
        //格式化时间，取出年份
        String year =  sdf.format(date);
        //格式化时间，得到传入参数的年份
       	String year_2 = sdf.format(birthday);
       	//计算生日
       	this.age = Integer.parseInt(year)-Integer.parseInt(year_2);
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
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
