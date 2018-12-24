package com.buddha.icbi.api;

import com.buddha.component.common.util.RandomUtil;

public class RandomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			System.out.println(RandomUtil.getUUID2());
			System.out.println("-------------------------------------");
		}
	}

}
