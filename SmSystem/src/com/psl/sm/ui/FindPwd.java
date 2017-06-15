package com.psl.sm.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.psl.sm.bean.Userinfo;
import com.psl.sm.dao.UserinfoDAO;
import com.psl.sm.utils.SwtUtil;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class FindPwd {

	protected Shell shell_findPwd;
	private Text text_targetEmial;
	private Text text_identifyCode;
	private Text text_newPwd;
	
	private String randow;
	private UserinfoDAO userinfoDAO=new UserinfoDAO();
	private Userinfo userinfo=new Userinfo();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FindPwd window = new FindPwd();
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
		shell_findPwd.open();
		shell_findPwd.layout();
		while (!shell_findPwd.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell_findPwd = new Shell();
		shell_findPwd.setSize(715, 500);
		shell_findPwd.setText("找回密码");
		
		Label label_tip = new Label(shell_findPwd, SWT.NONE);
		label_tip.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_tip.setBounds(169, 413, 138, 17);
		label_tip.setText("注：*为必填项");
		
		Label lblNewLabel_tips = new Label(shell_findPwd, SWT.NONE);
		lblNewLabel_tips.setBounds(135, 37, 473, 17);
		lblNewLabel_tips.setText("温馨提示：当您未连接网络时，不可以进行密码修改。请连接网络后再进行操作\r\n");
		
		text_targetEmial = new Text(shell_findPwd, SWT.BORDER);
		text_targetEmial.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text_targetEmial.setBounds(256, 131, 195, 29);
		
		Label label_targetEmial = new Label(shell_findPwd, SWT.NONE);
		label_targetEmial.setText("邮箱：");
		label_targetEmial.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_targetEmial.setBounds(195, 134, 55, 36);
		
		Button button = new Button(shell_findPwd, SWT.NONE);
		
		button.setText("发送邮件");
		button.setBounds(482, 131, 67, 29);
		
		text_identifyCode = new Text(shell_findPwd, SWT.BORDER);
		text_identifyCode.setBounds(256, 192, 195, 29);
		
		Label label_identifyCode = new Label(shell_findPwd, SWT.NONE);
		label_identifyCode.setText("验证码：");
		label_identifyCode.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_identifyCode.setBounds(180, 192, 67, 36);
		
		text_newPwd = new Text(shell_findPwd, SWT.BORDER| SWT.PASSWORD);
		text_newPwd.setBounds(256, 252, 195, 29);
		
		Label label_newPwd = new Label(shell_findPwd, SWT.NONE);
		label_newPwd.setText("新密码：");
		label_newPwd.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_newPwd.setBounds(180, 251, 67, 29);
		
		Button button_submit = new Button(shell_findPwd, SWT.NONE);
		
		button_submit.setText("提交");
		button_submit.setGrayed(true);
		button_submit.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		button_submit.setBounds(225, 339, 80, 42);
		
		Button button_back = new Button(shell_findPwd, SWT.NONE);
		
		button_back.setText("返回");
		button_back.setGrayed(true);
		button_back.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		button_back.setBounds(403, 339, 80, 42);
		
		Label label = new Label(shell_findPwd, SWT.NONE);
		label.setText("*");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(185, 139, 10, 17);
		
		Label label_1 = new Label(shell_findPwd, SWT.NONE);
		label_1.setText("*");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setBounds(169, 197, 10, 17);
		
		Label label_2 = new Label(shell_findPwd, SWT.NONE);
		label_2.setText("*");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setBounds(169, 256, 10, 17);

		//发送邮件
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				randow=new Random().nextInt(999999)+"";
				String targetEmail=text_targetEmial.getText();
				String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		        //正则表达式的模式
		        Pattern p = Pattern.compile(RULE_EMAIL);
		        //正则表达式的匹配器
		        Matcher m = p.matcher(targetEmail);
		        if(targetEmail==null||"".equals(targetEmail)){
		        	SwtUtil.showMessageBox(shell_findPwd, "温馨提示", "邮箱不能为空");
		        }else if(m.matches()){
		        	com.psl.sm.utils.JavaMail email=new com.psl.sm.utils.JavaMail();
		        	email.sendMessage(targetEmail, "舒美墨玉管理系统验证码", "验证码："+randow+"。如非本人操作，请忽略本信息");
		        }else{
		        	SwtUtil.showMessageBox(shell_findPwd, "错误提示", "邮箱格式不正确");
		        }
			}
		});
		
		//提交
		button_submit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String code=text_identifyCode.getText();
				if(code.equals(randow)){
					try {
						String email=text_targetEmial.getText();
						String newPwd=text_newPwd.getText();
						if(email!=null&&!"".equals(email) &&newPwd!=null&&!"".equals(newPwd)){
							userinfo.setEmail(email);
							userinfo.setPwd(newPwd);
							boolean flag=userinfoDAO.findPwd(userinfo);
							if(flag){
								SwtUtil.showMessageBox(shell_findPwd, "温馨提示", "修改成功");
								shell_findPwd.dispose();
								Login login=new Login();
								login.open();
							}else{
								SwtUtil.showMessageBox(shell_findPwd, "错误提示", "修改失败！");
								text_targetEmial.setText("");
								text_newPwd.setText("");
							}
						}else{
							SwtUtil.showMessageBox(shell_findPwd, "温馨提示", "邮箱或密码不能为空");
						}
					} catch (FileNotFoundException | SQLException e1) {
						SwtUtil.showMessageBox(shell_findPwd, "错误提示", "邮箱地址不正确！");
						e1.printStackTrace();
					}
				}else{
					SwtUtil.showMessageBox(shell_findPwd, "错误提示", "验证码错误！");
				}
				
			}
		});
		
		//返回
		button_back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell_findPwd.dispose();
				Login login=new Login();
				login.open();
			}
		});
	}
	
}
