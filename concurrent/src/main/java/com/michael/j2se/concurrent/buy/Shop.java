package com.michael.j2se.concurrent.buy;

import java.util.concurrent.atomic.AtomicInteger;

public class Shop {

//	/**
//	 * 用于表示商品
//	 */
//	public Integer goodsNumber;
//	
//	/** 商品的数量 */
//	public Shop(Integer goodsNumber) {
//		this.goodsNumber = goodsNumber;
//	}
//	
//	public void addGoods() {
//		this.goodsNumber ++;
//	}
//	
//	public Integer buyGoods() {
//		return this.goodsNumber == 0 ? null : this.goodsNumber--;
//	}
	
	/**
	 * 用于表示商品
	 */
	public volatile AtomicInteger goodsNumber;
	
	/** 商品的数量 */
	public Shop(AtomicInteger goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	
	public void addGoods() {
		this.goodsNumber.incrementAndGet();
	}
	
	public  Integer buyGoods() {
		return this.goodsNumber.intValue() == 0 ? null : this.goodsNumber.decrementAndGet();
	}
	
}
