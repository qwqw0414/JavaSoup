package com.java.soup;

import com.java.soup.controller.factorio.FactorioController;
import com.java.soup.controller.mhw.MhwController;
import com.java.soup.exception.AppException;

public class ApplicationRun {

	static final MhwController sc = new MhwController();
	static final FactorioController fc = new FactorioController();

	public static void main(String[] args) {

		System.out.println(">>> Start");
		try {

//			new ApplicationRun().mhwRun();
			new ApplicationRun().factorioRun();

		} catch (AppException e) {
			e.getMessage();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		System.out.println(">>> End");
	}

	public void factorioRun() {
		fc.item();
	}
	
	public void mhwRun() {
//		sc.mon();
//		sc.arm();
//		sc.wpElmt();
//		sc.sk();
//		sc.item();
	}
}
