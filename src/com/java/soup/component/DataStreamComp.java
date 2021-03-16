package com.java.soup.component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DataStreamComp {

	private static PropertiesComp prop = new PropertiesComp();

	private static final String ROOT_PATH = prop.get("path.root");

	public Document getHtml(String url) throws RuntimeException {

		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return doc;
	}

	public Element getBody(String url) throws RuntimeException {
		return getHtml(url).body();
	}

	public Document getFile(String url) throws RuntimeException {
		File file = new File(ROOT_PATH + url + ".html");
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "UTF-8");
			System.out.println(">> Write File : " + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

	public void writeList(String fileName, List<Map<String, Object>> list) throws RuntimeException {
		String path = ROOT_PATH + fileName + ".txt";
		BufferedWriter bw = null;
		
		try {

			bw = new BufferedWriter(new FileWriter(path));

			for (Object str : list) {
				bw.write(str + "\n");
			}
			
			bw.flush();
			
			System.out.println(">> Save File : " + path);

		} catch (IOException e) {
			e.printStackTrace();
			try {
				bw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void save(String fileName, List<Object> list) throws RuntimeException {

		String path = ROOT_PATH + fileName + ".sql";
		BufferedWriter bw = null;
		
		try {

			bw = new BufferedWriter(new FileWriter(path));

			for (Object str : list) {
				bw.write(str + "\n");
			}
			
			bw.flush();
			
			System.out.println(">> Save File : " + path);

		} catch (IOException e) {
			e.printStackTrace();
			try {
				bw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}


	}
	
	public void save(String fileName, Document doc) throws RuntimeException {

		String path = ROOT_PATH + fileName + ".html";
		BufferedWriter bw = null;

		this.mkDir();

		try {

			bw = new BufferedWriter(new FileWriter(path));

			bw.write(doc.html());
			bw.flush();
			System.out.println(">> Make File : " + path);

		} catch (IOException e) {
			e.printStackTrace();
			try {
				bw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public Document load(String url, String fileName) throws RuntimeException {

		String htmlPath = ROOT_PATH + fileName + ".html";
		Document doc = null;

		File file = new File(htmlPath);

		if (!file.exists()) {
			this.save(fileName, getHtml(url));
		}

		doc = getFile(fileName);

		return doc;
	}

	private void mkDir() throws RuntimeException {
		File folder = new File(ROOT_PATH);

		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!folder.exists()) {
			try {
				folder.mkdir(); // 폴더 생성합니다.
				System.out.println(">> Make Dir : " + ROOT_PATH);
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}

}
