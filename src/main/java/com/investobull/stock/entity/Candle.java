package com.investobull.stock.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Candle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date lastTradeTime;

	private int quotationLot;

	private Long tradedQty;

	private int openInterest;

	private float open;

	private float high;

	private float low;

	private float close;

	public Candle() {
	}

	public Candle(Date lastTradeTime, int quotationLot, Long tradedQty, int openInterest, float open, float high,
			float low, float close) {
		this.lastTradeTime = lastTradeTime;
		this.quotationLot = quotationLot;
		this.tradedQty = tradedQty;
		this.openInterest = openInterest;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastTradeTime() {
		return lastTradeTime;
	}

	public void setLastTradeTime(Date lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}

	public int getQuotationLot() {
		return quotationLot;
	}

	public void setQuotationLot(int quotationLot) {
		this.quotationLot = quotationLot;
	}

	public Long getTradedQty() {
		return tradedQty;
	}

	public void setTradedQty(Long tradedQty) {
		this.tradedQty = tradedQty;
	}

	public int getOpenInterest() {
		return openInterest;
	}

	public void setOpenInterest(int openInterest) {
		this.openInterest = openInterest;
	}

	public float getOpen() {
		return open;
	}

	public void setOpen(float open) {
		this.open = open;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public float getClose() {
		return close;
	}

	public void setClose(float close) {
		this.close = close;
	}

	@Override
	public String toString() {
		return "Candle [id=" + id + ", lastTradeTime=" + lastTradeTime + ", quotationLot=" + quotationLot
				+ ", tradedQty=" + tradedQty + ", openInterest=" + openInterest + ", open=" + open + ", high=" + high
				+ ", low=" + low + ", close=" + close + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candle other = (Candle) obj;
		return Objects.equals(id, other.id);
	}

}
