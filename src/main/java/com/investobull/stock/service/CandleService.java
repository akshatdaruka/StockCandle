package com.investobull.stock.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investobull.stock.entity.Candle;
import com.investobull.stock.parser.JsonParsing;
import com.investobull.stock.repository.CandleRespository;

@Service
public class CandleService {

	private JsonParsing jsonParsing = new JsonParsing();

	@Autowired
	private CandleRespository candleRespository;

	public CandleService() {
	}

	public CandleService(JsonParsing jsonParsing, CandleRespository candleRespository) {
		this.jsonParsing = jsonParsing;
		this.candleRespository = candleRespository;
	}

	public void saveJSON() {
		List<Candle> candles = new ArrayList<>();
		candles = jsonParsing.parseWholeJson();
		candleRespository.saveAll(candles);
	}

	public List<Candle> getData() {
		List<Candle> candles = new ArrayList<>();
		candles = (List<Candle>) candleRespository.findAll();
		return candles;
	}

	public String getOrb(int min) {
		String date;
		date = jsonParsing.getOrb(getData(), min);
		return date;
	}

	public JSONObject reSize(int interval) {
		JSONObject obj = new JSONObject();
		obj = jsonParsing.reSize(getData(),interval);
		return obj;
	}

}
