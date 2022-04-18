package com.deutsche.trade.service;

import java.util.List;

import com.deutsche.trade.entity.Trade;

public interface TradeService {

	boolean isValid(Trade trade);

	void updateExpiryFlagOfTrade();

	Trade save(Trade trade);

	List<Trade> findbyTradeId(String tradeId);
}
