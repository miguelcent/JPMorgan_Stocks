package com.jpmorgan.stocks.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.stat.StatUtils;
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
	
	public StockEntityManager getStocksEntityManager() {
		return stocksEntityManager;
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
		double index = 0.0;
		
		HashMap<String, Stock> stocks = stocksEntityManager.getStocks();
		Set<String> stockSymbols = stocks.keySet();
		ArrayList<Double> prices = new ArrayList<Double>();
		for(String stSymbol : stockSymbols){
			double stPrice = calcStockPricesCollection(stSymbol);
			if(stPrice != 0){
				prices.add(stPrice);
			}
		}
		
		//In this point, found than "Geometric Mean" is a method (formula) of the Apache Common's library "Math3"
		double[] pricesA = new double[prices.size()];
		for(int i = 0; i<=(prices.size()-1); i++){
			pricesA[i] = prices.get(i).doubleValue();
		}
		
		index = StatUtils.geometricMean(pricesA);
		
		return index;
	}

	public double calcStockPrice(String stSymbol) throws Exception {
		double price = 0.0;
		
		try{
			logger.debug("Calculating stock price.....");
			Stock stock = stocksEntityManager.getStock(stSymbol);
			if(stock==null){
				throw new Exception("Stock does not exists in the system");
			}
			
			price = calcStockPricesCollection(stSymbol);
			
			logger.debug("Stock price calculated: "+price);
			
		}catch(Exception ex){
			logger.error("Error calculating stockPrice for the stock "+stSymbol, ex);
		}
		
		return price;
	}

	public boolean recTrade(Trade trade) throws Exception {
		boolean record = false;
		try{
			logger.debug("Recording trade: "+trade);
			if(trade == null){
				throw new Exception("Trade null not accepted.");
			}
			record = stocksEntityManager.recordTrade(trade);
			
			if(record){
				trade.getStock().setPrice(trade.getPrice());
			}
		}catch(Exception e){
			logger.error("Error recording trade", e);
		}
		return record;
	}
	
	private double calcStockPricesCollection(String stSymbol) throws Exception{
		double price = 0.0;
		double shareSum = 0.0;
		double priceSum = 0.0;
		Calendar now = Calendar.getInstance();		
		logger.debug("Calculating stock price # of trades: "+getNumberOfTrades());
		
		ArrayList<Trade> originalTrades = stocksEntityManager.getTrades();
		ArrayList<Trade> tradesFiltered = new ArrayList<Trade>();
		
		//Filter of trades list to take only last 15 minutes
		for(Trade ot : originalTrades){
			Calendar time = Calendar.getInstance();
			time.setTime(ot.getTimeStamp());
			
			long nowMilis = now.getTimeInMillis();
			long timeMilis = time.getTimeInMillis();
			
			if(((nowMilis - timeMilis)/1000/60)<=15){
				tradesFiltered.add(ot);
			}
		}
		logger.debug("# of trades filtered (last 15 minutes): "+tradesFiltered.size());
		
		for(Trade trade : tradesFiltered){
			shareSum = shareSum + trade.getSharesQuantity();
			priceSum = priceSum + (trade.getPrice() * trade.getSharesQuantity());
		}
		
		price = priceSum / shareSum;
		
		return price;
	}
	
	public int getNumberOfTrades() throws Exception {
		return stocksEntityManager.getTrades().size();
	}
	
}
