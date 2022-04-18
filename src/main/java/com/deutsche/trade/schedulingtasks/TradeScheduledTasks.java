package com.deutsche.trade.schedulingtasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.deutsche.trade.service.TradeService;
import com.deutsche.trade.util.Util;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TradeScheduledTasks {

	@Autowired
	TradeService tradeService;

	@Scheduled(cron = "${trade.expiry.schedule}", zone = "UTC") // 1st of every month
	public void reportCurrentTime() {
		log.info("Running flag expiry task at UTC time {}", Util.getLocalDate());
		tradeService.updateExpiryFlagOfTrade();
	}
}