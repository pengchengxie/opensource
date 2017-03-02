package xin.imba.io.service.impl;


import xin.imba.io.service.DirectoryService;
import xin.imba.io.utils.OSSUtil;

import com.aliyun.oss.OSSClient;


/**
 * 
 * <b>简述：接口实现类 - OSS目录 </b>
 * 
 * @author : xiepengcheng 2016年12月9日 下午10:25:59
 */
public class DirectoryServiceImpl implements DirectoryService {

	@Override
	public String mkdir( OSSClient ossClient, String bucketName, String folderName ) {

		OSSUtil.mkdir( ossClient, bucketName, folderName );

		return "sucess";
	}

}
