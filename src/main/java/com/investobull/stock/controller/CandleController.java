package com.investobull.stock.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investobull.stock.entity.Candle;
import com.investobull.stock.service.CandleService;

@RestController
@RequestMapping("/stocks")
public class CandleController {

	private final CandleService candleService;

	public CandleController(CandleService candleService) {
		this.candleService = candleService;
	}

	@RequestMapping("/saveJSON")
	private String saveJSON() {
		try {
			candleService.saveJSON();
		} catch (Exception e) {
			System.out.println("Some Exception in Contoller");
			return "failure";
		}
		return "success";
	}

	@RequestMapping("/getData")
	private List<Candle> getData() {
		List<Candle> candles = new ArrayList<>();
		try {
			candles = candleService.getData();
		} catch (Exception e) {
			System.out.println("Some Exception in getData in Controller");
			return null;
		}
		return candles;
	}

	@RequestMapping("/getOrb/{min}")
	private String getOrb(@PathVariable("min") int min) {
		String date;
		try {
			date = candleService.getOrb(min);
		} catch (Exception e) {
			System.out.println("Exception in getOrb in Controller");
			return "failure";
		}
		return ("ORB Candle generated at " + date);
	}

	@RequestMapping("/reSize/{interval}")
	private String reSize(@PathVariable("interval") int interval) {
		JSONObject obj = new JSONObject();
		try {
			obj = candleService.reSize(interval);
		} catch (Exception e) {
			System.out.println("Exception in resize in controller");
			return null;
		}
		return obj.toString();
	}
}
