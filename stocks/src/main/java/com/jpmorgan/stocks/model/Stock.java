package com.jpmorgan.stocks.model;

/**
 * @author Miguel Centeno
 *
 */
public class Stock {

	/**
	 * 
	 */
	private String stSymbol = null;
	
	/**
	 * 
	 */
	private StockType stType = StockType.COMMON;
	
	/**
	 * 
	 */
	private double lastDividend = 0.0;
	
	/**
	 * 
	 */
	private double fixedDividend = 0.0;
	
	/**
	 * 
	 */
	private double parValue = 0.0;
	
	/**
	 * 
	 */
	private double price = 0.0;
	
	/**
	 * 
	 */
	public Stock(){
		
	}

	/**
	 * @return
	 */
	public String getStSymbol() {
		return stSymbol;
	}

	/**
	 * @param stSymbol
	 */
	public void setStSymbol(String stSymbol) {
		this.stSymbol = stSymbol;
	}

	/**
	 * @return
	 */
	public StockType getStType() {
		return stType;
	}

	/**
	 * @param stType
	 */
	public void setStType(StockType stType) {
		this.stType = stType;
	}

	/**
	 * @return
	 */
	public double getLastDividend() {
		return lastDividend;
	}

	/**
	 * @param lastDividend
	 */
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * @return
	 */
	public double getFixedDividend() {
		return fixedDividend;
	}

	/**
	 * @param fixedDividend
	 */
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	/**
	 * @return
	 */
	public double getParValue() {
		return parValue;
	}

	/**
	 * @param parValue
	 */
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	/**
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * @return
	 */
	public double getDividendYield() {
		double dy = 0.0;
		if(price > 0.0){
			if( stType == StockType.COMMON){
				dy = lastDividend/price;
			}else{
				dy = (fixedDividend*parValue)/price;
			}
		}
		
		return dy;
	}
	
	/**
	 * @return
	 */
	public double getPerRatio(){
		double pr = 0.0;
		
		if(price>0.0){
			pr = price/getDividendYield();
		}
		
		return pr;
	}
	
	public String toString(){
		
		String pattern = "Stock: [stock symbol: %s, stock type: %s, last dividend: %6.0f, fixed dividend: %6.2f, par value: %6.2f]";
		return String.format(pattern, stSymbol, stType, lastDividend, fixedDividend, parValue);
		
	}
	
}
