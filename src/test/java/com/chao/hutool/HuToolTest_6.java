package com.chao.hutool;

import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.StrSpliter;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

public class HuToolTest_6 {

	public static void main(String[] args) {
		test6();
	}

	public static void test1() {
//		ExcelReader reader = ExcelUtil.getReader(FileUtil.file("C:\\Users\\Administrator\\Desktop\\时长班导出表格.xls"));
		ExcelReader reader = ExcelUtil.getReader(ResourceUtil.getStream("config/writeTest.xlsx"));
		List<List<Object>> readList = reader.read();
		for(int i=0; i<readList.size(); i++) {
			if(i == 0) {
				continue;
			}
			List<Object> rowList = readList.get(i);
			for(int j=0; j<rowList.size(); j++) {
				// 每一列处理
				System.out.print(rowList.get(j) + "\t\t");
			}
			System.out.println();
		}
	}

	public static void test2() {
		ExcelReader reader = ExcelUtil.getReader(ResourceUtil.getStream("config/writeTest.xlsx"));
		List<Map<String, Object>> readList = reader.readAll();
		for(int i=0; i<readList.size(); i++) {
			Map<String, Object> row = readList.get(i);
			String one = MapUtil.getStr(row, "学员名称");
			System.out.print(one + "\t\t");
			String two = MapUtil.getStr(row, "总课时");
			System.out.print(two + "\t\t");
			System.out.println(readList);
		}
		
	}

	public static void test3() {
		ExcelReader reader = ExcelUtil.getReader(ResourceUtil.getStream("config/时长班导出表格.xls"));
		List<Map<String, Object>> readList = reader.readAll();
		for(int i=0; i<readList.size(); i++) {
			Map<String, Object> row = readList.get(i);
			String one = MapUtil.getStr(row, "学员名称");
			System.out.print(one + "\t\t");
			String two = MapUtil.getStr(row, "总课时");
			System.out.print(two + "\t\t");
			System.out.println();
		}
		
		reader.close();
	}

	public static void test4() {
		ExcelUtil.readBySax(ResourceUtil.getStream("config/时长班导出表格.xls"), 0, (int sheetIndex, int rowIndex, List<Object> rowlist) -> {
			Console.log("[{}] [{}] {}", sheetIndex, rowIndex, rowlist);
		});
	}
	
	public static void test5() {
		List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
		List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
		List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
		List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
		List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");

		List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);
		
		//通过工具类创建writer
		ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest.xlsx");
		//跳过当前行，既第一行，非必须，在此演示用
		//writer.passCurrentRow();
		
		//合并单元格后的标题行，使用默认标题样式
		writer.merge(row1.size() - 1, "测试标题");
		//一次性写出内容，强制输出标题
		writer.write(rows, true);
		//关闭writer，释放内存
		writer.close();
		
	}
	
	public static void test6() {
		String str1 = "a, ,efedsfs,   ddf";
		//参数：被切分字符串，分隔符逗号，0表示无限制分片数，去除两边空格，忽略空白项
		List<String> split = StrSpliter.split(str1, ',', 0, true, true);
		split.forEach(str -> Console.log(str));
	}
}
