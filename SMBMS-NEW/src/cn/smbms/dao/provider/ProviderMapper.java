package cn.smbms.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Provider;

public interface ProviderMapper {
	
	/**
	 * 根据id获取供应商信息
	 * @param id
	 * @return
	 */
	Provider getProviderById(@Param("id")String id)throws Exception;
	
	/**
	 * 获取供应商列表
	 * @param connection
	 * @param proName
	 * @return
	 */
	List<Provider> getProviderList(@Param("proName")String proName,@Param("proCode")String proCode,
															@Param("currentPageNo") int currentPageNo, @Param("pageSize")int pageSize)throws Exception;
	
	/**
	 * 增加供应商列表
	 * @param connection
	 * @param provider
	 * @return
	 */
	int add(Provider provider)throws Exception;
	
	/**
	 * 更新供应商信息
	 * @param connection
	 * @param provider
	 * @return
	 */
	int update(Provider provider)throws Exception;
	
	/**
	 * 删除供应商
	 * @param connection
	 * @param id
	 * @return
	 */
	int delete(@Param("id")Integer id)throws Exception;
	
	/**
	 * 查询总数据
	 * @param connection
	 * @param proName
	 * @param proCode
	 * @return
	 * @throws Exception
	 */
	int getProviderCount(@Param("proName")String proName,@Param("proCode")String proCode)throws Exception;
}
