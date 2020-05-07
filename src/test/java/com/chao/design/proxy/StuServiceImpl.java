package com.chao.design.proxy;

import java.util.Date;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;

public class StuServiceImpl implements StuService{

	@Override
	public void getStuInfo() {
		Dict set = Dict.create().set("id", RandomUtil.randomInt(5)).set("name", "lisi").set("birthday", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MINUTE_PATTERN));
		Console.log("学生信息：{}",set);
	}

}
