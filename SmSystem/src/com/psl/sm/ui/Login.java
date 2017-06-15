package com.psl.sm.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.psl.sm.bean.Userinfo;
import com.psl.sm.dao.UserinfoDAO;
import com.psl.sm.utils.SwtUtil;
import com.psl.sm.utils.UserinfoUtil;


import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Login {

	protected Shell shell;

	private Text text_pwd;
	private Label label_name;
	private Text text;
	private UserinfoDAO userinfoDAO=new UserinfoDAO();
	private Userinfo userinfo=new Userinfo();
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		shell.setLocation(new Point(78, 78));
		shell.setImage(SWTResourceManager.getImage(Login.class, "/images/logo.jpg"));
		shell.setSize(699, 474);
		shell.setText("舒美墨玉管理系统");
		// 主窗体居中
		// Dimension：标出尺寸 Toolkit：工具包
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗口居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2, (dem.height - shell.getSize().y) / 2);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(Login.class, "/images/dsbg1.png"));

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Times New Roman", 20, SWT.BOLD | SWT.ITALIC));
		label_1.setForeground(SWTResourceManager.getColor(248, 248, 255));
		label_1.setBounds(171, 37, 395, 56);
		label_1.setText("欢迎登录舒美墨玉管理系统");

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(Login.class, "/images/login2.png"));
		composite_1.setBounds(76, 99, 534, 275);

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_2.setBounds(81, 90, 71, 27);
		label_2.setText("用户名：");

		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setBounds(81, 159, 71, 27);
		label_3.setText("密   码：");

		text_pwd = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);// |SWT.PASSWORD
		text_pwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_pwd.setBounds(158, 159, 175, 27);

		label_name = new Label(composite_1, SWT.NONE);
		label_name.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_name.setBounds(158, 126, 175, 17);

		Label label = new Label(composite_1, SWT.NONE);
		label.setBounds(158, 123, 156, 17);

		Button button = new Button(composite_1, SWT.NONE);
		

		button.setImage(SWTResourceManager.getImage(Login.class, "/images/login3.png"));
		button.setBounds(349, 97, 96, 74);

		Label label_4 = new Label(composite_1, SWT.NONE);
		
		label_4.setBounds(158, 211, 61, 17);
		label_4.setText("忘记密码?");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setBounds(158, 90, 175, 27);
		
		Label label_updateName = new Label(composite_1, SWT.NONE);
		
		
		label_updateName.setText("对用户名不满意?");
		label_updateName.setBounds(243, 211, 96, 17);
		
		//登录
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String uname=text.getText();
					String upwd=text_pwd.getText();
					userinfo.setName(uname);
					userinfo.setPwd(upwd);
					
					Map<String, Object>map=userinfoDAO.login(userinfo);
					if (map != null && map.size() > 0) {
						UserinfoUtil.map=map;
						shell.dispose();
						Main main = new Main();
						main.open();
					}else{
						SwtUtil.showMessageBox(shell, "错误提示", "用户名或密码不正确！");
						text.setText("");
						text_pwd.setText("");
					}
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		//选择修改用户名
		label_updateName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
				UpdUserName updUserName=new UpdUserName();
				updUserName.open();
			}
		});
		
		//忘记密码
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
				FindPwd findPwd=new FindPwd();
				findPwd.open();
			}
		});
	}
}
