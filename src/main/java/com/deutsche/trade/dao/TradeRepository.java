package com.deutsche.trade.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deutsche.trade.entity.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {

	Optional<Trade> findTopByTradeIdOrderByVersionDesc(String tradeId);

	Optional<List<Trade>> findByMaturityDateLessThan(LocalDate now);

	Optional<Trade> findByTradeIdAndVersion(String traceId, Long version);

	Optional<List<Trade>> findByTradeId(String traceId);
}
