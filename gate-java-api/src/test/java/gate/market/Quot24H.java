package gate.market;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzt.vtrading.lib.exchange.spot.Gate;

public class Quot24H {
	
	public static void main(String[] args)
	{
		JSONObject json=new JSONObject();
		
		Gate gate=new Gate();
		gate.init(json);
		
		System.out.println(JSON.toJSONString(gate.getQuot24H("BTC", "USDT")));
		
		
	}

}
