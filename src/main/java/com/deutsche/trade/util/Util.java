package com.deutsche.trade.util;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Util {

	private Util() {
	}

	public static LocalDate getLocalDate() {
		return LocalDate.now(Clock.systemUTC());
	}

	public static LocalDateTime getLocalDateTime() {
		return LocalDateTime.now(Clock.systemUTC());
	}

}
