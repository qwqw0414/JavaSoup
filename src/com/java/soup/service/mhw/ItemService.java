package com.java.soup.service.mhw;

import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;

import com.java.soup.service.ServiceAbstract;
import com.java.soup.service.ServiceImpl;

public class ItemService extends ServiceAbstract implements ServiceImpl{

	@Override
	public void excute(Document doc) throws RuntimeException {
		
		List<String> li = doc.select("#itemList a").eachText();
		int id = 0;
		
		for(String str : li) {
			
			map = new HashMap<>();
			map.put("id", id++);
			map.put("name", str);
			list.add(map);
		}
		
	}

}
