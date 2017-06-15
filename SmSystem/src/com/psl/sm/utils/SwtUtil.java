package com.psl.sm.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class SwtUtil {

	public static void showMessageBox(Shell shell,String title,String message){
		MessageBox mg=new MessageBox(shell,SWT.NONE);
		mg.setText(title);
		mg.setMessage(message);
		mg.open();
	}
}
