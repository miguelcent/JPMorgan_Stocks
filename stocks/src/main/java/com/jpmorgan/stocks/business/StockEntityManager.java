package com.jpmorgan.stocks.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.Trade;

/**
 * @author Miguel Centeno
 *
 */
public interface StockEntityManager {
	
	/**
	 * @return an HashMap where the key values are Stock Symbols and the values are a Stock
	 */
	public HashMap<String, Stock> getStocks();
	/**
	 * @param stSymbol
	 * @return a Stock object representing a stock from a given Stock Symbol
	 */
	public Stock getStock(String stSymbol);
	/**
	 * @return
	 */
	public ArrayList<Trade> getTrades();
	/**
	 * @param trade
	 * @return true if a given Trade is correctly recorded. Other case return false
	 */
	public boolean recordTrade(Trade trade); 
	
	/**
	 * @return int value represents the number of trades. 
	 */
	public int getTradesNum();
	
	/**
	 * Method that reset the stocks of entity manager
	 */
	public void cleanStocks();
	
	/**
	 * Method that reset the trades of entity manager
	 */
	public void cleanTrades();
	
}
