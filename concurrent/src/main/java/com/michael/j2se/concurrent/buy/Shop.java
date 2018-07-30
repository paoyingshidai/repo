package com.michael.j2se.concurrent.buy;

public class Shop {

	/**
	 * 用于表示商品
	 */
	public Integer goodsNumber;
	
	/** 商品的数量 */
	public Shop(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	
	public void addGoods() {
		this.goodsNumber ++;
	}
	
	public Integer buyGoods() {
		return this.goodsNumber == 0 ? null : this.goodsNumber--;
	}
	
}
