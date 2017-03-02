package xin.imba.io.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xin.imba.io.biz.FileBiz;
import xin.imba.io.common.protocol.request.SimpleFileRequest;
import xin.imba.io.common.protocol.response.SimpleFileResponse;
import xin.imba.io.service.FileService;


/**
 * 
 * <b>简述：简单文件接口</b>
 * 
 * @author : xiepengcheng 2016年12月4日 下午9:15:00
 */
@Service
public class SimpleFileServiceImpl extends BaseFileServiceImpl implements FileService< SimpleFileResponse, SimpleFileRequest > {

	@Resource( name = "simpleFileBizImpl" )
	private FileBiz< SimpleFileResponse, SimpleFileRequest > fileBiz;


	@Override
	public SimpleFileResponse uploadFile( SimpleFileRequest simpleFileRequest ) throws Exception {
		simpleFileRequest.isValid();

		SimpleFileResponse simpleFileResponse = fileBiz.uploadFile( simpleFileRequest );

		simpleFileResponse.setUrl( urlFormat( simpleFileRequest, simpleFileResponse ) );

		return simpleFileResponse;

	}


	@Override
	public void deleteFile( SimpleFileRequest simpleFileRequest ) {
		super.deleteFile( simpleFileRequest );

	}

}
