package com.psl.sm.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Myproperties extends Properties {
	
	private static final long serialVersionUID = 1L;
	private static Myproperties myproperties;

	private Myproperties() throws IOException {
		InputStream in = Myproperties.class.getClassLoader().getResourceAsStream("db.properties"); // 取出数据源里面的数据
		this.load(in);
	}

	/**
	 * 实例化
	 * 
	 * @return
	 * @throws IOException
	 */
	public static Myproperties getInstance() throws IOException {
		if (null == myproperties) {
			return new Myproperties();
		}
		return myproperties;
	}
}
