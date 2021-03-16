package com.java.soup.service.mhw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.java.soup.service.ServiceAbstract;
import com.java.soup.service.ServiceImpl;

public class WpShapService extends ServiceAbstract implements ServiceImpl {

	@Override
	public void excute(Document doc) throws RuntimeException {
		
		String[] keyArr = {"red", "orange", "yellow", "green", "blue", "white", "purple", "black"};
		String[] typeArr = {"min", "max"};
		Elements elmt = doc.select("table.list tbody tr");

		for (Element e : elmt) {
			
//			name
//			String name = e.select("td.name b").text();
			
//			code
			int code = new WpService().getCode(e);
			
//			Shap
			List<Integer> shap = parseShap(e.select(".sharpness span").eachAttr("style"));
			
			if (shap.size() != 16)
				continue;
			
			int index = 0;
			
			map = new HashMap<>();
			map.put("code", code);
			
			for(String type : typeArr) {
				for(String key : keyArr) {
					map.put(type + "_" + key, shap.get(index++));
				}
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
	
}
