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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

@Controller
@RequestMapping("/provider")
public class ProviderController extends BaseController {
	private Logger logger = Logger.getLogger(ProviderController.class);

	// 注入供应商Service
	@Resource
	private ProviderService providerService;

	// 注入订单service
	@Resource
	private BillService billService;

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	// 删除供应商
	@RequestMapping(value = "/delete.json")
	@ResponseBody
	public Object deleteProvider(@RequestParam String id,HttpServletRequest request) throws Exception {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		// 根据id查询有没有订单信息
		List<Bill> billList = billService.getBillByProviderId(id);
		// 获取供应商信息
		Provider provider = providerService.getProviderById(id);
		if (billList.size() > 0) {
			// 先删除订单
			billService.deleteBillByProviderId(id);
		}
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles"); // 存储路径
		// 判断供应商是否存在本地文件
		if (provider.getCompanyLicPicPath() != null) {
			File targetFile = new File(path, provider.getCompanyLicPicPath());
			if (targetFile.exists()) {
				targetFile.delete();
			}
		}
		// 如果存在workPicPath，则先删除文件
		if (provider.getOrgCodePicPath() != null) {
			File targetFile = new File(path, provider.getOrgCodePicPath());
			if (targetFile.exists()) {
				targetFile.delete();
			}
		}
		// 如果该供应商没有订单，则直接删除供应商
		if (providerService.delete(Integer.parseInt(id))) {
			resultMap.put("delResult", "true");
		} else {
			resultMap.put("delResult", "false");
		}
		return resultMap;
	}

	// 跳转到供应商信息修改页面
	@RequestMapping(value = "/providermodify.html", method = RequestMethod.GET)
	public String modify(@RequestParam String id, Model model) throws Exception {
		Provider provider = providerService.getProviderById(id);
		model.addAttribute(provider);
		return "providermodify";
	}

	// 修改供应商信息
	@RequestMapping(value = "/providermodify.html", method = RequestMethod.POST)
	public String modifyProvider(Provider provider, HttpSession session) throws Exception {
		provider.setCreatedBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
		provider.setCreationDate(new Date());
		if (providerService.update(provider)) {
			return "redirect:/provider/providerList.html";
		}
		return "providermodify.html";
	}

	// 查看供应商信息
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable String id, Model model) throws Exception {
		logger.info("-----------------------------供应商查询");
		Provider provider = providerService.getProviderById(id);
		model.addAttribute(provider);
		return "providerview";
	}

	// 跳转到添加供应商信息
	@RequestMapping(value = "/providerAdd.html", method = RequestMethod.GET)
	public String providerAdd() {
		return "providerAdd";
	}

	// 完成添加供应商信息
	@RequestMapping(value = "/providerAdd.html", method = RequestMethod.POST)
	public String providerAdd(@ModelAttribute("provider") Provider provider, User user, HttpSession session)
			throws Exception {
		provider.setCreatedBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
		provider.setCreationDate(new Date());
		if (providerService.add(provider)) {
			return "redirect:/provider/providerList.html";
		}
		return "providerAdd";
	}

	// 供应商列表
	@RequestMapping(value = "/providerList.html")
	public String getProviderList(Model model, @RequestParam(value = "proCode", required = false) String proCode,
			@RequestParam(value = "proName", required = false) String proName,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) throws Exception {
		List<Provider> providerList = null;
		logger.info("获取供应商列表");
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 当前页码
		int currentPageNo = 1;
		if (proCode == null) {
			proCode = "";
		}
		if (proName == null) {
			proName = "";
		}

		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/user/syserror.html";
			}
		}
		// 总数量（表）
		int totalCount = providerService.getProviderCount(proName, proCode);
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
		providerList = providerService.getProviderList(proName, proCode, (currentPageNo-1)*pageSize, pageSize);
		model.addAttribute("providerList", providerList);
		model.addAttribute("proCode", proCode);
		model.addAttribute("proName", proName);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "providerList";
	}

	// 利用Spring MVC标签添加供应商信息，并且利用JSR 303 校验信息
	@RequestMapping(value = "/add.html", method = RequestMethod.GET)
	public String addPro(@ModelAttribute("provider") Provider provider) {
		return "providerAdd";
	}

	@RequestMapping(value = "/add.html", method = RequestMethod.POST)
	public String addPro(Provider provider, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "attachs", required = false) MultipartFile[] attachs) throws Exception {
		// 创建存储路径
		String companyLicPicPath = null; // 企业营业执照
		String orgCodePicPath = null; // 组织机构代码证
		String errorInfo = null;
		boolean flag = true;
		// String path = "Spring-MVC/statics/images";
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles"); // 存储路径
		logger.info("--------------------path-----------" + path);
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
					if (i == 0) {
						companyLicPicPath = path + File.separator + fileName;
					} else if (i == 1) {
						orgCodePicPath = path + File.separator + fileName;
					}
					logger.debug("-----------------------" + path);
					logger.debug("companyLicPicPath: " + companyLicPicPath);
					logger.debug("orgCodePicPath: " + orgCodePicPath);
				} else {
					request.setAttribute(errorInfo, "* 上传图片格式不正确");
					flag = false;
				}
			}
		}
		// 文件上传成功则添加到数据库
		if (flag) {
			provider.setCreatedBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
			provider.setCreationDate(new Date());
			provider.setCompanyLicPicPath(companyLicPicPath);
			provider.setOrgCodePicPath(orgCodePicPath);
			if (providerService.add(provider)) {
				return "redirect:/provider/providerList.html";
			}
		}
		return "providerAdd";
	}

	// 利用Spring MVC标签添加供应商信息，并且利用JSR 303 校验信息
	// @RequestMapping(value="/add.html",method=RequestMethod.POST)
	// public String addPro(@Valid Provider provider,BindingResult
	// bindingResult,HttpSession session) {
	// if(bindingResult.hasErrors()) {
	// logger.info("add provider has Error");
	// return "user/providerAdd";
	// }
	// provider.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
	// provider.setCreationDate(new Date());
	// if(providerService.add(provider)) {
	// return "redirect:/provider/providerList.html";
	// }
	// return "user/providerAdd";
	// }
}
