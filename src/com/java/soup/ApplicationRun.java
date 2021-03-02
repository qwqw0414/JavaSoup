package com.java.soup;

import com.java.soup.controller.SoupController;
import com.java.soup.exception.AppException;

public class ApplicationRun {

	static final SoupController sc = new SoupController();

	public static void main(String[] args) {

		System.out.println(">>> Start");
		try {

			new ApplicationRun().run();

		} catch (AppException e) {
			e.getMessage();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		System.out.println(">>> End");
	}

	public void run() {
		sc.mon();
//		sc.arm();
//		sc.wpElmt();
//		sc.sk();
	}

}
