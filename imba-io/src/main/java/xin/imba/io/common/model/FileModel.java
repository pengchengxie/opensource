package xin.imba.io.common.model;


import java.io.InputStream;
import java.util.Date;

import xin.imba.io.common.TuringJson;
import xin.imba.io.common.constant.OSSConstant;


/**
 * 
 * <b>简述：文件模型</b>
 * 
 * @author : xiepengcheng 2016年11月21日 下午3:53:26
 */
public class FileModel {

	/**
	 * 创建时间，可以为空
	 */
	private Date createTime;

	/**
	 * 修改时间，可以为空
	 */
	private Date modifyTime;

	/**
	 * 输入流
	 */
	private InputStream inputStream;

	/**
	 * 文件名称
	 */
	private String name;

	/**
	 * 文件路径
	 */
	private String path;

	/**
	 * 文件流大小
	 */
	private Long size;

	/**
	 * 文件所属
	 */
	private String owner;

	/**
	 * 文件MD5，非必须，如果为空则默认不进行MD5校验
	 */
	private String md5;

	/**
	 * 文件生成的url终止时间
	 */
	private Date expiration;

	/**
	 * 连接
	 */
	private String url;

	/**
	 * 文件描述信息
	 */
	private String notes;


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime( Date createTime ) {
		this.createTime = createTime;
	}


	public Date getModifyTime() {
		return modifyTime;
	}


	public void setModifyTime( Date modifyTime ) {
		this.modifyTime = modifyTime;
	}


	public InputStream getInputStream() {
		return inputStream;
	}


	public void setInputStream( InputStream inputStream ) {
		this.inputStream = inputStream;
	}


	public String getName() {
		return name;
	}


	public void setName( String name ) {
		this.name = name;
	}


	public String getPath() {
		return path;
	}


	public void setPath( String path ) {
		this.path = path;
	}


	public Long getSize() {
		return size;
	}


	public void setSize( Long size ) {
		this.size = size;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner( String owner ) {
		this.owner = owner;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes( String notes ) {
		this.notes = notes;
	}


	public String getMd5() {
		return md5;
	}


	public void setMd5( String md5 ) {
		this.md5 = md5;
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


	public void isValid() throws Exception {
		if ( null == this.getInputStream() || "".equals( this.getInputStream() ) ) {
			throw new Exception( "inputStream不能为空！" );
		}

		if ( null == this.getName() || "".equals( this.getName() ) ) {
			throw new Exception( "fileName不能为空！" );
		}

		if ( null == this.getExpiration() || "".equals( this.getExpiration() ) ) {
			this.setExpiration( new Date( OSSConstant.EXPIRATION_TIME ) );
		}
	}


	@Override
	public String toString() {
		return TuringJson.toJSONString( this, false );
	}
}
