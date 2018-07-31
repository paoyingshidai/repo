package com.michael.j2se.concurrent.buy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop {

	/**
	 * 用于表示商品
	 */
	public Integer goodsNumber;

	public List<Goods> goods = Collections.synchronizedList(new ArrayList<>());

	/** 商品的数量 */
	public Shop(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
		for (int i = 1; i < goodsNumber; i++) {
			goods.add(new Goods(i, "goodsName" + i, i));
		}
	}

	public Goods sellGoods() {
		if (goods.size() == 0) {
			return new Goods();
		} else {
			Goods good;
			synchronized (this) {		// 不要将 return 也加入同步操作。
				good = goods.get(0);
				goods.remove(0);
			}
			return good;
		}
	}

}
