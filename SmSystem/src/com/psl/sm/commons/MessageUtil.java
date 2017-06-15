package com.psl.sm.commons;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;


public class MessageUtil {
	public static void promt(Shell shell,String title,String message){
		MessageBox messageBox=new MessageBox(shell,SWT.NONE);
		messageBox.setText(title);
		messageBox.setMessage(message);
		messageBox.open();
	}
}

/*
 * 	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;

  Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBounds(0, -6, 434, 115);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBounds(66, 23, 55, 15);
		lblNewLabel_1.setText("读者编号：");
		
		text = new Text(composite, SWT.BORDER);
		text.setText("");
		text.setBounds(233, 20, 177, 21);
		
		Composite composite_2 = new Composite(composite, SWT.BORDER);
		composite_2.setBounds(10, 44, 414, 61);
		
		Label lblNewLabel_2 = new Label(composite_2, SWT.BORDER);
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setBounds(0, 0, 55, 15);
		lblNewLabel_2.setText("图书名称");
		
		Label label = new Label(composite_2, SWT.BORDER);
		label.setAlignment(SWT.CENTER);
		label.setText("读者姓名");
		label.setBounds(168, 0, 55, 15);
		
		Label label_1 = new Label(composite_2, SWT.BORDER);
		label_1.setAlignment(SWT.CENTER);
		label_1.setText("借书时间");
		label_1.setBounds(288, 0, 55, 15);
		
		Label label_3 = new Label(composite_2, SWT.BORDER);
		label_3.setAlignment(SWT.CENTER);
		label_3.setText("读者...");
		label_3.setBounds(227, 0, 55, 15);
		
		Label label_2 = new Label(composite_2, SWT.BORDER);
		label_2.setAlignment(SWT.CENTER);
		label_2.setBounds(349, 0, 65, 15);
		label_2.setText("归还时间");
		
		Label lblNewLabel_3 = new Label(composite_2, SWT.BORDER);
		lblNewLabel_3.setAlignment(SWT.CENTER);
		lblNewLabel_3.setBounds(56, 0, 48, 15);
		lblNewLabel_3.setText("图书...");
		
		Label label_4 = new Label(composite_2, SWT.BORDER);
		label_4.setAlignment(SWT.CENTER);
		label_4.setBounds(110, 0, 55, 15);
		label_4.setText("图书类别");
		
		Composite composite_1 = new Composite(shell, SWT.BORDER);
		composite_1.setBounds(0, 115, 434, 147);
		
		Composite composite_3 = new Composite(composite_1, SWT.BORDER);
		composite_3.setBounds(11, 0, 210, 147);
		
		Label lblNewLabel_6 = new Label(composite_3, SWT.NONE);
		lblNewLabel_6.setBounds(10, 19, 55, 15);
		lblNewLabel_6.setText("借书日期：");
		
		Label label_5 = new Label(composite_3, SWT.NONE);
		label_5.setText("规定天数：");
		label_5.setBounds(10, 46, 55, 15);
		
		Label label_6 = new Label(composite_3, SWT.NONE);
		label_6.setText("实际天数：");
		label_6.setBounds(10, 71, 55, 15);
		
		Label label_7 = new Label(composite_3, SWT.NONE);
		label_7.setText("超出天数");
		label_7.setBounds(10, 98, 55, 15);
		
		Label label_8 = new Label(composite_3, SWT.NONE);
		label_8.setText("罚款金额：");
		label_8.setBounds(10, 125, 55, 15);
		
		text_1 = new Text(composite_3, SWT.BORDER);
		text_1.setBounds(90, 16, 110, 21);
		
		text_2 = new Text(composite_3, SWT.BORDER);
		text_2.setBounds(90, 43, 110, 21);
		
		text_3 = new Text(composite_3, SWT.BORDER);
		text_3.setBounds(90, 68, 110, 21);
		
		text_4 = new Text(composite_3, SWT.BORDER);
		text_4.setBounds(90, 95, 110, 21);
		
		text_5 = new Text(composite_3, SWT.BORDER);
		text_5.setBounds(90, 122, 110, 21);
		
		Composite composite_4 = new Composite(composite_1, SWT.BORDER);
		composite_4.setBounds(227, 0, 197, 147);
		
		Label label_9 = new Label(composite_4, SWT.NONE);
		label_9.setText("当前时间：");
		label_9.setBounds(10, 30, 55, 15);
		
		Label label_10 = new Label(composite_4, SWT.NONE);
		label_10.setText("操  作  员：");
		label_10.setBounds(10, 80, 55, 15);
		
		text_6 = new Text(composite_4, SWT.BORDER);
		text_6.setBounds(87, 27, 110, 21);
		
		text_7 = new Text(composite_4, SWT.BORDER);
		text_7.setBounds(87, 77, 110, 21);
		
		Button btnNewButton = new Button(composite_4, SWT.NONE);
		btnNewButton.setBounds(10, 112, 75, 25);
		btnNewButton.setText("图书归还");
		
		Button btnNewButton_1 = new Button(composite_4, SWT.NONE);
		btnNewButton_1.setBounds(112, 112, 75, 25);
		btnNewButton_1.setText("退出");
		
		Label lblNewLabel_5 = new Label(composite_1, SWT.NONE);
		lblNewLabel_5.setBounds(227, 0, 55, 15);
		lblNewLabel_5.setText("系统信息");
		
		Label lblNewLabel_4 = new Label(composite_1, SWT.NONE);
		lblNewLabel_4.setBounds(11, 0, 55, 15);
		lblNewLabel_4.setText("罚款信息");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(0, 0, 55, 15);
		lblNewLabel.setText("基本信息");
 */
