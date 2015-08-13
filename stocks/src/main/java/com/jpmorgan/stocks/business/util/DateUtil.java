package com.jpmorgan.stocks.business.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date newDateMins(int mins){
		Calendar now = Calendar.getInstance();
		long milis = now.getTimeInMillis();
		long minutes = milis/1000/60;
		minutes = minutes - (int)Math.floor(Math.random()*mins);
		milis = minutes*1000*60;
		Date newDate = new Date(milis);
		return newDate;
	}
}
