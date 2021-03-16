package com.java.soup.controller.factorio;

import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;

import com.java.soup.component.DataStreamComp;
import com.java.soup.component.PropertiesComp;
import com.java.soup.service.factorio.CodeService;
import com.java.soup.service.factorio.RecipeService;

public class FactorioController {

	private static final DataStreamComp data = new DataStreamComp();
	private static final PropertiesComp prop = new PropertiesComp();
	
	public void item() {

		String url = prop.get("url.factorio.item");
		String fileName = "factorio\\html\\item"; 
		
		Document doc = data.load(url, fileName);
		CodeService cs = new CodeService();
		
		cs.excute(doc);
		
		List<Map<String, Object>> codeList = cs.getList();
		RecipeService is = new RecipeService();
		doc = null;
		
		for(Map<String, Object> i : codeList) {
			
			String id = String.valueOf(i.get("id"));
			doc = data.load(url + id, fileName + "_" + id);
			is.excute(doc);
		}
		
		is.print();
		is.count();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
