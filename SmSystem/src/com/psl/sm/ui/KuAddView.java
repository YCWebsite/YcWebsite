package com.psl.sm.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.psl.sm.commons.MessageUtil;
import com.psl.sm.dao.ProductDao;
import com.psl.sm.utils.UserinfoUtil;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class KuAddView extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private ProductDao dao = new ProductDao();
	private Text text;
	private Text text_4;
	private Combo combo; 
	private boolean isExist; 

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public KuAddView(Shell parent, int style) {
		super(parent, style);
		setText("添加套餐信息修改");
	}

	/**
	 * Open the dialog.
	 * @param str 
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 * @param str 
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM|SWT.APPLICATION_MODAL|SWT.CLOSE);
		shell.setTextDirection(10);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		shell.setSize(385, 459);
		// 主窗体居中
		// Dimension：标出尺寸 Toolkit：工具包
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗口居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2, (dem.height - shell.getSize().y) / 2);
		shell.setText("产品进货");

		Label label = new Label(shell, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label.setBounds(46, 43, 61, 17);
		label.setText("产品名称：");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("进货数量：");
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_1.setBounds(46, 94, 61, 17);

		text_1 = new Text(shell, SWT.BORDER | SWT.MULTI);
		text_1.setTextDirection(10);
		text_1.setBounds(113, 91, 190, 23);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("进货总额：");
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_2.setBounds(46, 136, 61, 17);

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(113, 133, 190, 23);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("设定单价：");
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_3.setBounds(46, 175, 61, 17);

		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(113, 175, 192, 23);

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!text.getText().trim().equals("") & !text_1.getText().trim().equals("") & !text_2.getText().trim().equals("") 
						& !text_3.getText().trim().equals("") & !text_4.getText().trim().equals("")
						& !combo.getText().trim().equals("")){
					try{
						String addPname=combo.getText().toString().trim();
						Integer addNum=Integer.parseInt(text_1.getText().toString().trim());
						Float addMoney=Float.parseFloat(text_2.getText().toString().trim());
						String note = text.getText().toString().trim();
						Float price = Float.parseFloat(text_3.getText().toString().trim());
						String jishu = text_4.getText().toString().trim();
						int userid = Integer.parseInt(UserinfoUtil.map.get("ID").toString());
						int i=0;
						if(isExist){
							i=dao.addProductNum(addNum,addPname);
						}else{
							i=dao.addNewProduct(addPname,addNum,price,jishu,userid);
						}
						i+=dao.addExpend(note,addMoney,userid);
						if(i==2){
							MessageUtil.promt(shell, "温馨提示", "添加成功");
							shell.dispose();
						}
					}catch(Exception e1){
						MessageUtil.promt(shell, "警告", "请输入正确的参数类型");
					}		
				}else{
					MessageUtil.promt(shell, "警告", "请将进货信息填写完整");
				}
			}
		});
		button.setBounds(253, 381, 80, 27);
		button.setText("添加");
		
		combo = new Combo(shell, SWT.NONE);
		combo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String str=combo.getText().toString().trim();
				if(combo.indexOf(str)!=-1){
					isExist=true;
					List<Object> param = new ArrayList<Object>();
					param.add(str);
					Map<String, Object> map = dao.findProductByPname(param);
					text_3.setText(map.get("PRICE").toString());
					text_4.setText(map.get("PTYPE").toString());
					text_3.setEnabled(false);
					text_4.setEnabled(false);
				}else{
					isExist=false;
					text_3.setText("");
					text_4.setText("");
					text_3.setEnabled(true);
					text_4.setEnabled(true);
				}
				
			}
		});
		combo.setBounds(113, 40, 190, 25);
		loadCombo(combo);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_4.setBounds(46, 261, 61, 17);
		label_4.setText("支出详情：");
		
		text = new Text(shell, SWT.BORDER | SWT.V_SCROLL | SWT.CENTER);
		text.setBounds(113, 258, 192, 95);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_5.setBounds(46, 221, 61, 17);
		label_5.setText("计量单位");
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBounds(113, 215, 190, 23);
		
		

	}

	private void loadCombo(Combo combo) {
		List<Map<String, Object>> list = dao.searchProduct();
		for(int i = 0;i<list.size();i++){
			String pname = list.get(i).get("PNAME").toString();
			combo.add(pname, i);
		}
	}
}
