package xin.imba.io.common.model;


import xin.imba.io.common.TuringJson;


/**
 * 
 * <b>简述：子账号参数</b>
 * 
 * @author : xiepengcheng 2016年12月7日 下午12:09:09
 */
public class Ram {

	private String accessKeyId;

	private String accessKeySecret;

	private String bucketName;


	public String getAccessKeyId() {
		return accessKeyId;
	}


	public void setAccessKeyId( String accessKeyId ) {
		this.accessKeyId = accessKeyId;
	}


	public String getAccessKeySecret() {
		return accessKeySecret;
	}


	public void setAccessKeySecret( String accessKeySecret ) {
		this.accessKeySecret = accessKeySecret;
	}


	public String getBucketName() {
		return bucketName;
	}


	public void setBucketName( String bucketName ) {
		this.bucketName = bucketName;
	}


	public void isValid() throws Exception {
		if ( null == this.getAccessKeyId() || "".equals( this.getAccessKeyId() ) ) {
			throw new Exception( "accessKeyId不能为空！" );
		}

		if ( null == this.getAccessKeySecret() || "".equals( this.getAccessKeySecret() ) ) {
			throw new Exception( "accessKeySecret不能为空！" );
		}

		if ( null == this.getBucketName() || "".equals( this.getBucketName() ) ) {
			throw new Exception( "bucketName不能为空！" );
		}
	}


	@Override
	public String toString() {
		return TuringJson.toJSONString( this, false );
	}
}
