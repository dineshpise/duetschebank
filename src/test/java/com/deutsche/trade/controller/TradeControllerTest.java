package com.deutsche.trade.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.deutsche.trade.entity.Trade;
import com.deutsche.trade.service.TradeService;
import com.deutsche.trade.util.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TradeController.class)
class TradeControllerTest {

	@MockBean
	TradeService tradeService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void testSave() throws Exception {
		Trade trade = Helper.getTrade();
		when(tradeService.save(trade)).thenReturn(trade);

		mockMvc.perform(
				post("/trade").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(trade)))
				.andExpect(status().isOk());
	}

	@Test
	void testRetriveTrade() throws Exception {
		Trade trade = Helper.getTrade();
		List<Trade> list = Arrays.asList(trade);
		when(tradeService.findbyTradeId("T2")).thenReturn(list);
		mockMvc.perform(get("/trade").param("tradeId", "T2")).andExpect(status().isOk());
	}

}
