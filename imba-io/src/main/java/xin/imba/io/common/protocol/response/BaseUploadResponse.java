package xin.imba.io.common.protocol.response;


import java.util.Date;

import xin.imba.io.common.TuringJson;


/**
 * 
 * <b>简述：文件上传后返回内容</b>
 * 
 * @author : xiepengcheng 2016年11月21日 下午3:56:11
 */
public class BaseUploadResponse {

	/**
	 * 创建时间，可以为空
	 */
	private Date createTime;

	/**
	 * 文件生成的url终止时间
	 */
	private Date expiration;

	/**
	 * 连接
	 */
	private String url;


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime( Date createTime ) {
		this.createTime = createTime;
	}


	public Date getExpiration() {
		return expiration;
	}


	public void setExpiration( Date expiration ) {
		this.expiration = expiration;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl( String url ) {
		this.url = url;
	}


	@Override
	public String toString() {
		return TuringJson.toJSONString( this, false );
	}

}
