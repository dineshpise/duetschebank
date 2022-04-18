package com.deutsche.trade.util;

import com.deutsche.trade.entity.Trade;

public class Helper {

	public static Trade getTrade() {
		Trade trade = new Trade();
		trade.setBookId("bookId");
		trade.setId(1L);
		trade.setCounterParty("counterParty3");
		trade.setTradeId("T1");
		trade.setMaturityDate(Util.getLocalDate());
		trade.setExpiredFlag("N");
		trade.setVersion(1L);
		trade.setCreatedDate(Util.getLocalDate());
		return trade;
	}

}
