package com.deutsche.trade.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.deutsche.trade.dao.TradeRepository;
import com.deutsche.trade.entity.Trade;
import com.deutsche.trade.exception.InvalidTradeException;
import com.deutsche.trade.util.Helper;
import com.deutsche.trade.util.Util;

@SpringBootTest
class TradeServiceImplTest {

	@Mock
	private TradeRepository tradeRepository;

	@Autowired
	private TradeService tradeService;

	@DisplayName("Test Save Trade")
	@Test
	void testSave() {
		Trade trade = Helper.getTrade();
		when(tradeRepository.save(trade)).thenReturn(trade);
		assertEquals(trade, tradeService.save(trade));

	}

	@DisplayName("Maturity date Invalid Trade Exception for past date")
	@Test
	void testValidMaturity() {
		Trade trade = Helper.getTrade();
		trade.setMaturityDate(Util.getLocalDate().minusDays(1));
		Exception exception = assertThrows(InvalidTradeException.class, () -> tradeService.isValid(trade));
		assertEquals("Invalid Trade Exception: Maturity date cannot be less than todays date", exception.getMessage());
	}

	@DisplayName("Test get trade with trade id")
	@Test
	void testfindbyTradeId() {
		Trade trade = Helper.getTrade();
		List<Trade> list = Arrays.asList(trade);
		Optional<List<Trade>> optionalTrade = Optional.of(list);
		when(tradeRepository.findByTradeId("T1")).thenReturn(optionalTrade);
		assertNotNull(tradeService.findbyTradeId("T1"));
		assertTrue(tradeService.findbyTradeId("T2").isEmpty());

	}

}
