package com.jpmorgan.stocks.service;

import com.jpmorgan.stocks.model.Trade;

/**
 * @author Miguel Centeno
 *
 */
public interface StockService {

	/**
	 * Calculate Dividend Yield
	 * 
	 * @param stSymbol a Stock Symbol
	 * @return A double value representing the dividend yield for a given stock
	 * @throws Exception
	 */
	public double calcDividendYield(String stSymbol) throws Exception;

	/**
	 * Calculate P/E Ratio
	 * 
	 * @param stSymbol a Stock Symbol
	 * @return A double value representing the P/E Ratio for a given stock
	 * @throws Exception
	 */
	public double calcPerRatio(String stSymbol) throws Exception;

	/**
	 * Calculate GBCE All Share Index using Geometric mean for all stocks.
	 * 
	 * @return a double value representing the result of calculate All Share Index in the Geometric mean for GBCE
	 * @throws Exception
	 */
	public double calcGBCEAllShareIndex() throws Exception;

	/**
	 * Calculate the price of a stock
	 * 
	 * @param stSymbol
	 * @return a double value representing the price of a given stock
	 * @throws Exception
	 */
	public double calcStockPrice(String stSymbol) throws Exception;

	
	/**
	 * @param trade a Trade object
	 * @return boolean: true if recording proccess is ok and false in other case
	 * @throws Exception
	 */
	public boolean recTrade(Trade trade) throws Exception;
}
