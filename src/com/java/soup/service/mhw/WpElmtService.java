package com.java.soup.service.mhw;

import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.java.soup.component.PropertiesComp;
import com.java.soup.service.ServiceAbstract;
import com.java.soup.service.ServiceImpl;

public class WpElmtService extends ServiceAbstract implements ServiceImpl {

	private List<String> elmtList = new PropertiesComp().getList("option.elmt");

	@Override
	public void excute(Document doc) throws RuntimeException {

		Elements element = doc.select("table.list tbody tr");
		WpService ws = new WpService();

		for (Element e : element) {

//			option
			String opt = e.select("td.option").text();

//			hasOption
			if (opt == null || opt.length() == 0)
				continue;

//			hasElmt
			for (String elmt : elmtList) {
				if (opt.contains(elmt)) {

//					code
					int code = ws.getCode(e);

					String[] optArr = opt.split(" ");
					int amount = 0;
					String drg = null;

					for (int i = 0; i < optArr.length; i++) {
						if (optArr[i].contains(elmt.trim())) {
							try {
								amount = Integer.parseInt(optArr[i + 1].replace(")", ""));
							} catch (NumberFormatException ex) {
							}
						}

						if (optArr[i].contains("용봉력")) {
							drg = optArr[i + 1];
						}
					}

					int drgBind = 0;
					
					if(drg != null) {
						switch (drg) {
						case "대":
							drgBind = 3;
							break;
						case "중":
							drgBind = 2;
							break;
						case "소":
							drgBind = 1;
							break;
						default:
							drgBind = 0;
							break;
						}
					}
					
//					옵션 파싱
					String bind = opt.indexOf("(") < 0 ? "F" : "T";

					map = new HashMap<>();
					map.put("code", code);
					map.put("elmt_name", elmt.trim());
					map.put("elmt_value", amount);
					map.put("is_bind", bind);
					map.put("drgon_bind", drgBind);

					list.add(map);

					break;
				}
			}
		}
	}
}
