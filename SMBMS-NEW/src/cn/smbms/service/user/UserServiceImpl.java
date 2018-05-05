package cn.smbms.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	
	@Override
	public User login(String userCode, String userPassword) throws Exception {
		User user = new User();
		user = userMapper.getLoginUser(userCode);
		//匹配密码
		if(user != null) {
			if(!user.getUserPassword().equals(userPassword)) {
				user = null;
			}
		}
		return user;
	}


	@Override
	public int add(User user) throws Exception {
		return userMapper.add(user);
	}


	@Override
	public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize) throws Exception {
		return userMapper.getUserList(userName, userRole, currentPageNo, pageSize);
	}


	@Override
	public int getUserCount(String userName, int userRole) throws Exception {
		return userMapper.getUserCount(userName, userRole);
	}


	@Override
	public int deleteUserById(Integer delId) throws Exception {
		return userMapper.deleteUserById(delId);
	}


	@Override
	public User getUserById(String id) throws Exception {
		return userMapper.getUserById(id);
	}


	@Override
	public int modify(User user) throws Exception {
		return userMapper.modify(user);
	}


	@Override
	public int updatePwd(int id, String pwd) throws Exception {
		return userMapper.updatePwd(id, pwd);
	}


	@Override
	public int findUserCode(String userCode) throws Exception {
		return userMapper.findUserCode(userCode);
	}

}
