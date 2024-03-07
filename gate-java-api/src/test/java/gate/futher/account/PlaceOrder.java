package gate.futher.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzt.vtrading.lib.exchange.contract.GateForeverUSDT;
import com.lzt.vtrading.lib.exchange.model.Contract;
import com.lzt.vtrading.lib.exchange.model.ContractOrderParam;

public class PlaceOrder {
	
	public static void main(String[] args) {
		GateForeverUSDT gate = new GateForeverUSDT();
		JSONObject paramJson = new JSONObject();
		paramJson.put("apiKey", "d0d76a56213e4440b6495210a33c8c2a");
		paramJson.put("secretKey", "36ff158cdcb1e65535e93b0bdf30845d6d4f8c3ee9dfe931962087b9350fad82");

		gate.init(paramJson);

		Contract contract = new Contract();
		ContractOrderParam param=new ContractOrderParam();
		
		param.setAmount("10");
		param.setPrice("1.08");
		// 1表示开仓，0表示平仓
		param.setOffset("1");
			// 1表示购买，0表示售卖
		param.setTradeType("1");
		param.setMatchType("0");
		
		contract.setContractId("EOS_USDT"); //421638197925
		
		System.out.println(JSON.toJSONString(gate.order(param, contract)));

	}

}
