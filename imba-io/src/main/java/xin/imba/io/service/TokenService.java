package xin.imba.io.service;


import java.util.List;

import xin.imba.io.common.model.STSToken;
import xin.imba.io.common.protocol.request.STSTokenRequest;
import xin.imba.io.common.protocol.request.SecurityFileRequest;

import com.aliyuncs.exceptions.ClientException;


/**
 * 
 * <b>简述：接口 - 令牌接口</b>
 * 
 * @author : xiepengcheng 2016年12月2日 下午6:36:09
 */
public interface TokenService {

	/**
	 * 
	 * <b>简述：获取临时令牌</b>
	 * 
	 * @return
	 */
	public List< STSToken > getSTSToken( STSTokenRequest stsTokenRequest );


	/**
	 * 
	 * <b>简述：通过临时令牌获取临时房屋URL</b>
	 * 
	 * @param securityFileRequest
	 * @return
	 * @throws ClientException
	 * @throws InterruptedException
	 */
	public String getUrlByToken( SecurityFileRequest securityFileRequest ) throws Exception;

}
