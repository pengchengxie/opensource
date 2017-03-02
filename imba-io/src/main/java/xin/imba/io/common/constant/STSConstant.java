package xin.imba.io.common.constant;


/**
 * 
 * <b>简述：OSS常量类</b>
 * 
 * @author : xiepengcheng 2016年11月16日 下午1:26:01
 */
public class STSConstant {

	/**
	 * 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
	 */
	public static final String REGION_CN_BEIJING = "cn-beijing";

	/**
	 * 当前 STS API 版本
	 */
	public static final String STS_API_VERSION = "2015-04-01";

	/**
	 * RoleSessionName 是临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
	 * 但是注意RoleSessionName的长度和规则，不要有空格，只能有'-' '_' 字母和数字等字符
	 * 具体规则请参考API文档中的格式要求
	 */
	public static final String ROLE_SESSION_NAME = "external-username";

}
