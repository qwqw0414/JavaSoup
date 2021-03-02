package com.java.soup.service.mon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.java.soup.service.common.ServiceAbstract;
import com.java.soup.service.common.ServiceImpl;

public class MonService extends ServiceAbstract implements ServiceImpl {

	@Override
	public void excute(Document doc) {

		Elements elmt = doc.select("table.table tbody tr");

		for (Element e : elmt) {

//			name
			String name = e.select("td.name b").text();

			if (name == null || name.length() == 0)
				continue;

//			id
			String temp = e.select("td.icon a").attr("href");
			int code = Integer.valueOf(temp.substring(temp.indexOf("=") + 1));

//			alias
			String alias = e.select("td.name").text();
			alias = alias.substring(0, alias.indexOf(" "));

//			type
			String type = e.select("td.type").text();

//			parts
			String parts = e.select("td.parts").text();

//			weak
			String weak = e.select("td.weak").text();

//			elmt
			List<String> elm = e.select("td.elemental img").eachAttr("src");

//			mez
			List<String> mez = e.select("td.mez img").eachAttr("src");

			map = new HashMap<>();
			map.put("code", code);
			map.put("name", name);
			map.put("alias", alias);
			map.put("type", type);
			map.put("parts", parts);
			map.put("weak", weak);
			map.put("elm", parseStar(elm));
			map.put("mez", parseStar(mez));
			list.add(map);

		}
	}

	private List<String> parseStar(List<String> elm) {

		List<String> result = new ArrayList<>();
		String[] arr = { "14x14_x.png", "14x14_star.png", "27x14_star.png", "40x14_star.png" };

		for (String str : elm) {
			for (int i = 0; i < arr.length; i++) {
				if (str.contains(arr[i])) {
					result.add(String.valueOf(i));
				}
			}
		}

		return result;
	}
	

}
