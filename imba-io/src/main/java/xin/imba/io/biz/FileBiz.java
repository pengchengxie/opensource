package xin.imba.io.biz;


/**
 * 
 * <b>简述：文件上传接口</b>
 * 
 * @author : xiepengcheng 2016年9月23日 下午6:16:13
 */
public interface FileBiz< T, E > {

	/**
	 * 
	 * <b>简述：文件上传接口</b>
	 * 
	 * @param e
	 * @return
	 * @throws Exception
	 */
	public T uploadFile( E e ) throws Exception;

}
