package com.jpmorgan.stocks.business;

import java.util.ArrayList;
import java.util.HashMap;



import org.apache.log4j.Logger;

import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.Trade;

public class StockEntityManagerImpl implements StockEntityManager {
	
	Logger logger = Logger.getLogger(StockEntityManagerImpl.class);
	/**
	 * 
	 */
	private HashMap<String, Stock> stocks = null;
	/**
	 * 
	 */
	private ArrayList<Trade> trades = null;
	
	public StockEntityManagerImpl() {
		trades = new ArrayList<Trade>();
		stocks = new HashMap<String, Stock>();
	}
	
	public void setStocks(HashMap<String, Stock> stocks) {
		this.stocks = stocks;
	}

	public void setTrades(ArrayList<Trade> trades) {
		this.trades = trades;
	}

	public HashMap<String, Stock> getStocks() {
		return stocks;
	}

	public Stock getStock(String stSymbol) {
		Stock stock = null;
		try {
			if(stSymbol!=null){
				stock = stocks.get(stSymbol);
			}
		} catch(Exception exception) {
			logger.error("An error fired while retrieving stock. Stock Symbol :"+stSymbol, exception);
		}
			return stock;
	}

	public ArrayList<Trade> getTrades() {
		return trades;
	}

	public boolean recordTrade(Trade trade) {
		boolean ret = false;
		try{
			ret = trades.add(trade);
		}catch(Exception e){
			logger.error("An error fired recording trade.", e);
		}
		return ret;
	}
	
	public int getTradesNum() {
		return trades.size();
	}
	
	public void cleanStocks(){
		this.stocks = null;
	}
	
	public void cleanTrades(){
		this.trades = null;
	}

}
