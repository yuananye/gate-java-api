package gate.account;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lzt.vtrading.lib.exchange.spot.Gate;

public class PlaceOrder {
	
	public static void main(String[] args) {
		Gate gate=new Gate();
		JSONObject paramJson = new JSONObject();
		paramJson.put("apiKey", "d0d76a56213e4440b6495210a33c8c2a");
		paramJson.put("secretKey", "36ff158cdcb1e65535e93b0bdf30845d6d4f8c3ee9dfe931962087b9350fad82");
	
		gate.init(paramJson);
		
		Map<String,String> paramMap=new HashMap<String,String>();
		
		paramMap.put("counterCoin", "ETH");
		paramMap.put("baseCoin", "USDT");
		paramMap.put("price", "3088");
		paramMap.put("amount", "0.001"); //517196924545
		
		System.out.println(JSONObject.toJSONString(gate.buy(paramMap)));
		
	}

}
