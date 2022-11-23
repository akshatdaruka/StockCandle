package com.investobull.stock.repository;

import org.springframework.data.repository.CrudRepository;

import com.investobull.stock.entity.Candle;

public interface CandleRespository extends CrudRepository<Candle, Long> {

}
