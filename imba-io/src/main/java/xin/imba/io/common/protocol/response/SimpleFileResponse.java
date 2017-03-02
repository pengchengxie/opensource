package xin.imba.io.common.protocol.response;


import xin.imba.io.common.TuringJson;


/**
 * 
 * <b>简述：文件上传后返回内容</b>
 * 
 * @author : xiepengcheng 2016年11月21日 下午3:56:11
 */
public class SimpleFileResponse extends BaseUploadResponse {

	@Override
	public String toString() {
		return TuringJson.toJSONString( this, false );
	}

}
