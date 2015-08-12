package com.jpmorgan.stocks.service;

import org.apache.log4j.Logger;

import com.jpmorgan.stocks.business.StockEntityManager;
import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.Trade;

public class StockServiceImpl implements StockService {
	/**
	 *
	 */
	private Logger logger = Logger.getLogger(StockServiceImpl.class);
	
	/**
	 * 
	 */
	private StockEntityManager stocksEntityManager = null;


	/**
	 * 
	 * @param stocksEntityManager
	 */
	public void setStocksEntityManager(StockEntityManager stocksEntityManager) {
		this.stocksEntityManager = stocksEntityManager;
	}
	
	public double calcDividendYield(String stSymbol) throws Exception {
		
		double dy = -1.0;
		try{
			logger.debug("Calculating Dividend Yield for Stock : "+stSymbol);
			Stock stock = stocksEntityManager.getStock(stSymbol);
			
			if(stock == null){
				throw new Exception("The stock "+stSymbol+" does not exists in the System.");
			}
			
			dy = stock.getDividendYield();
			
			logger.debug("Dividend Yield: "+dy);
		} catch (Exception e){
			logger.error("Error calculating Dividend Yield for stock "+stSymbol, e);
		}
		
		return dy;
		
	}

	public double calcPerRatio(String stSymbol) throws Exception {
		double peRatio = -1.0;
		
		try{
			logger.debug("Calculating P/E Ratio for stock "+stSymbol);
			Stock stock = stocksEntityManager.getStock(stSymbol);
			
			if(stock == null){
				throw new Exception("The stock "+stSymbol+" does not exists in the System");
			}
			
			peRatio = stock.getPerRatio();
			
			logger.debug("P/E Ratio: "+stSymbol);
			
		} catch (Exception e){
			logger.error("Error calculating P/E Ratio for stock "+stSymbol, e);
		}
		return peRatio;
	}

	public double calcGBCEAllShareIndex() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public double calcStockPrice(String stSymbol) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public void recTrade(Trade trade) throws Exception {
		try{
			logger.debug("Recording trade: "+trade);
		}catch(Exception e){
			logger.error("Error recording trade", e);
			if(trade == null){
				throw new Exception("Trade null not accepted.");
			}
		}

	}

}
