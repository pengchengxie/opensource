package xin.imba.io.service;


import com.aliyun.oss.OSSClient;


/**
 * 
 * <b>简述：接口 - OSS目录</b>
 * 
 * @author : xiepengcheng 2016年12月9日 下午5:18:18
 */
public interface DirectoryService {

	public String mkdir( OSSClient ossClient, String bucketName, String folderName );
}
