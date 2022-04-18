package com.deutsche.trade.util;

import com.deutsche.trade.entity.Trade;
import com.deutsche.trade.exception.InvalidTradeException;

public class Validate {

	private Validate() {
	}

	public static boolean validateVersion(Trade trade, Trade oldTrade) {
		boolean result = false;
		if (trade.getVersion() >= oldTrade.getVersion()) {
			result = true;
		} else {
			throw new InvalidTradeException("Lower version no, latest version no is " + oldTrade.getVersion());
		}
		return result;
	}

	public static boolean validateMaturityDate(Trade trade) {
		return !trade.getMaturityDate().isBefore(Util.getLocalDate());
	}

}
