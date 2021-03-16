package com.java.soup.controller.mhw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;

import com.java.soup.component.DataStreamComp;
import com.java.soup.component.PropertiesComp;
import com.java.soup.service.mhw.ArmService;
import com.java.soup.service.mhw.ItemService;
import com.java.soup.service.mhw.MonInfoService;
import com.java.soup.service.mhw.MonService;
import com.java.soup.service.mhw.SkillService;
import com.java.soup.service.mhw.WpElmtService;
import com.java.soup.service.mhw.WpService;
import com.java.soup.service.mhw.WpShapService;

public class MhwController {

	private static final DataStreamComp data = new DataStreamComp();
	private static final PropertiesComp prop = new PropertiesComp();

	public void sk() throws RuntimeException {

		String url = prop.get("url.sk") + "Materials_and_recipes";
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

		Document doc = data.load(prop.get("url.mon") + "/?code=0", "mon");
		MonService ms = new MonService();

		ms.excute(doc);
		ms.print();
		ms.count();
		
//		data.save("mon", ms.getSql("Null"));

		MonInfoService mis = new MonInfoService();
		doc = null;

//		getInfoList
		for (int i = 1; i <= 91; i++) {
			doc = data.load(prop.get("url.mon") + "/?code=" + i, "mon\\mon_" + i);
			mis.excute(doc);
		}
		
//		code Join
		String[] paraArr = { "code", "name" };
		List<Map<String, Object>> misList = mis.getList();
		List<Map<String, Object>> codeList = ms.getColumns(paraArr);
		
		for(int i = 0; i < misList.size(); i++) {
			for(int j = 0; j < codeList.size(); j++) {
				
				if(codeList.get(j).get("name").equals(misList.get(i).get("title"))) {
					Map<String, Object> map = misList.get(i);
					map.put("code",codeList.get(j).get("code"));
					misList.set(i, map);
				}
			}
		}
		
		mis.set(misList);
//		mis.print();
		mis.count();
		
		data.writeList("\\txt\\mis", mis.getList());
	}

	public void arm() throws RuntimeException {

		Document doc = data.load(prop.get("url.arm"), "arm");
		ArmService as = new ArmService();

		as.excute(doc);
		as.print();
		as.count();

		data.save("arm", as.getSql("tb_am"));
	}

	public void item() {
		
		String url = prop.get("url.item");
		String fileName = "it";
		
		Document doc = data.load(url, fileName);
		ItemService is = new ItemService();
		
		is.excute(doc);
		is.print();
		is.count();
		
	}

}
