package com.investobull.stock.parser;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.investobull.stock.entity.Candle;

public class JsonParsing {

	public List<Candle> parseWholeJson() {
		JSONParser parser = new JSONParser();
		List<Candle> listOfCandles = new ArrayList<>();
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\Akshat Daruka\\Investobull\\jsontimeseries5min.txt"));
			String jsonInString = new Gson().toJson(obj);
			JSONObject mJSONObject = new JSONObject(jsonInString);
			JSONArray candles = (JSONArray) mJSONObject.get("candles");
			for (int n = 0; n < candles.length(); n++) {
				Candle candle = new Candle();
				JSONObject object = candles.getJSONObject(n);
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				Date date = formatter.parse(object.getString("LastTradeTime"));
				candle.setLastTradeTime(date);
				candle.setClose(Float.parseFloat(object.getString("Close")));
				candle.setHigh(Float.parseFloat(object.getString("High")));
				candle.setLow(Float.parseFloat(object.getString("Low")));
				candle.setOpen(Float.parseFloat(object.getString("Open")));
				candle.setOpenInterest(Integer.parseInt(object.getString("OpenInterest")));
				candle.setQuotationLot(Integer.parseInt(object.getString("QuotationLot")));
				candle.setTradedQty(Long.parseLong(object.getString("TradedQty")));
				listOfCandles.add(candle);
			}

		} catch (Exception e) {
			System.out.println("Some exception occured while parsing JSON");
			return null;
		}
		return listOfCandles;
	}

	public String getOrb(List<Candle> data, int min) {
		float lowestRange = 99999;
		float highestRange = -99999;
		for (int i = data.size() - 1; i >= data.size() - min / 5; i--) {
			float localLow = data.get(i).getLow();
			float localHigh = data.get(i).getHigh();
			if (localHigh > highestRange)
				highestRange = localHigh;
			if (localLow < lowestRange)
				lowestRange = localLow;
		}
		for (int i = data.size() - min / 5 - 1; i >= 0; i--) {
			float localLow = data.get(i).getLow();
			float localHigh = data.get(i).getHigh();
			if (localLow < lowestRange || localHigh > highestRange) {
				Date dateOfOrb = data.get(i).getLastTradeTime();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				String strDate = dateFormat.format(dateOfOrb);
				return strDate;
			}
		}
		return "No ORB !";
	}

	public JSONObject reSize(List<Candle> data, int interval) {
		JSONObject obj = new JSONObject();
		JSONArray newArray = new JSONArray();
		int noOfCandleTogether = interval / 5;
		int i = data.size() - 1;
		while (i >= 0) {
			int inter = 0;
			float newHigh = -99999;
			float newOpen = -999999;
			float newLow = 999999;
			Date newDate = null;
			float newClose = -99999;
			Long newTradeQnty = 0L;
			while (inter < noOfCandleTogether) {
				newTradeQnty+=data.get(i).getTradedQty();
				if (inter == 0)
					newOpen = data.get(i).getOpen();
				if (inter == noOfCandleTogether - 1) {
					newClose = data.get(i).getClose();
					newDate = data.get(i).getLastTradeTime();
				}
				if (data.get(i).getHigh() > newHigh)
					newHigh = data.get(i).getHigh();
				if (data.get(i).getLow() < newLow)
					newLow = data.get(i).getLow();
				i--;
				inter++;
			}
			JSONObject newJson = new JSONObject();
			Candle newCandle = new Candle();
			newCandle.setClose(newClose);
			newCandle.setHigh(newHigh);
			newCandle.setLastTradeTime(newDate);
			newCandle.setLow(newLow);
			newCandle.setOpen(newOpen);
			newCandle.setQuotationLot(1);
			newCandle.setOpenInterest(0);
			newCandle.setTradedQty(newTradeQnty);
			newJson.put("LastTradeTime", newCandle.getLastTradeTime());
			newJson.put("QuotationLot", 1);
			newJson.put("TradedQty", newCandle.getTradedQty());
			newJson.put("OpenInterest", newCandle.getOpenInterest());
			newJson.put("Open", newCandle.getOpen());
			newJson.put("High", newCandle.getHigh());
			newJson.put("Low", newCandle.getLow());
			newJson.put("Close", newCandle.getClose());
			newArray.put(newJson);
		}
		obj.put("candles", newArray);
		return obj;
	}
}
