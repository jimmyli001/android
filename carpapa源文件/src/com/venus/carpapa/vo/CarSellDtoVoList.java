package com.venus.carpapa.vo;

import java.util.ArrayList;

public class CarSellDtoVoList {
	/**
	 * 待检车的车辆数
	 */
	private int sum;
	/**
	 * 待检车的车辆列表
	 */
	private ArrayList<CarSellDtoVo> CarSellDtoVo_List;

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public ArrayList<CarSellDtoVo> getCarSellDtoVo_List() {
		return CarSellDtoVo_List;
	}

	public void setCarSellDtoVo_List(ArrayList<CarSellDtoVo> carSellDtoVo_List) {
		CarSellDtoVo_List = carSellDtoVo_List;
	}

}
