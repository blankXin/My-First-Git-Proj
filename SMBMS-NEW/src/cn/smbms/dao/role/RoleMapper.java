package cn.smbms.dao.role;

import java.util.List;

import cn.smbms.pojo.Role;

public interface RoleMapper {
	/**
	 * 获取角色信息
	 * @return
	 */
	public List<Role> getRoleList();
}
