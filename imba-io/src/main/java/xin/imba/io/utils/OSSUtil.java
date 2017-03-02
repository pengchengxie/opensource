package xin.imba.io.utils;


import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Date;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;


public class OSSUtil {

	/**
	 * 
	 * <b>简述：服务器端生成GET的url签名字串</b>
	 * 
	 * @param key
	 * @param expiration
	 * @param ossClient
	 * @param bucket
	 * @return
	 */
	public static URL getUrl( String key, Date expiration, OSSClient ossClient, String bucket ) {
		GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest( bucket, key, HttpMethod.GET );
		request.setExpiration( expiration ); // 设置过期时间,一年时间为 new Date( new Date().getTime() + 1000 * 3600 * 24 * 365 );
		URL signedUrl = ossClient.generatePresignedUrl( request ); // 生成URL签名(HTTP GET请求)

		return signedUrl;

	}


	/**
	 * 
	 * <b>简述：服务器端生成PUT的url签名字串，允许用户临时进行其他操作（比如上传，删除Object）,通过传入 HttpMethod.PUT 参数，用户可以使用生成的URL上传Object</b>
	 * 
	 * @param key
	 * @param expiration
	 * @param ossClient
	 * @param bucket
	 * @return
	 */
	public static URL getPutUrl( String key, Date expiration, OSSClient ossClient, String bucket ) {
		// Date expiration = DateUtil.parseRfc822Date("Wed, 18 Mar 2015 14:20:00 GMT");
		URL url = ossClient.generatePresignedUrl( bucket, key, expiration, HttpMethod.PUT );

		return url;

	}


	/**
	 * 
	 * <b>简述：创建目录</b>
	 * 
	 * @param ossClient
	 * @param bucketName
	 * @param folderName
	 */
	public static void mkdir( OSSClient ossClient, String bucketName, String folderName ) {
		if ( !folderName.endsWith( "/" ) ) {
			folderName = folderName + "/";
		}

		ossClient.putObject( bucketName, folderName, new ByteArrayInputStream( new byte[ 0 ] ) );
		ossClient.shutdown();

	}

}
