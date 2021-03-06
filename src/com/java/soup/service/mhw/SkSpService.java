package com.java.soup.service.mhw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.java.soup.service.ServiceAbstract;
import com.java.soup.service.ServiceImpl;

public class SkSpService extends ServiceAbstract implements ServiceImpl {

	@Override
	public void excute(Document doc) throws RuntimeException {
		Elements elmt = doc.select("#listTable table tbody tr");

		for (Element e : elmt) {

			int id = Integer.parseInt(e.attr("field1"));

			String name = e.select("td.name a").text();

			String etc = e.select("td.etc0").html();

			List<String> etcList = new ArrayList<>(Arrays.asList(etc.split("<br>")));

			if (id == 7) {

				for (String i : etcList) {

					map = new HashMap<>();
					map.put("id", id);
					map.put("name", name);
					map.put("dec", i.trim());

					list.add(map);
				}
			}
		}
	}
}
