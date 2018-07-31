package com.michael.j2se.concurrent.buy;

public class User {

	Shop shop;

	Goods goods;

	public User(Shop shop) {
		this.shop = shop;
	}

	public void buy() {
		this.goods = shop.sellGoods();
	}

}
