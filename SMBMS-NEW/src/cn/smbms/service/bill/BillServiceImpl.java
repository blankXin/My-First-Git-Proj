package cn.smbms.service.bill;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import cn.smbms.dao.bill.BillMapper;
import cn.smbms.pojo.Bill;

@Service
public class BillServiceImpl implements BillService{

	@Resource
	private BillMapper billMapper;
	
	@Override
	public List<Bill> getBillList(String productName, String billCode, int currentPageNo, int pageSize)
			throws Exception {
		return billMapper.getBillList(productName, billCode, currentPageNo, pageSize);
	}

	@Override
	public List<Bill> getBillByProviderId(String providerId) throws Exception {
		return billMapper.getBillByProviderId(providerId);
	}

	@Override
	public int deleteBillByProviderId(String providerId) throws Exception {
		return billMapper.deleteBillByProviderId(providerId);
	}

}
