package cn.smbms.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	private Logger logger = Logger.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	// 删除用户
	@RequestMapping(value = "/deleteUser.json")
	@ResponseBody
	public Object deleteUser(@RequestParam String id, HttpServletRequest request) throws Exception{
		// 根据id获取user，判断是否存在idPicPath或者workPicPath
		User user = userService.getUserById(id);
		HashMap<String,String> resultMap = new HashMap<String,String>();
		if(user == null) {
			resultMap.put("delResult","notexist");
			return JSONArray.toJSONString(resultMap);
		}
		// 文件流
		// 文件路径
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles"); // 存储路径
		logger.info("-----------path----------"+path);
		// 如果存在idPicPath，则先删除文件
		if (user.getIdPicPath() != null) {
			File targetFile = new File(path, user.getIdPicPath());
			if(targetFile.exists()) {
				targetFile.delete();
			}
		}
		// 如果存在workPicPath，则先删除文件
		if (user.getWorkPicPath() != null) {
			File targetFile = new File(path, user.getWorkPicPath());
			if(targetFile.exists()) {
				targetFile.delete();
			}
		}
		// 删除用户
		if(userService.deleteUserById(Integer.parseInt(id))>0) {
			resultMap.put("delResult","true");
		}else {
			resultMap.put("delResult","false");
		}
		return JSONArray.toJSONString(resultMap);
	}

	// 进入密码修改页面
	@RequestMapping(value = "/pwdmodify.html", method = RequestMethod.GET)
	public String pwdModify() {
		return "pwdmodify";
	}

	// 完成密码修改
	@RequestMapping(value = "/pwdmodify.html", method = RequestMethod.POST)
	public String pwd(@RequestParam String userId, @RequestParam String newpassword) throws Exception {
		boolean flag = userService.updatePwd(Integer.parseInt(userId), newpassword)>0?true:false;
		if (flag)
			return "redirect:/login.html";
		else
			return "pwdmodify";
	}

	// 验证原密码是否正确
	@RequestMapping(value = "/modify.json")
	@ResponseBody
	public Object pwdModify(@RequestParam String id, @RequestParam String oldpassword)throws Exception {
		logger.debug("---------------id===========" + id);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		User user = userService.getUserById(id);
		if (id == null) {
			resultMap.put("result", "sessionerror");
		} else if (user.getUserPassword().equals(oldpassword)) {
			resultMap.put("result", "true");
		} else if (oldpassword == null) {
			resultMap.put("result", "error");
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}

	// 验证userCode同名
	@RequestMapping(value = "/ucexist.json")
	@ResponseBody
	public Object userCodeIsExit(@RequestParam String userCode) throws Exception{
		logger.debug("userCodeIsExit userCode：" + userCode);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(userCode)) {
			resultMap.put("userCode", "nullOrEmpty");
		} else {
			int result = userService.findUserCode(userCode);
			if (result > 0 )
				resultMap.put("userCode", "exist");
			else
				resultMap.put("userCode", "noexist");
		}
		return resultMap;
	}

	// 异步查看用户信息
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	@ResponseBody
	public User view(@RequestParam String id) throws Exception{
		// String cjson = "";
		// if(id == null || "".equals(id)) {
		// return "nodate";
		// }else {
		User user = new User();
		try {
			user = userService.getUserById(id);
			// cjson = JSON.toJSONString(user);
		} catch (Exception e) {
			e.printStackTrace();
			// return "failed"; //falied 失败
		}
		// }
		// logger.debug(cjson);
		return user;
	}

	// 用户列表
	@RequestMapping(value = "/userlist.html")
	public String getUserList(Model model, @RequestParam(value = "queryname", required = false) String queryUserName,
			@RequestParam(value = "queryUserRole", required = false) String queryUserRole,
			@RequestParam(value = "pageIndex", required = false) String pageIndex)throws Exception {
		int _queryUserRole = 0;
		List<User> userList = null;
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 当前页码
		int currentPageNo = 1;

		if (queryUserName == null) {
			queryUserName = "";
		}
		if (queryUserRole != null && !queryUserRole.equals("")) {
			_queryUserRole = Integer.parseInt(queryUserRole);
		}

		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/user/syserror.html";
			}
		}
		// 总数量（表）
		int totalCount = userService.getUserCount(queryUserName, _queryUserRole);
		// 总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		// 控制首页和尾页
		if (currentPageNo < 1) {
			currentPageNo = 1;
		} else if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		userList = userService.getUserList(queryUserName, _queryUserRole, (currentPageNo-1)*pageSize, pageSize);
		model.addAttribute("userList", userList);
		List<Role> roleList = null;
		roleList = roleService.getRoleList();
		model.addAttribute("roleList", roleList);
		model.addAttribute("queryUserName", queryUserName);
		model.addAttribute("queryUserRole", queryUserRole);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "userlist";
	}
	
	// 进入添加用户页面
		@RequestMapping(value = "/useradd.html", method = RequestMethod.GET)
		public String userAdd(@ModelAttribute("user") User user) throws Exception{
			return "useradd";
		}

		// 添加用户操作
		@RequestMapping(value = "/useraddsave.html", method = RequestMethod.POST)
		public String addSave(User user, HttpSession session, HttpServletRequest request,
				@RequestParam(value = "attachs", required = false) MultipartFile[] attachs) throws Exception{
			String idPicPath = null; // 存储照片的路径
			String workPicPath = null; // 工作证照片路径
			String errorInfo = null;
			boolean flag = true;
			String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles"); // 存储路径
			logger.info("uploadFile path =========>" + path);
			for (int i = 0; i < attachs.length; i++) {
				MultipartFile attach = attachs[i];
				if (!attach.isEmpty()) {
					if (i == 0) {
						errorInfo = "uploadFileError";
					} else if (i == 1) {
						errorInfo = "uploadWpError";
					}
					String oldFileName = attach.getOriginalFilename(); // 源文件名,Original（原文）
					String prefix = FilenameUtils.getExtension(oldFileName); // 原文件名后缀
					int filesize = 5000000; // 限制文件上传大小
					logger.debug("uploadFile size -------->" + attach.getSize());
					if (attach.getSize() > filesize) { // 上传文件不得超过500kb
						request.setAttribute("uploadFileError", "*上传文件不得超过500kb");
						flag = false;
					} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
							|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) { // 上传图片格式不正确
						String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_Personal.jpg";
						File targetFile = new File(path, fileName);
						if (!targetFile.exists()) { // 判断是否存在文件
							targetFile.mkdirs(); // 不存在即创建目录
						}
						// 保存
						try {
							attach.transferTo(targetFile);
						} catch (Exception e) {
							e.printStackTrace();
							request.setAttribute(errorInfo, "* 上传失败");
							flag = false;
						}
						if (i == 0) {
							idPicPath = path + File.separator + fileName;
						} else if (i == 1) {
							workPicPath = path + File.separator + fileName;
						}
						logger.debug("idPicPath: " + idPicPath);
						logger.debug("workPicPath: " + workPicPath);
					} else {
						request.setAttribute(errorInfo, "* 上传图片格式不正确");
						flag = false;
					}
				}
			}
			// 文件上传成功则添加到数据库
			if (flag) {
				user.setCreatedBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
				user.setCreationDate(new Date());
				user.setIdPicPath(idPicPath);
				user.setWorkPicPath(workPicPath);
				if (userService.add(user)>0) {
					return "redirect:/user/userlist.html";
				}
			}
			// 添加不成功就返回到当前
			return "useradd";
		}
		// 根据id查找用户信息
		@RequestMapping(value = "/usermodify.html", method = RequestMethod.GET)
		public String getUserById(@RequestParam String uid, Model model) throws Exception{
			logger.debug("getUserById uid=======" + uid);
			User user = userService.getUserById(uid);
			model.addAttribute(user);
			return "usermodify";
		}

		// 修改用户信息
		@RequestMapping(value = "/usermodifysave.html", method = RequestMethod.POST)
		public String userModify(User user, HttpSession session,HttpServletRequest request,
				@RequestParam(value = "attachs", required = false) MultipartFile[] attachs) throws Exception{
			// 创建存储路径
			String idPicPath = null; // 个人照片
			String workPicPath = null; // 工作照片
			String errorInfo = null;
			boolean flag = true;
			String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles"); // 存储路径
			for (int i = 0; i < attachs.length; i++) {
				MultipartFile attach = attachs[i];
				if (!attach.isEmpty()) {
					if (i == 0) {
						errorInfo = "comError";
					} else if (i == 1) {
						errorInfo = "orgError";
					}
					String oldFileName = attach.getOriginalFilename(); // 源文件名字
					String suffix = FilenameUtils.getExtension(oldFileName); // 获取后缀
					int filesize = 5000000; // 上传大小限制
					if (attach.getSize() > filesize) {
						request.setAttribute(errorInfo, "* 上传大小不能超过500KB");
						flag = false;
					} else if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg")
							|| suffix.equalsIgnoreCase("png") || suffix.equalsIgnoreCase("pneg")) {
						// 重新命名文件
						String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_Personal.jpg";
						File targetFile = new File(path, fileName);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						// 保存
						try {
							attach.transferTo(targetFile); // 上传到目标路径
						} catch (Exception e) {
							e.printStackTrace();
							request.setAttribute(errorInfo, "* 上传失败");
							flag = false;
						}
						if(i == 0) {
							idPicPath = fileName;
						}else if(i==1) {
							workPicPath = fileName;
						}
						logger.debug("-----------------------"+path);
						logger.debug("companyLicPicPath: " + idPicPath);
						logger.debug("orgCodePicPath: " + workPicPath);
					} else {
						request.setAttribute(errorInfo, "* 上传图片格式不正确");
						flag = false;
					}
				}
			}
			//文件上传成功则添加到数据库
			if(flag) {
				logger.debug("user modify -----------" + user.getId());
				user.setModifyBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
				user.setIdPicPath(idPicPath);
				user.setWorkPicPath(workPicPath);
				user.setModifyDate(new Date());
				if (userService.modify(user)>0) {
					logger.info("--------------------------" + user.getWorkPicPath());
					return "redirect:/user/userlist.html";
				}
			}
			return "usermodify";
		}
	
}
