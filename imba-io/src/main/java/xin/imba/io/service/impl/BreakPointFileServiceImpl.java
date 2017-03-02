package xin.imba.io.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xin.imba.io.biz.FileBiz;
import xin.imba.io.common.protocol.request.BreakpointFileRequest;
import xin.imba.io.common.protocol.response.BreakpointFileResponse;
import xin.imba.io.service.FileService;


/**
 * 
 * <b>简述：应用商城专用文件接口</b>
 * 
 * @author : xiepengcheng 2016年12月4日 下午9:15:00
 */
@Service
public class BreakPointFileServiceImpl extends BaseFileServiceImpl implements FileService< BreakpointFileResponse, BreakpointFileRequest > {

	@Resource( name = "breakpointFileBizImpl" )
	private FileBiz< BreakpointFileResponse, BreakpointFileRequest > fileBiz;


	@Override
	public BreakpointFileResponse uploadFile( BreakpointFileRequest breakpointFileRequest ) throws Exception {
		breakpointFileRequest.isValid();

		BreakpointFileResponse breakpointFileResponse = fileBiz.uploadFile( breakpointFileRequest );

		breakpointFileResponse.setUrl( urlFormat( breakpointFileRequest, breakpointFileResponse ) );

		return breakpointFileResponse;

	}


	@Override
	public void deleteFile( BreakpointFileRequest breakpointFileRequest ) {
		super.deleteFile( breakpointFileRequest );

	}

}
