package xin.imba.io.service.impl;


import org.apache.log4j.Logger;

import xin.imba.io.common.constant.OSSConstant;
import xin.imba.io.common.model.FileModel;
import xin.imba.io.common.model.Ram;
import xin.imba.io.common.protocol.request.BaseUploadRequest;
import xin.imba.io.common.protocol.request.RequestType;
import xin.imba.io.common.protocol.response.BaseUploadResponse;

import com.aliyun.oss.OSSClient;


public class BaseFileServiceImpl {

	private Logger logger = Logger.getLogger( this.getClass() );


	protected String urlFormat( BaseUploadRequest baseUploadRequest, BaseUploadResponse baseUploadResponse ) {

		String ossDomain = "http://" + baseUploadRequest.getRam().getBucketName() + "." + OSSConstant.DOMAIN_NAME;
		String url = baseUploadResponse.getUrl().replace( ossDomain, baseUploadRequest.getDomainName() );

		return url;

	}


	/**
	 * 
	 * <b>简述：删除OSS指定BUCKET的指定文件</b>
	 * 
	 * @param fileUploadRequest
	 */
	public void deleteFile( BaseUploadRequest baseUploadRequest ) {
		Ram ram = baseUploadRequest.getRam();
		FileModel fileModel = baseUploadRequest.getFileModel();
		OSSClient ossClient = null;
		if ( null != baseUploadRequest.getRequestType() && baseUploadRequest.getRequestType().equals( RequestType.intranet.toString() ) ) {
			ossClient = new OSSClient( OSSConstant.END_POINT_INTERNAL, ram.getAccessKeyId(), ram.getAccessKeySecret() );
		} else {
			ossClient = new OSSClient( OSSConstant.END_POINT, ram.getAccessKeyId(), ram.getAccessKeySecret() );
		}

		if ( ossClient.doesBucketExist( ram.getBucketName() ) ) {
			ossClient.deleteObject( ram.getBucketName(), fileModel.getName() );
			logger.info( "删除Object：" + fileModel.getName() + "成功。" );
		} else {
			logger.error( "Bucket不存在：" + ram.getBucketName() + "。" );
		}
	}

}
