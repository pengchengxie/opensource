package xin.imba.io.service.impl;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import xin.imba.io.common.constant.LogConstant;
import xin.imba.io.common.constant.OSSConstant;
import xin.imba.io.common.model.STSToken;
import xin.imba.io.common.protocol.request.STSTokenRequest;
import xin.imba.io.common.protocol.request.SecurityFileRequest;
import xin.imba.io.service.TokenService;
import xin.imba.io.utils.STSUtil;

import com.aliyuncs.exceptions.ClientException;


/**
 * 
 * <b>简述：令牌接口实现类</b>
 * 
 * @author : xiepengcheng 2016年12月4日 下午9:16:32
 */
@Service
public class TokenServiceImpl implements TokenService {

	private Logger logger = Logger.getLogger( this.getClass() );


	@Override
	public List< STSToken > getSTSToken( STSTokenRequest stsTokenRequest ) {
		try {
			STSToken stsToken =
					STSUtil.getSTSToken( stsTokenRequest.getAccessKeyId(), stsTokenRequest.getAccessKeySecret(), stsTokenRequest.getRoleArn(), stsTokenRequest
							.getRoleSessionName(), stsTokenRequest.getPolicy() );
			List< STSToken > stsTokens = new ArrayList< STSToken >();
			stsTokens.add( stsToken );

			return stsTokens;
		} catch ( ClientException e ) {
			logger.error( LogConstant.ERROR_LOG_PREFIX + "Failed to get a token. Error code: " + e.getErrCode() + "Error message: " + e.getErrMsg() );
		}

		return null;
	}


	@Override
	public String getUrlByToken( SecurityFileRequest securityFileRequest ) throws Exception {
		securityFileRequest.isValid();
		URL TempUrl =
				STSUtil.getTempUrl( securityFileRequest.getKey(), securityFileRequest.getExpiration(), securityFileRequest.getBucketName(), securityFileRequest
						.getStsToken() );

		String ossDomain = "http://" + securityFileRequest.getBucketName() + "." + OSSConstant.DOMAIN_NAME;
		String url = TempUrl.toString().replace( ossDomain, securityFileRequest.getDomainName() );

		return url;
	}

}
