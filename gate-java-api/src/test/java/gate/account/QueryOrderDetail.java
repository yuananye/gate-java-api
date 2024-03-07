package gate.account;

import com.alibaba.fastjson.JSONObject;
import com.lzt.vtrading.lib.exchange.model.TradeOrder;
import com.lzt.vtrading.lib.exchange.spot.Gate;

public class QueryOrderDetail {
	

	public static void main(String[] args) {
		Gate gate=new Gate();
		JSONObject paramJson = new JSONObject();
		paramJson.put("apiKey", "d0d76a56213e4440b6495210a33c8c2a");
		paramJson.put("secretKey", "36ff158cdcb1e65535e93b0bdf30845d6d4f8c3ee9dfe931962087b9350fad82");
	
		gate.init(paramJson);
		
		TradeOrder param=new TradeOrder();
		param.setCointerCoin("ETH");
		param.setBaseCoin("USDT");
		param.setId("517229905939");
		
		System.out.println(JSONObject.toJSONString(gate.queryOrder(param)));
		
	}

}
