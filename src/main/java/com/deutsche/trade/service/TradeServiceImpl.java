package com.deutsche.trade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutsche.trade.dao.TradeRepository;
import com.deutsche.trade.entity.Trade;
import com.deutsche.trade.exception.InvalidTradeException;
import com.deutsche.trade.util.Util;
import com.deutsche.trade.util.Validate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TradeServiceImpl implements TradeService {

	@Autowired
	TradeRepository tradeRepository;

	@Override
	public boolean isValid(Trade trade) {
		boolean result = false;
		if (Validate.validateMaturityDate(trade)) {
			Optional<Trade> exsitingTrade = tradeRepository.findTopByTradeIdOrderByVersionDesc(trade.getTradeId());
			if (exsitingTrade.isPresent()) {
				result = Validate.validateVersion(trade, exsitingTrade.get());
			} else {
				result = true;
			}
		} else {
			throw new InvalidTradeException("Maturity date cannot be less than todays date");
		}
		return result;
	}

	@Override
	public void updateExpiryFlagOfTrade() {
		tradeRepository.findByMaturityDateLessThan(Util.getLocalDate()).ifPresent(list -> list.forEach(t -> {
			if (!Validate.validateMaturityDate(t)) {
				t.setExpiredFlag("Y");
				log.info("Trade which needs to updated {}", t.getTradeId());
				tradeRepository.save(t);
			}
		}));
	}

	@Override
	public Trade save(Trade trade) {
		Trade tradeLocal = new Trade();
		if (null != trade) {
			trade.setExpiredFlag("N");
			trade.setCreatedDate(Util.getLocalDate());
			Optional<Trade> optionalTrade = tradeRepository.findByTradeIdAndVersion(trade.getTradeId(),
					trade.getVersion());
			if (optionalTrade.isPresent()) {
				tradeLocal = optionalTrade.get();
				trade.setId(tradeLocal.getId());
				tradeLocal = tradeRepository.save(trade);
			} else {
				tradeLocal = tradeRepository.save(trade);
			}
		}
		return tradeLocal;

	}

	@Override
	public List<Trade> findbyTradeId(String tradeId) {
		Optional<List<Trade>> optionalTrade = tradeRepository.findByTradeId(tradeId);
		if (optionalTrade.isPresent()) {
			return optionalTrade.get();
		} else {
			return new ArrayList<>();
		}
	}
}
