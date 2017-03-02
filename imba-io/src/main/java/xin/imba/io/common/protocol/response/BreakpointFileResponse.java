package xin.imba.io.common.protocol.response;


import xin.imba.io.common.TuringJson;


/**
 * 
 * <b>简述：断点续传放回参数</b>
 * 
 * @author : xiepengcheng 2016年12月10日 下午2:07:34
 */
public class BreakpointFileResponse extends BaseUploadResponse {

	@Override
	public String toString() {
		return TuringJson.toJSONString( this, false );
	}

}
