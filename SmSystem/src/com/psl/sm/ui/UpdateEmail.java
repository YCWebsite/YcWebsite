package com.psl.sm.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.awt.Dimension;
import java.awt.Toolkit;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.psl.sm.bean.Userinfo;
import com.psl.sm.dao.UserinfoDAO;
import com.psl.sm.utils.SwtUtil;
import com.psl.sm.utils.UserinfoUtil;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class UpdateEmail extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_newEmail;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public UpdateEmail(Shell parent, int style) {
		super(parent, style);
		setText("修改邮箱");
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
		shell.setSize(328, 263);
		// 主窗体居中
		// Dimension：标出尺寸 Toolkit：工具包
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗口居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2, (dem.height - shell.getSize().y) / 2);
		shell.setText("修改邮箱");

		Label label_oldEmail = new Label(shell, SWT.NONE);
		label_oldEmail.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_oldEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_oldEmail.setBounds(41, 43, 66, 27);
		label_oldEmail.setText("旧邮箱：");
		

		Button button = new Button(shell, SWT.NONE);
		
		button.setBounds(232, 181, 80, 27);
		button.setText("确认修改");
		
		Label lblNewLabel_oldEmail = new Label(shell, SWT.NONE);
		lblNewLabel_oldEmail.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_oldEmail.setBounds(113, 43, 166, 27);
		lblNewLabel_oldEmail.setText(UserinfoUtil.map.get("EMAIL").toString());
		
		Label label_newEmail = new Label(shell, SWT.NONE);
		label_newEmail.setText("新邮箱：");
		label_newEmail.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_newEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_newEmail.setBounds(41, 94, 66, 27);
		
		text_newEmail = new Text(shell, SWT.BORDER);
		text_newEmail.setBounds(114, 94, 165, 27);
		
		//修改邮箱
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String email = text_newEmail.getText();
				String name=UserinfoUtil.map.get("NAME").toString();
				 String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		        //正则表达式的模式
		        Pattern p = Pattern.compile(RULE_EMAIL);
		        //正则表达式的匹配器
		        Matcher m = p.matcher(email);
		        if(m.matches()){
		        	UserinfoDAO userinfoDAO=new UserinfoDAO();
		        	Userinfo userinfo=new Userinfo();
		        	userinfo.setEmail(email);
		        	userinfo.setName(name);
		        	try {
						boolean flag=userinfoDAO.updateEmail(userinfo);
						if(flag){
							SwtUtil.showMessageBox(shell, "温馨提示", "修改成功");
							shell.dispose();
							
						}else{
							SwtUtil.showMessageBox(shell, "错误提示", "修改失败");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
		        }
				
				
			}
		});

	}
}
