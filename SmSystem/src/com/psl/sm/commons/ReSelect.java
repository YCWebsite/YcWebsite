package com.psl.sm.commons;

import java.io.FileNotFoundException;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.psl.sm.dao.ProductDao;
import com.psl.sm.dao.RuleDao;

public class ReSelect {
	Shell shell = new Shell();

	/**
	 * 套餐table查询所有
	 * 
	 * @param table
	 * @throws FileNotFoundException
	 */
	public void RuleSelect(Table table){
		RuleDao ruledao = new RuleDao();
		List<Map<String, Object>> list = ruledao.searchRule();
		table.removeAll();
		if (null != list && list.size() > 0) {
			for (Map<String, Object> map : list) {
				TableItem tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(new String[] { map.get("RID").toString(), map.get("RNAME").toString(),
						map.get("RNOTE").toString(), map.get("MEM_MONEY").toString(),
						map.get("PUB_MONEY").toString() });
			}
		} else {
			MessageUtil.promt(shell, "温馨提示", "无套餐信息");
		}
	}

	public void KuSelect(Table table) {
		ProductDao productdao = new ProductDao();
		List<Map<String, Object>> list = productdao.searchProduct();
		table.removeAll();
		if (null != list && list.size() > 0) {
			for (Map<String, Object> map : list) {
				TableItem tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(new String[] { map.get("PID").toString(), map.get("PNAME").toString(),
						map.get("PNUM").toString(), map.get("PTYPE").toString(),map.get("PRICE").toString() });
			}
		} else {
			MessageUtil.promt(shell, "温馨提示", "无库存信息");
		}
		
	}
}
