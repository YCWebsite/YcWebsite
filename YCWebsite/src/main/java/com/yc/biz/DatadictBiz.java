package com.yc.biz;

import java.util.List;

import com.yc.bean.Datadict;

public interface DatadictBiz {
	
	public List<Datadict> selectDataByType(String type);
	
	public List<Datadict> selectMainMenu();
	
	public boolean updateDataByType(Datadict datadict);
}
