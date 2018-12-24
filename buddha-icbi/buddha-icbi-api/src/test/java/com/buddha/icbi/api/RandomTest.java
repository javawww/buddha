package com.buddha.icbi.api;

import com.buddha.component.common.util.RandomUtil;
import com.buddha.component.common.util.StringUtils;

public class RandomTest {

	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			System.out.println(RandomUtil.generateCode());
			System.out.println("--------------------------");
		}
	}

}
