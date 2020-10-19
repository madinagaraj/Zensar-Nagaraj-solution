package com.PortfolioAction.PortfolioAction.service;

import com.PortfolioAction.PortfolioAction.mapper.TimeSeriesDailyJson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class GetStockMarketPrice {
    //RestTemplate restTemplate;
    RestTemplate restTemplate = new RestTemplate();
    TimeSeriesDailyJson timeSeriesDailyJson;
    TimeSeriesDailyJson.TimeSeriesDailyJsonDaily timeSeriesDailyJsonDaily;
    TimeSeriesDailyJson.TimeSeriesDailyJsonMetaData metaData;



    public String get(String symbol) {

        String URI = "https://www.alphavantage.co/query?apikey=BPYXEC00NPEI4YSY&function=TIME_SERIES_DAILY_ADJUSTED&symbol="+symbol;

        timeSeriesDailyJson = restTemplate.getForObject(URI,TimeSeriesDailyJson.class);

       metaData= timeSeriesDailyJson.getMetaData();
       String lastrefreshdate=metaData.getLastRefreshed();

      timeSeriesDailyJsonDaily = timeSeriesDailyJson.getDaily().get(lastrefreshdate);
      String closingprice = timeSeriesDailyJsonDaily.getClosingPrice();
        return closingprice;
    }
}
