package com.java.soup.controller;

import org.jsoup.nodes.Document;

import com.java.soup.component.DataStreamComp;
import com.java.soup.component.PropertiesComp;
import com.java.soup.service.ArmService;
import com.java.soup.service.MonService;
import com.java.soup.service.WpService;

public class SoupController {

	private static final DataStreamComp data = new DataStreamComp();
	private static final PropertiesComp prop = new PropertiesComp();

	public void wp() throws RuntimeException {
		
		String url = prop.get("url.wp");
		String fileName = "wp";
		
		WpService ws = new WpService();
		
		for(int i = 1; i <= 14; i++) {
			Document doc = data.load(url + i, fileName + i);
			ws.excute(doc);
		}
		
		ws.print();
		ws.count();
	}
	
	public void mon() throws RuntimeException {

		Document doc = data.load(prop.get("url.mon"), "mon");
		MonService ms = new MonService();

		ms.excute(doc);
		ms.count();

		data.save("mon", ms.getSql("Null"));
	}

	public void arm() throws RuntimeException {

		Document doc = data.load(prop.get("url.arm"), "arm");
		ArmService as = new ArmService();
		
		as.excute(doc);
//		as.print();
//		as.count();
	}

}
