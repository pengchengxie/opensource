package xin.imba.io.common.protocol.request;


import java.util.Date;

import xin.imba.io.common.TuringJson;
import xin.imba.io.common.model.STSToken;


/**
 * 
 * <b>简述：</b>
 * 
 * @author : xiepengcheng 2017年2月10日 下午2:23:52
 */
public class SecurityFileRequest {

	private String key;
	private Date expiration;
	private String bucketName;
	private String domainName;
	private STSToken stsToken;


	public String getKey() {
		return key;
	}


	public void setKey( String key ) {
		this.key = key;
	}


	public Date getExpiration() {
		return expiration;
	}


	public void setExpiration( Date expiration ) {
		this.expiration = expiration;
	}


	public String getBucketName() {
		return bucketName;
	}


	public void setBucketName( String bucketName ) {
		this.bucketName = bucketName;
	}


	public String getDomainName() {
		return domainName;
	}


	public void setDomainName( String domainName ) {
		this.domainName = domainName;
	}


	public STSToken getStsToken() {
		return stsToken;
	}


	public void setStsToken( STSToken stsToken ) {
		this.stsToken = stsToken;
	}


	public void isValid() throws Exception {
		if ( null == this.getKey() || "".equals( this.getKey() ) ) {
			throw new Exception( "key不能为空！" );
		}

		if ( null == this.getExpiration() || "".equals( this.getExpiration() ) ) {
			this.setExpiration( new Date( new Date().getTime() + 1000 * 3600 ) );
		}
		if ( null == this.getBucketName() || "".equals( this.getBucketName() ) ) {
			throw new Exception( "bucketName不能为空！" );
		}

		if ( null == this.getDomainName() || "".equals( this.getDomainName() ) ) {
			throw new Exception( "domainName不能为空！" );
		}

		if ( null == this.getStsToken() || "".equals( this.getStsToken() ) ) {
			throw new Exception( "stsToken不能为空！" );
		} else {
			this.getStsToken().isValid();
		}
	}


	@Override
	public String toString() {
		return TuringJson.toJSONString( this, false );
	}

}
