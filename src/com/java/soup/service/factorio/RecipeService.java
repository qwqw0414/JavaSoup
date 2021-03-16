package com.java.soup.service.factorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.java.soup.service.ServiceAbstract;
import com.java.soup.service.ServiceImpl;

public class RecipeService extends ServiceAbstract implements ServiceImpl {

	private final String NONE_REQUIRED = "None required";
	private final char PLUS = '+';
	private final char RESULT = '→';
	
	@Override
	public void excute(Document doc) throws RuntimeException {

		Elements elements = doc.select("div.infobox tr");

		String name = doc.select("H1#firstHeading").text();
		String getTime = null;

		String typeMatch = elements.select(".infobox-vrow-value").eq(0).text().trim();
		
		if(NONE_REQUIRED.equals(typeMatch) || typeMatch.length() == 0) 
			return;
		
		List<String> titleList = new ArrayList<>();
		List<String> amountList = new ArrayList<>();
		
		int dotCount = countOfChar(typeMatch, PLUS);
		
//		Recipe
		for (int i = 0; i < elements.size(); i++) {

			Elements recipe = elements.select("tr.border-top").eq(i);

			if ("Recipe".equals(recipe.text().trim())) {
				Elements divs = recipe.next().select("div.factorio-icon");
				
				for (Element div : divs) {
					String title = div.select("a").attr("title");

					if ("Time".equals(title)) {
						getTime = div.text();
					} else {
						titleList.add(title);
						amountList.add(div.text());
					}
				}
				break;
			}
		}

//		Elements recipe = elements.select("tr.border-top").eq(0).next();
//		String time = recipe.text();

		if (getTime == null)
			return;

		double time = Double.parseDouble(getTime);

//		Set Result
		for(int i = 0; i < titleList.size(); i++) {
			map = new HashMap<>();
			map.put("name", name);
			map.put("time", time);
			map.put("mat", titleList.get(i));
			map.put("amount", amountList.get(i));
			
			if (i == dotCount) {
				map.put("type", "R");
			} else {
				map.put("type", "M");
			}
			
			list.add(map);
		}

	}
	
	
	private int countOfChar(String line, char target) {
		
		int cnt = 0;
		
		for(int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			
			if(c == target)
				cnt++;
		}
		
		return cnt;
	}

}
