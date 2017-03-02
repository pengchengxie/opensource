package xin.imba.io.common.protocol.request;


import xin.imba.io.common.TuringJson;


public class STSTokenRequest {

	String accessKeyId;

	String accessKeySecret;

	/**
	 * RoleArn 需要在 RAM 控制台上获取
	 */
	String roleArn;

	/**
	 * RoleSessionName 是临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
	 * 但是注意RoleSessionName的长度和规则，不要有空格，只能有'-' '_' 字母和数字等字符
	 * 具体规则请参考API文档中的格式要求
	 */
	String roleSessionName;

	/**
	 * 授权策略
	 */
	String policy;


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


	public String getRoleArn() {
		return roleArn;
	}


	public void setRoleArn( String roleArn ) {
		this.roleArn = roleArn;
	}


	public String getRoleSessionName() {
		return roleSessionName;
	}


	public void setRoleSessionName( String roleSessionName ) {
		this.roleSessionName = roleSessionName;
	}


	public String getPolicy() {
		return policy;
	}


	public void setPolicy( String policy ) {
		this.policy = policy;
	}


	@Override
	public String toString() {
		return TuringJson.toJSONString( this, false );
	}

}
