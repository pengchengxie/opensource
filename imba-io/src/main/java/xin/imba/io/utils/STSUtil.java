package xin.imba.io.utils;


import java.net.URL;
import java.util.Date;

import xin.imba.io.common.constant.OSSConstant;
import xin.imba.io.common.constant.STSConstant;
import xin.imba.io.common.model.STSToken;

import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;


/**
 * 
 * <b>简述：STS临时凭证公共类</b>
 * 
 * @author : xiepengcheng 2016年12月2日 下午9:54:31
 */
public class STSUtil {

	/**
	 * 
	 * <b>简述：获取访问OSS的临时令牌</b>
	 * 
	 * @param accessKeyId
	 * @param accessKeySecret
	 * @param roleArn
	 * @param roleSessionName
	 * @param policy
	 * @param protocolType
	 * @return
	 * @throws ClientException
	 */
	public static AssumeRoleResponse assumeRole( String accessKeyId, String accessKeySecret, String roleArn, String roleSessionName, String policy, ProtocolType protocolType )
			throws ClientException {
		try {
			// 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
			IClientProfile profile = DefaultProfile.getProfile( STSConstant.REGION_CN_BEIJING, accessKeyId, accessKeySecret );
			DefaultAcsClient client = new DefaultAcsClient( profile );

			final AssumeRoleRequest request = new AssumeRoleRequest();
			request.setVersion( STSConstant.STS_API_VERSION );
			request.setMethod( MethodType.POST );
			request.setProtocol( protocolType );
			request.setRoleArn( roleArn );
			request.setRoleSessionName( roleSessionName );
			request.setPolicy( policy );

			final AssumeRoleResponse response = client.getAcsResponse( request );
			return response;
		} catch ( ClientException e ) {
			throw e;
		}
	}


	/**
	 * 
	 * <b>简述：获取临时令牌-系统默认方法</b>
	 * 
	 * @return
	 * @throws ClientException
	 * @throws InterruptedException
	 */
	// public static STSToken getSTSToken() throws ClientException, InterruptedException {
	//
	// STSToken stsToken = new STSToken();
	// try {
	//
	// final AssumeRoleRequest request = new AssumeRoleRequest();
	// request.setVersion( STSConstant.STS_API_VERSION );
	// request.setMethod( MethodType.POST );
	// request.setProtocol( ProtocolType.HTTPS );
	// request.setRoleArn( STSConstant.ROLE_ARN );
	// request.setRoleSessionName( STSConstant.ROLE_SESSION_NAME );
	// request.setPolicy( STSConstant.POLICY );
	//
	// final AssumeRoleResponse response = TuringSTSClient.getInstance().getAcsClient().getAcsResponse( request );
	//
	// stsToken.setExpiration( response.getCredentials().getExpiration() );
	// stsToken.setAccessKeyId( response.getCredentials().getAccessKeyId() );
	// stsToken.setAccessKeySecret( response.getCredentials().getAccessKeySecret() );
	// stsToken.setSecurityToken( response.getCredentials().getSecurityToken() );
	// return stsToken;
	//
	// } catch ( ClientException e ) {
	// throw e;
	// }
	// }

	/**
	 * 
	 * <b>简述：获取访问OSS的临时令牌</b>
	 * 
	 * @param accessKeyId
	 * @param accessKeySecret
	 * @param roleArn
	 * @param roleSessionName
	 * @param policy
	 * @param protocolType
	 *            必须为HTTPS ProtocolType protocolType = ProtocolType.HTTPS;
	 * @return
	 * @throws ClientException
	 */
	public static STSToken getSTSToken( String accessKeyId, String accessKeySecret, String roleArn, String roleSessionName, String policy )
			throws ClientException {
		STSToken stsToken = new STSToken();
		try {
			AssumeRoleResponse response = assumeRole( accessKeyId, accessKeySecret, roleArn, roleSessionName, policy, ProtocolType.HTTPS );
			stsToken.setExpiration( response.getCredentials().getExpiration() );
			stsToken.setAccessKeyId( response.getCredentials().getAccessKeyId() );
			stsToken.setAccessKeySecret( response.getCredentials().getAccessKeySecret() );
			stsToken.setSecurityToken( response.getCredentials().getSecurityToken() );
			return stsToken;
		} catch ( ClientException e ) {
			throw e;
		}
	}


	/**
	 * 
	 * <b>简述：使用临时令牌获取文件url - 系统默认方法</b>
	 * 
	 * @param key
	 * @param expiration
	 * @param bucketName
	 * @return
	 * @throws ClientException
	 * @throws InterruptedException
	 */
	public static URL getTempUrl( String key, Date expiration, String bucketName, STSToken stsToken ) throws ClientException, InterruptedException {

		OSSClient client = new OSSClient( OSSConstant.END_POINT, stsToken.getAccessKeyId(), stsToken.getAccessKeySecret(), stsToken.getSecurityToken() );

		URL url = client.generatePresignedUrl( bucketName, key, expiration );

		return url;

	}

}
