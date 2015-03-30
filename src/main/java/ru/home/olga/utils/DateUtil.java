package ru.home.olga.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Благодатских С.
 */
public class DateUtil {

	public static Date fromLocalDate(LocalDate date) {
		if (date == null) {
			return null;
		}
		return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

}
