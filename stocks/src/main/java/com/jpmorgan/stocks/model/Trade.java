package com.jpmorgan.stocks.model;

import java.util.Date;

/**
 * 
 * @author Miguel Centeno
 *
 */
public class Trade {
	/**
	 * 
	 */
	private Date timeStamp = null;
	
	/**
	 * 
	 */
	private Stock stock = null;
	
	/**
	 * 
	 */
	private TradeType tradeIndicator = TradeType.BUY;
	
	/**
	 * 
	 */
	private int sharesQuantity = 0;
	
	/**
	 * 
	 */
	private double price = 0.0;
	
	
	/**
	 * 
	 */
	public Trade(){
	}
	
	public Trade(Date timeStamp, Stock stock, TradeType tradeType, int sharesQ, double price){
		this.timeStamp = timeStamp;
		this.stock = stock;
		this.tradeIndicator = tradeType;
		this.sharesQuantity = sharesQ;
		this.price = price;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * 
	 * @param timeStamp
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSharesQuantity() {
		return sharesQuantity;
	}
	
	/**
	 * 
	 * @param sharesQuantity
	 */
	public void setSharesQuantity(int sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}
	
	/**
	 * 
	 * @return
	 */
	public TradeType getTradeIndicator() {
		return tradeIndicator;
	}
	
	/**
	 * 
	 * @param tradeIndicator
	 */
	public void setTradeIndicator(TradeType tradeIndicator) {
		this.tradeIndicator = tradeIndicator;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * 
	 * @return
	 */
	public Stock getStock() {
		return stock;
	}
	
	/**
	 * 
	 * @param stock
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		String pattern = "Trade Object [time stamp: %tF %tT, stock: %s, indicator: %s, shares quantity: %7d, price: %8.2f]";
		return String.format(pattern, timeStamp,timeStamp, stock, tradeIndicator, sharesQuantity, price);
	}
	
	
	
}
