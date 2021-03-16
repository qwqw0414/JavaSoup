package com.java.soup.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.select.Elements;

public abstract class ServiceAbstract {

	protected List<Map<String, Object>> list = new ArrayList<>();
	protected Map<String, Object> map;
	protected Elements element;
	protected int cnt = 0;

	protected void print(List<Object> list) throws RuntimeException {
		for (Object map : list) {
			System.out.println(map);
		}
	}

	protected void print(Map<String, Object> map) throws RuntimeException {
		System.out.println(map);
	}

	public int count() throws RuntimeException {
		System.out.println(">> List Size : " + list.size());
		return list.size();
	}

	public void set(List<Map<String, Object>> list) throws RuntimeException {
		this.list = list;
	}
	
	public void print() throws RuntimeException {
		for (Object map : list) {
			System.out.println(map);
		}
	}

	public List<Map<String, Object>> getList() throws RuntimeException {
		return list;
	}

	public List<Map<String, Object>> getColumns(String[] columns) {

		List<Map<String, Object>> result = new ArrayList<>();

		for (Map<String, Object> map : list) {
			Map<String, Object> param = new HashMap<>();
			for (String column : columns) {
				param.put(column, map.get(column));
			}
			result.add(param);
		}
		
		return result;
	}

	public List<Object> getSql(String table) throws RuntimeException {

		List<Object> result = new ArrayList<>();

		for (Map<String, Object> map : list) {

			Set<String> keySet = map.keySet();
			String tableLine = "INSERT INTO " + table + "(";
			String valueLine = " VALUES(";
			int cnt = 0;

			for (String key : keySet) {

				String val = String.valueOf(map.get(key));
				cnt++;

				if (cnt == keySet.size()) {
					valueLine += val == null ? "null)" : "'" + val + "');";
					tableLine += key + ")";
				} else {
					valueLine += val == null ? "null, " : "'" + val + "', ";
					tableLine += key + ", ";
				}
			}
			result.add(tableLine + valueLine);
		}

		return result;
	}

}
