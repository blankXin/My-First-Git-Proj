package cn.smbms.service.provider;

import java.util.List;

import cn.smbms.pojo.Provider;

public interface ProviderService {
	/**
	 * 根据ID查找Provider
	 * @param id
	 * @return
	 */
	public Provider getProviderById(String id)throws Exception;
	/**
	 * 获取供应商列表
	 * @param connection
	 * @param proName
	 * @return
	 */
	List<Provider> getProviderList(String proName,String proCode, int currentPageNo, int pageSize)throws Exception;
	
	/**
	 * 增加供应商列表
	 * @param connection
	 * @param provider
	 * @return
	 */
	boolean add(Provider provider)throws Exception;
	
	/**
	 * 更新供应商信息
	 * @param connection
	 * @param provider
	 * @return
	 */
	boolean update(Provider provider)throws Exception;
	
	/**
	 * 删除供应商
	 * @param connection
	 * @param id
	 * @return
	 */
	boolean delete(Integer id)throws Exception;
	
	/**
	 * 查询总数据
	 * @param connection
	 * @param proName
	 * @param proCode
	 * @return
	 * @throws Exception
	 */
	int getProviderCount(String proName,String proCode)throws Exception;
}
