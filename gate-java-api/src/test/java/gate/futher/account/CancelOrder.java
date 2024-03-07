package gate.futher.account;

import com.alibaba.fastjson.JSONObject;
import com.lzt.vtrading.lib.exchange.contract.GateForeverUSDT;
import com.lzt.vtrading.lib.exchange.model.Contract;
import com.lzt.vtrading.lib.exchange.model.TradeOrder;

public class CancelOrder {
	
	
	public static void main(String[] args) {
		GateForeverUSDT gate = new GateForeverUSDT();
		JSONObject paramJson = new JSONObject();
		paramJson.put("apiKey", "d0d76a56213e4440b6495210a33c8c2a");
		paramJson.put("secretKey", "36ff158cdcb1e65535e93b0bdf30845d6d4f8c3ee9dfe931962087b9350fad82");

		gate.init(paramJson);

		Contract contract = new Contract(); //421638197925
		
		TradeOrder order=new TradeOrder();
		order.setId("421638197925");

		contract.setContractId("ORDI_USDT");
		
		gate.revokeOrder(order, contract);

	}

}
