package com.deutsche.trade.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.deutsche.trade.entity.Trade;
import com.deutsche.trade.exception.InvalidTradeException;

@SpringBootTest
class ValidateTest {

	@Test
	void testValidateVersion() {
		Trade newTrade = Helper.getTrade();
		Trade oldTrade = Helper.getTrade();

		newTrade.setVersion(2L);
		oldTrade.setVersion(3L);
		Exception exception = assertThrows(InvalidTradeException.class,
				() -> Validate.validateVersion(newTrade, oldTrade));
		assertEquals("Invalid Trade Exception: Lower version no, latest version no is 3", exception.getMessage());
	}

	@Test
	void testValidateMaturityDate() {
		Trade trade = Helper.getTrade();
		trade.setMaturityDate(Util.getLocalDate().minusDays(1));
		assertEquals(false, Validate.validateMaturityDate(trade));
	}
}
