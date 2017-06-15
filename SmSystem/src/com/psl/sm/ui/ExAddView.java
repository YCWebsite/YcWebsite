package com.psl.sm.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.psl.sm.commons.MessageUtil;
import com.psl.sm.dao.ProductDao;
import com.psl.sm.utils.UserinfoUtil;

public class ExAddView extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_1;
	private Text text_2;
	private ProductDao dao = new ProductDao();
	private DateTime dateTime;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ExAddView(Shell parent, int style) {
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
		shell.setSize(310, 360);
		// 主窗体居中
		// Dimension：标出尺寸 Toolkit：工具包
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗口居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2, (dem.height - shell.getSize().y) / 2);
		shell.setText("其他支出信息添加");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("支出详情：");
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_1.setBounds(31, 43, 61, 17);

		text_1 = new Text(shell, SWT.BORDER | SWT.V_SCROLL | SWT.CENTER | SWT.MULTI);
		text_1.setTextDirection(10);
		text_1.setBounds(98, 40, 166, 103);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("支出金额：");
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_2.setBounds(31, 169, 61, 17);

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(98, 166, 166, 23);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("支出时间：");
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_3.setBounds(31, 220, 61, 17);

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String note = text_1.getText();
				if(!"".equals(note) & !"".equals(text_2.getText().toString().trim())){
					try{
						Float money = Float.parseFloat(text_2.getText());
						String dateStr=dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+" "+dateTime.getHours()+":"+dateTime.getMinutes()+":"+dateTime.getSeconds();
						int userid = Integer.parseInt(UserinfoUtil.map.get("ID").toString());
						List<Object> param = new ArrayList<Object>();
						param.add(note);
						param.add(money);
						param.add(dateStr);
						param.add(userid);
						int flag = dao.addExpend(param);
						if(flag>0){
							MessageUtil.promt(shell, "温馨提示", "添加成功");
							shell.close();
						}
					}catch(Exception e1){
						MessageUtil.promt(shell, "警告", "请输入正确的参数类型！");
					}
				}else{
					MessageUtil.promt(shell, "警告", "请将添加信息填写完整！");
				}
			}
		});
		button.setBounds(191, 272, 80, 27);
		button.setText("添加");
		
		dateTime = new DateTime(shell, SWT.BORDER|SWT.DROP_DOWN);
		dateTime.setBounds(98, 220, 88, 24);
		
		

	}
}
