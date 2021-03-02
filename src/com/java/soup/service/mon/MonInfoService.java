package com.java.soup.service.mon;

import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.java.soup.service.common.ServiceAbstract;
import com.java.soup.service.common.ServiceImpl;

public class MonInfoService extends ServiceAbstract implements ServiceImpl {

	@Override
	public void excute(Document doc) throws RuntimeException {

		Elements elmt = doc.select("table.table tbody tr");
		String title = doc.select("div.sub_title").text();
		
		for (Element e : elmt) {

//			tier
//			part
//			td a
//			td
			
			map = new HashMap<>();
			map.put("title", title);
			
			list.add(map);

		}

	}

}
