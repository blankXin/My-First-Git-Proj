package cn.smbms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.smbms.pojo.Role;
import cn.smbms.service.role.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	//使用注解倒入roleService
	@Resource
	private RoleService roleService;
	
	//实现跳转到Role页面
	@RequestMapping("/roleList.html")
	public String getRole(Model model) throws Exception {
		List<Role> roleList = new ArrayList<Role>();
		roleList = roleService.getRoleList();
		model.addAttribute("roleList",roleList);
		return "roleList";
	}
	
	//异步获取角色列表
	@RequestMapping(value="/getRoleList.json")
	@ResponseBody
	public Object getRoleList() throws Exception {
		List<Role> roleList = new ArrayList<Role>();
		roleList = roleService.getRoleList();
		String json = JSONArray.toJSONString(roleList);
		System.out.println("----------------json--------------"+json);
		return json;
	}
}
