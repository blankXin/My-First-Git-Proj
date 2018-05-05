package cn.smbms.service.bill;

import java.util.List;

import cn.smbms.pojo.Bill;

public interface BillService {
	/**
	 * 获取订单信息
	 * @param productName
	 * @param billCode
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Bill> getBillList(String productName,String billCode,int currentPageNo,int pageSize)throws Exception;
	
	/**
	 * 根据Providerid获取订单信息
	 * @param id
	 * @return
	 */
	List<Bill> getBillByProviderId(String providerId)throws Exception;
	
	/**
	 * 根据ProviderId删除订单信息
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	int deleteBillByProviderId(String providerId)throws Exception;
}
