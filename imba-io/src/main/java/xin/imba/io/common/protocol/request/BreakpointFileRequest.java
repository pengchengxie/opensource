package xin.imba.io.common.protocol.request;


import xin.imba.io.common.TuringJson;
import xin.imba.io.common.model.ShardModel;


/**
 * 
 * <b>简述：断点续传请求参数</b>
 * 
 * @author : xiepengcheng 2016年12月10日 下午2:05:47
 */
public class BreakpointFileRequest extends BaseUploadRequest {

	/**
	 * 本地文件路径
	 */
	private String localFilePath;

	private ShardModel shardModel;


	public String getLocalFilePath() {
		return localFilePath;
	}


	public void setLocalFilePath( String localFilePath ) {
		this.localFilePath = localFilePath;
	}


	public ShardModel getShardModel() {
		return shardModel;
	}


	public void setShardModel( ShardModel shardModel ) {
		this.shardModel = shardModel;
	}


	public void isValid() throws Exception {
		if ( null == this.getLocalFilePath() || "".equals( this.getLocalFilePath() ) ) {
			throw new Exception( "localFilePath不能为空！" );
		}

		if ( null == this.getShardModel() ) {
			this.setShardModel( new ShardModel() );
		}
	}


	@Override
	public String toString() {
		return TuringJson.toJSONString( this, false );
	}

}
