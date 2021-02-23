package com.java.soup.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WpService extends ServiceAbstract implements ServiceImpl {

	@Override
	public void excute(Document doc) throws RuntimeException {

		Elements elmt = doc.select("table.table tbody tr");

		for (Element e : elmt) {

//			name
			String name = e.select("td.name b").text();

			if (name == null || name.length() == 0)
				continue;

//			code
			String temp = e.select(".name a").attr("href");
			int code = Integer.valueOf(temp.substring(temp.indexOf("=") + 1));

//			R
			int rare = Integer.parseInt(e.select(".name span").attr("class").replace("rare", ""));

//			A
			int a = Integer.parseInt(e.select(".attack").text());

//			C
			float c = (float) (0.01 * Integer.parseInt(e.select(".critical").text().replace("%", "")));

//			Slot 
			String slot = e.select(".slot span").text();

//			Shap
			List<String> shap = e.select(".sharpness span").eachAttr("style");

			map = new HashMap<>();
//			map.put("name", name);
			map.put("code", code);
//			map.put("rare", rare);
//			map.put("a", a);
//			map.put("c", c);
//			map.put("slot", parseSlot(slot));

			if (shap.size() == 16) {
				map.put("shap", parseShap(shap));
			}

			list.add(map);
			
		}
	}

	private List<Integer> parseShap(List<String> data) throws RuntimeException {

		List<Integer> result = new ArrayList<>();

		for (String i : data) {
			int num = 0;
			try {
				num = Integer.parseInt(i.replace("width:", "").replace("px", ""));
			} catch (NumberFormatException e) {
			}
			result.add(num);
		}
		return result;
	}

	private List<Integer> parseSlot(String data) throws RuntimeException {

		List<Integer> result = new ArrayList<>();

		for (String i : data.split("")) {
			result.add(Integer.parseInt(i));
		}

		return result;
	}
}
