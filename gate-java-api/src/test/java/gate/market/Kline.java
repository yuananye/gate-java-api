package gate.market;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzt.vtrading.lib.exchange.model.param.KlineParam;
import com.lzt.vtrading.lib.exchange.spot.Gate;

public class Kline {
	
	public static void main(String[] args)
	{
		JSONObject json=new JSONObject();
		
		Gate gate=new Gate();
		gate.init(json);
		
		KlineParam param=new KlineParam();
		param.setCounterCoin("BTC");
		param.setBaseCoin("USDT");
		param.setType("1min");
		
		System.out.println(JSON.toJSONString(gate.queryKlines(param)));
		
		
	}

}
