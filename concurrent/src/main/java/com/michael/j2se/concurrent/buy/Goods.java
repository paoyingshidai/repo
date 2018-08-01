package com.michael.j2se.concurrent.buy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

	private Integer goodsId;

	private String goodsName;

	private Integer price;

}
