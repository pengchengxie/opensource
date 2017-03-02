package xin.imba.io.service;


/**
 * 
 * <b>简述：文件上传接口</b>
 * 
 * @author : xiepengcheng 2016年9月23日 下午6:16:13
 */
public interface FileService< T, E > {

	/**
	 * 
	 * <b>简述：上传文件</b>
	 * 
	 * @param fileUploadRequest
	 * @return
	 * @throws Exception
	 */
	public T uploadFile( E e ) throws Exception;


	public void deleteFile( E e );

}
