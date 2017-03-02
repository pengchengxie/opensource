package xin.imba.io.common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class TuringJson extends JSON {

	public static String toJSONString( Object object ) {
		return toJSONString( object, SerializerFeature.DisableCircularReferenceDetect );
	}

}
