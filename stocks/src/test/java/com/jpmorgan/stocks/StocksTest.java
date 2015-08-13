package com.jpmorgan.stocks;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.jpmorgan.stocks.business.StockEntityManagerImpl;
import com.jpmorgan.stocks.business.util.DateUtil;
import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.StockType;
import com.jpmorgan.stocks.model.Trade;
import com.jpmorgan.stocks.model.TradeType;
import com.jpmorgan.stocks.service.StockServiceImpl;

public class StocksTest {
	
	Logger logger = Logger.getLogger(StocksTest.class);
	
	StockEntityManagerImpl entityManager = new StockEntityManagerImpl();
	
	private Stock stock1;
	private Stock stock2;
	private Stock stock3;
	private Stock stock4;
	private Stock stock5;
	
	private Trade trade1;
	private Trade trade2;
	private Trade trade3;
	private Trade trade4;
	private Trade trade5;
	
	private ArrayList<Trade> tradeList = new ArrayList<Trade>();
	
	StockEntityManagerImpl stockEntityManager;
	StockServiceImpl service;
	
	public void init(){
		
		BasicConfigurator.configure();
		
		stockEntityManager = new StockEntityManagerImpl();
		service = new StockServiceImpl();
		
		stock1 = new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.00, 105.00);
		stock2 = new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.00, 98.00);
		stock3 = new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.00, 68.00);
		stock4 = new Stock("GIN", StockType.PREFERRED, 8.0, 0.02, 100.00, 99.00);
		stock5 = new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.00, 253.00);
		
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		
		stocks.add(stock1);
		stocks.add(stock2);
		stocks.add(stock3);
		stocks.add(stock4);
		stocks.add(stock5);
		
		HashMap<String, Stock> stocksMap = new HashMap<String, Stock>();
		
		for(Stock s : stocks){
			stocksMap.put(s.getStSymbol(), s);
		}
		
		stockEntityManager.setStocks(stocksMap);
		
		trade1 = new Trade(DateUtil.newDateMins(20), stock1, TradeType.BUY, 2, 102.00);
		trade2 = new Trade(DateUtil.newDateMins(20), stock2, TradeType.BUY, 4, 99.00);
		trade3 = new Trade(DateUtil.newDateMins(20), stock3, TradeType.SELL, 5, 67.00);
		trade4 = new Trade(DateUtil.newDateMins(20), stock4, TradeType.SELL, 2, 101.00);
		trade5 = new Trade(DateUtil.newDateMins(20), stock5, TradeType.BUY, 8, 261.00);
	
		tradeList.add(trade1);
		tradeList.add(trade2);
		tradeList.add(trade3);
		tradeList.add(trade4);
		tradeList.add(trade5);
		
		service.setStocksEntityManager(stockEntityManager);
		
	}
	
	@Test
	public void tradeRecord(){
		logger.info("Start  TradeTest ...");
		init();

		try{
			// Initial trades are empty, means, trades number equls to cero (0)
			int tradesNumber = service.getStocksEntityManager().getTrades().size();
			logger.info("Trades number: "+tradesNumber);
			Assert.assertEquals(tradesNumber, 0);

			// Insert many trades in the stock system
			for(Trade trade: tradeList){
				boolean result = service.recTrade(trade);
				Assert.assertTrue(result);
			}

			// After record trades, the number of trades should be equal to the trades list
			tradesNumber = service.getStocksEntityManager().getTrades().size();
			logger.info("Trades number: "+tradesNumber);
			Assert.assertEquals(tradesNumber, tradeList.size());


		}catch(Exception exception){
			logger.error(exception);
			Assert.assertTrue(false);
		}

		logger.info("Finish TradeTest ...OK");
		
		entityManager.cleanStocks();
		entityManager.cleanTrades();
	}
	
	@Test
	public void calculateDividendYieldTest(){
		init();
		tradeRecord();
		logger.info("Start  calculateDividendYieldTest ...");

		try{
			
			int tradesNumber = service.getStocksEntityManager().getTrades().size();
			logger.info("Trades number: "+tradesNumber);

			
			String[] stockSymbols = {"TEA", "POP", "ALE", "GIN", "JOE"};
			
			for(String stockSymbol: stockSymbols){
				double dividendYield = service.calcDividendYield(stockSymbol);
				logger.info(stockSymbol+" - DividendYield calculated: "+dividendYield);
				Assert.assertTrue(dividendYield >= 0.0);
			}

		}catch(Exception exception){
			logger.error(exception);
			Assert.assertTrue(false);
		}

		logger.info("Finish calculateDividendYieldTest ...OK");
		
		entityManager.cleanStocks();
		entityManager.cleanTrades();
	}
	
	@Test
	public void calculatePERatioTest(){
		init();
		tradeRecord();
		logger.info("Start  calculatePERatioTest ...");

		try{
			// Create the stock service and verify it's not null object
			

			int tradesNumber = service.getStocksEntityManager().getTrades().size();
			logger.info("Trades number: "+tradesNumber);

			
			String[] stockSymbols = {"TEA", "POP", "ALE", "GIN", "JOE"};
			for(String stockSymbol: stockSymbols){
				double peRatio = service.calcPerRatio(stockSymbol);
				logger.info(stockSymbol+" - P/E Ratio calculated: "+peRatio);
				Assert.assertTrue(peRatio >= 0.0);
			}
		}catch(Exception exception){
			logger.error(exception);
			Assert.assertTrue(false);
		}

		logger.info("Finish calculatePERatioTest ...OK");
		entityManager.cleanStocks();
		entityManager.cleanTrades();
	}
	
	/**
	 * 
	 */
	@Test
	public void calculateStockPriceTest(){
		try{
			init();
			tradeRecord();

			int tradesNumber = service.getNumberOfTrades();
			logger.info("Trades number: "+tradesNumber);
			
			// Calculates the Stock Price for all stocks
			String[] stockSymbols = {"TEA", "POP", "ALE", "GIN", "JOE"};
			//String[] stockSymbols = {"TEA"};
			for(String stockSymbol: stockSymbols){
				double stockPrice = service.calcStockPrice(stockSymbol);
				logger.info(stockSymbol+" - Stock Price calculated: "+stockPrice);
				Assert.assertTrue(stockPrice >= 0.0);
			}

			
		}catch(Exception exception){
			logger.error(exception);
			Assert.assertTrue(false);
		}
		entityManager.cleanStocks();
		entityManager.cleanTrades();

	}
	
	@Test
	public void calculateGBCEAllShareIndexTest(){
		try{
			init();
			tradeRecord();
			int tradesNumber = service.getStocksEntityManager().getTrades().size();
			logger.info("Trades number: "+tradesNumber);
			
			double GBCEAllShareIndex = service.calcGBCEAllShareIndex();
			logger.info("GBCE All Share Index: "+GBCEAllShareIndex);
			Assert.assertTrue(GBCEAllShareIndex > 0.0);
			
		}catch(Exception exception){
			logger.error(exception);
			Assert.assertTrue(false);
		}

	}
	
}
