package cn.smbms.dao.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;

public interface BillMapper {
	/**
	 * 获取订单信息
	 * @param productName
	 * @param billCode
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Bill> getBillList(@Param("productName")String productName,@Param("billCode")String billCode,
			@Param("currentPageNo") int currentPageNo, @Param("pageSize")int pageSize)throws Exception;
	
	/**
	 * 根据Providerid获取订单信息
	 * @param id
	 * @return
	 */
	List<Bill> getBillByProviderId(@Param("providerId")String providerId)throws Exception;
	
	/**
	 * 根据ProviderId删除订单信息
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	int deleteBillByProviderId(@Param("providerId")String providerId)throws Exception;
}
