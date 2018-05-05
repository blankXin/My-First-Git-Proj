package cn.smbms.service.provider;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.pojo.Provider;

@Service
public class ProviderServiceImpl implements ProviderService {

	// 注入Provider
	@Resource
	private ProviderMapper providerMapper;

	@Override
	public Provider getProviderById(String id) throws Exception{
		return providerMapper.getProviderById(id);
	}

	@Override
	public List<Provider> getProviderList(String proName, String proCode, int currentPageNo, int pageSize) throws Exception{
		return providerMapper.getProviderList(proName, proCode, currentPageNo, pageSize);
	}

	@Override
	public boolean add(Provider provider) throws Exception{
		boolean flag = false;
		if(providerMapper.add(provider)>0)
			flag = true;
		return flag;
	}

	@Override
	public boolean update(Provider provider) throws Exception{
		boolean flag = false;
		if(providerMapper.update(provider)>0)
			flag = true;
		return flag;
	}

	@Override
	public boolean delete(Integer id)throws Exception {
		boolean flag = false;
		if(providerMapper.delete(id)>0)
			flag = true;
		return flag;
	}

	@Override
	public int getProviderCount(String proName, String proCode) throws Exception{
		return providerMapper.getProviderCount(proName, proCode);
	}


}
