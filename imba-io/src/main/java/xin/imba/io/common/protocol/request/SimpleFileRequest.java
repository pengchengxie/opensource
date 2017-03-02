package xin.imba.io.common.protocol.request;


import xin.imba.io.common.TuringJson;


/**
 * 
 * <b>简述：文件上传请求实体</b>
 * 
 * @author : xiepengcheng 2016年11月21日 下午3:53:26
 */
public class SimpleFileRequest extends BaseUploadRequest {

	@Override
	public String toString() {
		return TuringJson.toJSONString( this, false );
	}

}
