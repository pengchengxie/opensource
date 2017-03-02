package xin.imba.io.common.model;


import xin.imba.io.common.TuringJson;


/**
 * 
 * <b>简述：访问OSS临时令牌</b>
 * 
 * @author : xiepengcheng 2016年12月2日 下午6:24:18
 */
public class STSToken {

	/**
	 * 令牌到期时间
	 */
	private String expiration;

	/**
	 * 访问id
	 */
	private String accessKeyId;

	/**
	 * 访问秘钥
	 */
	private String accessKeySecret;

	/**
	 * 令牌
	 */
	private String securityToken;


	public String getExpiration() {
		return expiration;
	}


	public void setExpiration( String expiration ) {
		this.expiration = expiration;
	}


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


	public String getSecurityToken() {
		return securityToken;
	}


	public void setSecurityToken( String securityToken ) {
		this.securityToken = securityToken;
	}


	public void isValid() throws Exception {

		if ( null == this.getExpiration() || "".equals( this.getExpiration() ) ) {
			throw new Exception( "expiration不能为空！" );
		}

		if ( null == this.getAccessKeyId() || "".equals( this.getAccessKeyId() ) ) {
			throw new Exception( "accessKeyId不能为空！" );
		}

		if ( null == this.getAccessKeySecret() || "".equals( this.getAccessKeySecret() ) ) {
			throw new Exception( "accessKeySecret不能为空！" );
		}

		if ( null == this.getSecurityToken() || "".equals( this.getSecurityToken() ) ) {
			throw new Exception( "securityToken不能为空！" );
		}
	}


	@Override
	public String toString() {
		return TuringJson.toJSONString( this, false );
	}
}
