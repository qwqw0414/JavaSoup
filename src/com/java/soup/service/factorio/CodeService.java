package com.java.soup.service.factorio;

import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.java.soup.service.ServiceAbstract;
import com.java.soup.service.ServiceImpl;

public class CodeService extends ServiceAbstract implements ServiceImpl {

	@Override
	public void excute(Document doc) throws RuntimeException {

		Elements elements = doc.select("div.inventory div.tab div.factorio-icon");

		for (Element e : elements) {

			String id = e.select("a").attr("href").replace("/","");
			String name = e.select("a").attr("title");

			map = new HashMap<>();
			map.put("id", id);
			map.put("name", name);
			list.add(map);
		}
		

	}

}
