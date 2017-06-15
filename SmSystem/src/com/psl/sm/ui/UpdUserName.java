package com.psl.sm.ui;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.psl.sm.bean.Userinfo;
import com.psl.sm.dao.UserinfoDAO;
import com.psl.sm.utils.SwtUtil;

public class UpdUserName {

	protected Shell shell;
	
	private Text text_targetEmail;
	private Text text_identifyCode;
	private Text text_newName;
	
	private String randow;
	private UserinfoDAO userinfoDAO=new UserinfoDAO();
	private Userinfo userinfo=new Userinfo();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UpdUserName window = new UpdUserName();
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
		shell.setSize(715, 500);
		shell.setText("修改用户名");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel.setBounds(195, 126, 48, 36);
		lblNewLabel.setText("邮箱：");
		
		text_targetEmail = new Text(shell, SWT.BORDER);
		text_targetEmail.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text_targetEmail.setBounds(250, 127, 195, 29);
		
		Label label_identifyCode = new Label(shell, SWT.NONE);
		label_identifyCode.setText("验证码：");
		label_identifyCode.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_identifyCode.setBounds(177, 187, 67, 36);
		
		text_identifyCode = new Text(shell, SWT.BORDER);
		text_identifyCode.setBounds(250, 188, 195, 29);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(470, 124, 67, 29);
		btnNewButton.setText("发送邮件");
		
		Label label_tips = new Label(shell, SWT.NONE);
		label_tips.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_tips.setText("温馨提示：当您未连接网络时，不可以进行密码修改。请连接网络后再进行操作\r\n");
		label_tips.setBounds(152, 45, 435, 29);
		
		Label label_newName = new Label(shell, SWT.NONE);
		label_newName.setText("新用户名：");
		label_newName.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_newName.setBounds(164, 251, 80, 29);
		
		text_newName = new Text(shell, SWT.BORDER);
		text_newName.setBounds(250, 252, 195, 29);
		
		Button button_ensureUpdate = new Button(shell, SWT.NONE);
		
		button_ensureUpdate.setGrayed(true);
		button_ensureUpdate.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		button_ensureUpdate.setBounds(286, 326, 80, 42);
		button_ensureUpdate.setText("确认修改");
		
		Button button_return = new Button(shell, SWT.NONE);
		
		button_return.setText("返回");
		button_return.setGrayed(true);
		button_return.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		button_return.setBounds(441, 326, 80, 42);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("*");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(184, 130, 11, 11);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("*");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setBounds(166, 191, 11, 11);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("*");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setBounds(152, 256, 11, 11);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setBounds(152, 403, 110, 17);
		lblNewLabel_1.setText("注：*为必填项");
		
		
		//发送邮件
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String emailAddr=text_targetEmail.getText();
				String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		        //正则表达式的模式
		        Pattern p = Pattern.compile(RULE_EMAIL);
		        //正则表达式的匹配器
		        Matcher m = p.matcher(emailAddr);
		        
				if(emailAddr==null||"".equals(emailAddr)){
					SwtUtil.showMessageBox(shell, "温馨提示", "邮箱不能为空");
				}else if(m.matches()){
					randow=new Random().nextInt(999999)+"";
					String targetEmail=text_targetEmail.getText();
					com.psl.sm.utils.JavaMail email=new com.psl.sm.utils.JavaMail();
					
					email.sendMessage(targetEmail, "舒美墨玉管理系统验证码", "验证码："+randow+"。如非本人操作，请忽略本信息");
				}else{
					SwtUtil.showMessageBox(shell, "错误提示", "邮箱格式不正确");
				}
			}
		});
		
		//确认修改用户名
		button_ensureUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String code=text_identifyCode.getText();
				if(code.equals(randow)){
					String newName=text_newName.getText();
					String email=text_targetEmail.getText();
					try {
						if(newName!=null&&!"".equals(newName)  && email!=null&&!"".equals(email)){
							userinfo.setEmail(email);
							userinfo.setName(newName);
						}else{
							SwtUtil.showMessageBox(shell, "温馨提示", "邮箱或用户名不能为空");
						}
						
						boolean flag=userinfoDAO.updateUserName(userinfo);
						if(flag){
							SwtUtil.showMessageBox(shell, "温馨提示", "修改成功");
							shell.dispose();
							Login login=new Login();
							login.open();
						}else{
							SwtUtil.showMessageBox(shell, "错误提示", "修改失败");
							text_newName.setText("");
							text_targetEmail.setText("");
						}
					} catch (FileNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}else{
					SwtUtil.showMessageBox(shell, "错误提示", "验证码错误！");
				}
			}
		});
		//返回登录界面
		button_return.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				Login login=new Login();
				login.open();
			}
		});
	}

}
