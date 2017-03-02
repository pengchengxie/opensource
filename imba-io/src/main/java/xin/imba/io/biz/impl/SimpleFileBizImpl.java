package xin.imba.io.biz.impl;


import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import xin.imba.io.biz.FileBiz;
import xin.imba.io.common.constant.OSSConstant;
import xin.imba.io.common.model.FileModel;
import xin.imba.io.common.model.Ram;
import xin.imba.io.common.protocol.request.RequestType;
import xin.imba.io.common.protocol.request.SimpleFileRequest;
import xin.imba.io.common.protocol.response.SimpleFileResponse;
import xin.imba.io.utils.FileUtil;
import xin.imba.io.utils.OSSUtil;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;


/**
 * 
 * <b>简述：简单文件上传业务逻辑接口实现类</b>
 * 
 * @author : xiepengcheng 2016年9月23日 下午6:18:58
 */
@Service
public class SimpleFileBizImpl implements FileBiz< SimpleFileResponse, SimpleFileRequest > {

	private Logger logger = Logger.getLogger( this.getClass() );


	/**
	 * 
	 * <b>简述：简单上传</b>
	 * 
	 * @param simpleUploadRequest
	 * @return
	 * @throws Exception
	 * @see com.turing.baseio.biz.FileBiz#uploadFile(com.turing.baseio.common.protocol.request.SimpleUploadRequest)
	 */
	@Override
	public SimpleFileResponse uploadFile( SimpleFileRequest simpleFileRequest ) throws Exception {
		Ram ram = simpleFileRequest.getRam();
		FileModel fileModel = simpleFileRequest.getFileModel();
		SimpleFileResponse simpleFileResponse = new SimpleFileResponse();

		OSSClient ossClient = null;
		if ( null != simpleFileRequest.getRequestType() && simpleFileRequest.getRequestType().equals( RequestType.intranet.toString() ) ) {
			ossClient = new OSSClient( OSSConstant.END_POINT_INTERNAL, ram.getAccessKeyId(), ram.getAccessKeySecret() );
		} else {
			ossClient = new OSSClient( OSSConstant.END_POINT, ram.getAccessKeyId(), ram.getAccessKeySecret() );
		}

		try {
			if ( ossClient.doesBucketExist( ram.getBucketName() ) ) {
				ObjectMetadata meta = new ObjectMetadata();
				if ( null != fileModel.getMd5() && !"".equals( fileModel.getMd5() ) ) {
					byte[] in2b = FileUtil.input2byte( fileModel.getInputStream() );
					meta.setContentMD5( fileModel.getMd5() );
					ossClient.putObject( ram.getBucketName(), StringUtils.removeStart( fileModel.getName(), "/" ), new ByteArrayInputStream( in2b ), meta );

				} else {
					ossClient.putObject( ram.getBucketName(), StringUtils.removeStart( fileModel.getName(), "/" ), fileModel.getInputStream() );
				}

				logger.info( "Object：" + fileModel.getName() + "存入OSS成功。" );

				URL url = OSSUtil.getUrl( fileModel.getName(), fileModel.getExpiration(), ossClient, ram.getBucketName() );
				fileModel.setUrl( url.toString() );
				fileModel.setCreateTime( new Date() );

				simpleFileResponse.setCreateTime( fileModel.getCreateTime() );
				simpleFileResponse.setExpiration( fileModel.getExpiration() );
				simpleFileResponse.setUrl( fileModel.getUrl() );

			} else {
				logger.error( "Bucket不存在：" + ram.getBucketName() + "。" );
			}

		} catch ( OSSException oe ) {
			oe.printStackTrace();
		} catch ( ClientException ce ) {
			ce.printStackTrace();
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			ossClient.shutdown();
		}

		return simpleFileResponse;
	}

}
