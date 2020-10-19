package com.PortfolioAction.PortfolioAction.mapper;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSeriesDailyJson {
    @JsonProperty("Meta Data")
    private TimeSeriesDailyJsonMetaData metaData;

    public TimeSeriesDailyJsonMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(TimeSeriesDailyJsonMetaData metaData) {
        this.metaData = metaData;
    }


    @JsonProperty("Time Series (Daily)")
    private Map<String, TimeSeriesDailyJsonDaily> daily;

    public Map<String, TimeSeriesDailyJsonDaily> getDaily() {
        return daily;
    }

    public void setDaily(Map<String, TimeSeriesDailyJsonDaily> daily) {
        this.daily = daily;
    }


    @Data
@JsonIgnoreProperties(ignoreUnknown = true)
public static class TimeSeriesDailyJsonDaily {
    public TimeSeriesDailyJsonDaily() {
    }
        @JsonProperty("1. open")
        private String openingPrice;
        @JsonProperty("2. high")
        private String highPrice;
        @JsonProperty("3. low")
        private String lowPrice;
        @JsonProperty("4. close")
        private String closingPrice;
        @JsonProperty("5. adjusted close")
        private String adjclose;
        @JsonProperty("6. volume")
        private String volume;
        @JsonProperty("7. dividend amount")
        private String dividendamount;
        @JsonProperty("8. split coefficient")
        private String splitcoefficient;


        public String getClosingPrice() {
            return closingPrice;
        }

        public void setClosingPrice(String closingPrice) {
            this.closingPrice = closingPrice;
        }
    }

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSeriesDailyJsonMetaData{

    @JsonProperty("1. Information")
    private String Information;
    @JsonProperty("2. Symbol")
    private String Symbol;
    @JsonProperty("3. Last Refreshed")
    private String LastRefreshed;
    @JsonProperty("4. Output Size")
    private String OutputSize;
    @JsonProperty("5. Time Zone")
    private String TimeZone;


        public String getInformation() {
            return Information;
        }

        public void setInformation(String information) {
            Information = information;
        }

        public String getLastRefreshed() {
            return LastRefreshed;
        }

        public void setLastRefreshed(String lastRefreshed) {
            LastRefreshed = lastRefreshed;
        }
    }

}