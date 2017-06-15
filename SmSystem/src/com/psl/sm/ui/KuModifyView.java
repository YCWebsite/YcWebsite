package com.psl.sm.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.psl.sm.commons.MessageUtil;
import com.psl.sm.dao.ProductDao;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class KuModifyView extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private ProductDao dao = new ProductDao();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public KuModifyView(Shell parent, int style) {
		super(parent, style);
		setText("套餐信息修改");
	}

	/**
	 * Open the dialog.
	 * 
	 * @param str
	 * 
	 * @return the result
	 */
	public Object open(String[] str) {
		createContents(str);
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
	 * 
	 * @param str
	 */
	private void createContents(final String[] str) {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.CLOSE);
		shell.setTextDirection(10);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		shell.setSize(314, 320);
		// 主窗体居中
		// Dimension：标出尺寸 Toolkit：工具包
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗口居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2, (dem.height - shell.getSize().y) / 2);
		shell.setText("产品维护");

		Label label = new Label(shell, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label.setBounds(32, 43, 61, 17);
		label.setText("产品名称：");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(99, 40, 166, 23);
		text.setText(str[0]);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("计量单位：");
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_1.setBounds(32, 109, 61, 17);

		text_1 = new Text(shell, SWT.BORDER | SWT.MULTI);
		text_1.setTextDirection(10);
		text_1.setBounds(99, 106, 166, 23);
		text_1.setText(str[1]);

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("      单价：");
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_2.setBounds(32, 165, 61, 17);

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(99, 162, 166, 23);
		text_2.setText(str[2]);

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Integer pid = Integer.parseInt(str[3]);
				String pname = text.getText();
				String ptype = text_1.getText();
				if (!"".equals(pname.trim()) & !"".equals(ptype.trim()) & !"".equals(text_2.getText().toString().trim())) {
					try{
						Float price = Float.parseFloat(text_2.getText().toString().trim());
						List<Object> param = new ArrayList<Object>();
						param.add(pname);
						param.add(ptype);
						param.add(price);
						param.add(pid);
						int flag = dao.modifyProduct(param);
						if (flag > 0) {
							MessageUtil.promt(shell, "温馨提示", "维护成功");
							shell.close();
						}
					}catch(Exception e1){
						MessageUtil.promt(shell, "警告", "请输入正确的参数类型");
					}
				} else {
					MessageUtil.promt(shell, "警告", "请将维护信息填写完整");
				}
			}
		});
		button.setBounds(179, 232, 80, 27);
		button.setText("维护");
		
	}
}
