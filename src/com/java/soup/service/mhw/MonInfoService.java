package com.java.soup.service.mhw;

import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.java.soup.service.ServiceAbstract;
import com.java.soup.service.ServiceImpl;

public class MonInfoService extends ServiceAbstract implements ServiceImpl {

	@Override
	public void excute(Document doc) throws RuntimeException {

		Elements elmt = doc.select("table.list tbody tr");
		String title = doc.select("div.sub_title").text();
		String tire = null;
		String part = null;

		for (Element e : elmt) {

//			tier
			String getTire = e.select(".tier").text();
			tire = getTire.length() == 0 ? tire : getTire;

			if(tire == null || tire.length() == 0)
				continue;
			
//			part
			String getPart = e.select(".part").text();
			part = getPart.length() == 0 ? part : getPart;

//			td a
			String name = e.select("td a").text();

//			td
			int size = e.select("td").size();
			int rare = e.select("td").eq(size - 1).text().length();

			map = new HashMap<>();
			map.put("title", title);
			map.put("tire", tire);
			map.put("part", part);
			map.put("name", name);
			map.put("rare", rare);

			list.add(map);

		}

	}

}
