package xin.imba.io.common.model;


import xin.imba.io.common.TuringJson;


/**
 * 
 * <b>简述：分片模型</b>
 * 
 * @author : xiepengcheng 2016年12月10日 下午3:17:14
 */
public class ShardModel {

	/**
	 * 并发线程数量
	 */
	private int taskNum = 1;

	/**
	 * 分片大小
	 */
	private long partSize = 102400L;


	public int getTaskNum() {
		return taskNum;
	}


	public void setTaskNum( int taskNum ) {
		this.taskNum = taskNum;
	}


	public long getPartSize() {
		return partSize;
	}


	public void setPartSize( long partSize ) {
		this.partSize = partSize;
	}


	@Override
	public String toString() {
		return TuringJson.toJSONString( this, false );
	}
}
