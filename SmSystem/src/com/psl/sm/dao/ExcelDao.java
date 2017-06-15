package com.psl.sm.dao;

import java.io.File;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Shell;

import com.psl.sm.commons.DbHelper;
import com.psl.sm.commons.MessageUtil;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelDao {
	DbHelper db = new DbHelper();
	Shell shell=new Shell();
	
	public void StoreinfoExcel() {
		
	        try {
	            WritableWorkbook wwb = null;
	             
	               // 创建可写入的Excel工作簿
	               String fileName = "D://库存信息.xls";
	               File file=new File(fileName);
	               if (!file.exists()) {
	                   file.createNewFile();
	               }
	               //以fileName为文件名来创建一个Workbook
	               wwb = Workbook.createWorkbook(file);

	               // 创建工作表
	               WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
	               
	               //查询数据库中所有的数据
	               StoreinfoDao dao = new StoreinfoDao();
	       		   //dao.findAllpowerinfo();
	       		   List<Map<String,Object>> list= dao.StoreSelect();
	       		   
	       		   //System.out.println(dao.findAllrolestyle());
	               //要插入到的Excel表格的行号，默认从0开始
	               Label labelSID= new Label(0, 0, "商品编号");//表示第
	               Label labelCName= new Label(2, 0, "商品名");
	               Label labelNum= new Label(4, 0, "库存数量");             
	               
	               ws.addCell(labelSID);
	               ws.addCell(labelCName);
	               ws.addCell(labelNum);

	               for (int i = 0; i < list.size(); i++) {
	            	   //map.get("PWD").toString()
	                   Label labelSID_i= new Label(0, i+1,list.get(i).get("SID")+"");
	                   Label labelCName_i= new Label(2, i+1, list.get(i).get("CNAME")+"");
	                   Label labelNum_i= new Label(4, i+1, list.get(i).get("STORENUM")+"");
	                   
	                   ws.addCell(labelSID_i);
		               ws.addCell(labelCName_i);
		               ws.addCell(labelNum_i);
	               }
	             
	              //写进文档
	               wwb.write();
	               if(ws.getColumns()>0){
	            	   MessageUtil.promt(shell, "温馨提示", "导出成功,位置为:D://库存信息.xls");
	               }else{
	            	   MessageUtil.promt(shell, "温馨提示", "导出失败");
	               }
	              // 关闭Excel工作簿对象
	               wwb.close();

	        } catch (Exception e) {
	        	MessageUtil.promt(shell, "警告", "文件已存在");

	        } 
	    }
}       