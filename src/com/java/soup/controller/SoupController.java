package com.java.soup.controller;

import org.jsoup.nodes.Document;

import com.java.soup.component.DataStreamComp;
import com.java.soup.component.PropertiesComp;
import com.java.soup.service.am.ArmService;
import com.java.soup.service.mon.MonInfoService;
import com.java.soup.service.mon.MonService;
import com.java.soup.service.sk.SkillService;
import com.java.soup.service.wp.WpElmtService;
import com.java.soup.service.wp.WpService;
import com.java.soup.service.wp.WpShapService;

public class SoupController {

	private static final DataStreamComp data = new DataStreamComp();
	private static final PropertiesComp prop = new PropertiesComp();

	public void sk() throws RuntimeException {

		String url = prop.get("url.sk");
		String fileName = "sk";

		SkillService ss = new SkillService();

		Document doc = data.load(url, fileName);
		ss.excute(doc);
		ss.print();
		ss.count();
	}

	public void wp() throws RuntimeException {

		String url = prop.get("url.wp");
		String fileName = "wp";

		WpService ws = new WpService();

		for (int i = 1; i <= 14; i++) {
			Document doc = data.load(url + i, fileName + i);
			ws.excute(doc);
		}

//		ws.print();
		ws.count();
		data.save("tet", ws.getSql("tb_wp"));
	}

	public void wpElmt() throws RuntimeException {
		String url = prop.get("url.wp");
		String fileName = "wp";

		WpElmtService ws = new WpElmtService();

		for (int i = 1; i <= 14; i++) {
			Document doc = data.load(url + i, fileName + i);
			ws.excute(doc);
		}

//		ws.print();
		ws.count();
		data.save("tet", ws.getSql("tb_wp_elmt"));
	}

	public void wpShap() throws RuntimeException {

		String url = prop.get("url.wp");
		String fileName = "wp";

		WpShapService ws = new WpShapService();

		for (int i = 1; i <= 14; i++) {
			Document doc = data.load(url + i, fileName + i);
			ws.excute(doc);
		}

//		ws.print();
		ws.count();
		data.save("tet", ws.getSql("tb_wp_shap"));
	}

	public void mon() throws RuntimeException {

		Document doc = data.load(prop.get("url.mon"), "mon");
		MonService ms = new MonService();

//		ms.excute(doc);
//		ms.count();

//		data.save("mon", ms.getSql("Null"));

//		info

		MonInfoService mis = new MonInfoService();
		doc = null;

		for (int i = 0; i <= 91; i++) {

			doc = data.load(prop.get("url.mon") + "/?code=" + i, "mon\\mon_" + i);
			mis.excute(doc);
		}
		mis.count();

	}

	public void arm() throws RuntimeException {

		Document doc = data.load(prop.get("url.arm"), "arm");
		ArmService as = new ArmService();

		as.excute(doc);
		as.print();
		as.count();

		data.save("arm", as.getSql("tb_am"));
	}

}
