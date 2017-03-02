package xin.imba.io.common.protocol.request;


import xin.imba.io.common.TuringJson;
import xin.imba.io.common.model.FileModel;
import xin.imba.io.common.model.Ram;


public class BaseUploadRequest {

	/**
	 * 请求类型，通用外网请求，内网请求
	 */
	private String requestType = RequestType.outernet.toString();

	/**
	 * 返回url的域名，如appfile.tuling123.com
	 */
	private String domainName;

	/**
	 * 子账号
	 */
	private Ram ram;

	/**
	 * 文件模型
	 */
	private FileModel fileModel;


	public String getRequestType() {
		return requestType;
	}


	public void setRequestType( String requestType ) {
		this.requestType = requestType;
	}


	public String getDomainName() {
		return domainName;
	}


	public void setDomainName( String domainName ) {
		this.domainName = domainName;
	}


	public Ram getRam() {
		return ram;
	}


	public void setRam( Ram ram ) {
		this.ram = ram;
	}


	public FileModel getFileModel() {
		return fileModel;
	}


	public void setFileModel( FileModel fileModel ) {
		this.fileModel = fileModel;
	}


	public void isValid() throws Exception {
		if ( null == this.getDomainName() || "".equals( this.getDomainName() ) ) {
			throw new Exception( "domainName不能为空！" );
		}

		if ( null == this.getRam() ) {
			throw new Exception( "ram不能为空！" );
		} else {
			this.getRam().isValid();
		}

		if ( null == this.getFileModel() ) {
			throw new Exception( "fileModel不能为空！" );
		} else {
			this.getFileModel().isValid();
		}
	}


	@Override
	public String toString() {
		return TuringJson.toJSONString( this, false );
	}

}
