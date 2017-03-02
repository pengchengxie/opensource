package xin.imba.io.common.protocol.request;


import xin.imba.io.common.model.Ram;


/**
 * 
 * <b>简述：目录操作请求参数</b>
 * 
 * @author : xiepengcheng 2016年12月9日 下午5:21:57
 */
public class DirectoryRequest {

	private Ram ram;

	private String directory;


	public Ram getRam() {
		return ram;
	}


	public void setRam( Ram ram ) {
		this.ram = ram;
	}


	public String getDirectory() {
		return directory;
	}


	public void setDirectory( String directory ) {
		this.directory = directory;
	}

}
