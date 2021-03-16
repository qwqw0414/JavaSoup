package com.java.soup.service.mhw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.java.soup.service.ServiceAbstract;
import com.java.soup.service.ServiceImpl;

public class WpService extends ServiceAbstract implements ServiceImpl {

	@Override
	public void excute(Document doc) throws RuntimeException {

		String type = doc.select("td.type label.active a").text();
		Elements elmt = doc.select("table.table tbody tr");

		for (Element e : elmt) {

//			name
			String name = e.select("td.name b").text();

			if (name == null || name.length() == 0)
				continue;

//			final
			int fin = Integer.parseInt(e.attr("data-final"));
			
//			code
			int code = getCode(e);

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

//			option
			String option = e.select(".option").html();
			
			map = new HashMap<>();
			map.put("name", name);
			map.put("type", type);
			map.put("code", code);
			map.put("rare", rare);
			map.put("attack", a);
			map.put("critical", c);
			map.put("fin", fin);
			
			int i = 0;
			
			for(int ii : parseSlot(slot)) {
				map.put("slot_" + ++i, ii);
			}
			
//			map.put("opt", option);

			list.add(map);
			
		}
	}

	public int getCode(Element e) {
//		code
		String temp = e.select(".name a").attr("href");
		return Integer.valueOf(temp.substring(temp.indexOf("=") + 1));
	}

	private List<Integer> parseSlot(String data) throws RuntimeException {

		List<Integer> result = new ArrayList<>();

		for (String i : data.split("")) {
			result.add(Integer.parseInt(i));
		}

		return result;
	}
}
