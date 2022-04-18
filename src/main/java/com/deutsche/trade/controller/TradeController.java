package com.deutsche.trade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deutsche.trade.entity.Trade;
import com.deutsche.trade.service.TradeService;

@RestController
public class TradeController {

	@Autowired
	TradeService tradeService;

	@PostMapping("/trade")
	public ResponseEntity<String> trade(@RequestBody Trade trade) {
		if (tradeService.isValid(trade)) {
			tradeService.save(trade);
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/trade")
	public List<Trade> retriveTrade(@RequestParam String tradeId) {
		return tradeService.findbyTradeId(tradeId);
	}

}
