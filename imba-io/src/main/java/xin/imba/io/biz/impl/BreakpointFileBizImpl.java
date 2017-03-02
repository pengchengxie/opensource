package xin.imba.io.biz.impl;


import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import xin.imba.io.biz.FileBiz;
import xin.imba.io.common.constant.OSSConstant;
import xin.imba.io.common.model.FileModel;
import xin.imba.io.common.model.Ram;
import xin.imba.io.common.model.ShardModel;
import xin.imba.io.common.protocol.request.BreakpointFileRequest;
import xin.imba.io.common.protocol.request.RequestType;
import xin.imba.io.common.protocol.response.BreakpointFileResponse;
import xin.imba.io.utils.OSSUtil;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.UploadFileRequest;


/**
 * 
 * <b>简述：断点续传实现类</b>
 * 
 * @author : xiepengcheng 2016年12月10日 下午3:31:52
 */
@Service
public class BreakpointFileBizImpl implements FileBiz< BreakpointFileResponse, BreakpointFileRequest > {

	private Logger logger = Logger.getLogger( this.getClass() );


	@Override
	public BreakpointFileResponse uploadFile( BreakpointFileRequest breakpointFileRequest ) {
		Ram ram = breakpointFileRequest.getRam();
		FileModel fileModel = breakpointFileRequest.getFileModel();
		ShardModel shardModel = breakpointFileRequest.getShardModel();
		BreakpointFileResponse breakpointFileResponse = new BreakpointFileResponse();

		OSSClient ossClient = null;
		if ( null != breakpointFileRequest.getRequestType() && breakpointFileRequest.getRequestType().equals( RequestType.intranet.toString() ) ) {
			ossClient = new OSSClient( OSSConstant.END_POINT_INTERNAL, ram.getAccessKeyId(), ram.getAccessKeySecret() );
		} else {
			ossClient = new OSSClient( OSSConstant.END_POINT, ram.getAccessKeyId(), ram.getAccessKeySecret() );
		}

		try {
			if ( ossClient.doesBucketExist( ram.getBucketName() ) ) {

				UploadFileRequest uploadFileRequest = new UploadFileRequest( ram.getBucketName(), fileModel.getName() ); // 设置断点续传请求
				uploadFileRequest.setUploadFile( breakpointFileRequest.getLocalFilePath() ); // 指定上传的本地文件
				uploadFileRequest.setTaskNum( shardModel.getTaskNum() ); // 指定上传并发线程数
				uploadFileRequest.setPartSize( shardModel.getPartSize() ); // 指定上传的分片大小
				uploadFileRequest.setEnableCheckpoint( true ); // 开启断点续传
				try {
					ossClient.uploadFile( uploadFileRequest ); // 断点续传上传
				} catch ( Throwable e ) {
					logger.error( "Object：" + fileModel.getName() + "上传失败！" );
					e.printStackTrace();
				}
				logger.info( "Object：" + fileModel.getName() + "存入OSS成功。" );

				URL url = OSSUtil.getUrl( fileModel.getName(), fileModel.getExpiration(), ossClient, ram.getBucketName() );
				fileModel.setUrl( url.toString() );
				fileModel.setCreateTime( new Date() );

				breakpointFileResponse.setCreateTime( fileModel.getCreateTime() );
				breakpointFileResponse.setExpiration( fileModel.getExpiration() );
				breakpointFileResponse.setUrl( fileModel.getUrl() );

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

		return breakpointFileResponse;
	}

}
