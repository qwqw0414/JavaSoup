package com.java.soup.service.am;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.java.soup.component.PropertiesComp;
import com.java.soup.service.common.ServiceAbstract;
import com.java.soup.service.common.ServiceImpl;
import com.sun.xml.internal.stream.buffer.sax.Properties;

public class ArmService extends ServiceAbstract implements ServiceImpl {

	private List<String> resList = new PropertiesComp().getList("option.reselmt");
	
	@Override
	public void excute(Document doc) throws RuntimeException {
		Elements elmt = doc.select("table.table tbody tr");

		for (Element e : elmt) {

//			name
			String name = e.select("td.name span").not(".cmtnum").text();

			if (name == null || name.trim().length() == 0)
				continue;

//			code
			String temp = e.select("td.name a").attr("href");
			int code = Integer.valueOf(temp.substring(temp.lastIndexOf("=") + 1));

//			type
			String type = e.select("td.type").text();

//			R
			int rare = Integer.parseInt(e.select(".name span").attr("class").replace("rare", ""));

			
//			def
			String def = e.select("td.defense").text();

//			slot
			String slot = e.select("td.slot").text();

//			elm
			List<String> elm = e.select("td.elemental span").eachText();

//			opt
			String[] opt = e.select("td.skill").html().split("<br>");

			map = new HashMap<>();
			map.put("code", code);
			map.put("name", name);
			map.put("type", type);
			map.put("rare", rare);
			map.put("def", def);
			
			{
				int i = 0;
				for(int ii : parseSlot(slot)) {
					map.put("slot_" + ++i, ii);
				}
			}
			
			List<Integer> elmtList = parseElmt(elm);
			for(int i = 0; i < resList.size(); i++) {
				map.put(resList.get(i), elmtList.get(i));
			}
			
//			map.put("opt", parseOtp(opt));

			list.add(map);
		}

	}

	private List<String> parseOtp(String[] data) throws RuntimeException {
		
		List<String> result = new ArrayList<>();
		
		for(String i : data) {
			result.add(i);
		}
		
		return result;
	}
	
	private List<Integer> parseSlot(String data) throws RuntimeException {
		
		List<Integer> result = new ArrayList<>();
		
		for(String i : data.split(" ")) {
			
			int num = 0;
			
			try {
				num = Integer.parseInt(i);
			} catch (NumberFormatException e) {
			}
			
			result.add(num);
		}
		
		while(result.size() < 3) {
			result.add(0);
		}
		
		return result;
	}
	
	private List<Integer> parseElmt(List<String> list) throws RuntimeException {

		List<Integer> result = new ArrayList<>();
		boolean sw = false;

		for (String i : list) {
			if (sw) {
				int num = 0;
				try {
					num = Integer.parseInt(i);
				} catch (NumberFormatException e) {
					sw = !sw;
				}
				result.add(num);
			}
			sw = !sw;
		}

		if (result.size() == 4)
			result.add(0);

		return result;
	}

}
