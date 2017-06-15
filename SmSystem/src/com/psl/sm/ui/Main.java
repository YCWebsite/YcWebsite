package com.psl.sm.ui;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

import com.psl.sm.bean.Userinfo;
import com.psl.sm.commons.LogUtil;
import com.psl.sm.commons.MessageUtil;
import com.psl.sm.commons.ReSelect;
import com.psl.sm.dao.ExpendDAO;
import com.psl.sm.dao.MemberDAO;
import com.psl.sm.dao.Pay_vipDAO;
import com.psl.sm.dao.Total_consumeDAO;
import com.psl.sm.dao.UserinfoDAO;
import com.psl.sm.utils.SwtUtil;
import com.psl.sm.utils.TimeUtils;
import com.psl.sm.utils.UserinfoUtil;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.eclipse.swt.widgets.DateTime;

public class Main {

	protected Shell shell;

	private StackLayout stackLayout;

	MemberDAO memberDAO = new MemberDAO();
	ExpendDAO expendDAO=new ExpendDAO();
	Pay_vipDAO pay_vipDAO=new Pay_vipDAO();
	Total_consumeDAO total_consumeDAO=new Total_consumeDAO();
	UserinfoDAO userinfoDAO=new UserinfoDAO();
	Userinfo userinfo=new Userinfo();

	private Tree tree;

	private TreeItem treeItem_5;
	private Button button_addPubRule;
	private Button button_vipReg;
	private Button button_vipSelectType;
	private Button button_comsunSelectType;
	private Button button_addPubProduct;
	private Button button_viplogin;
	private Button btnNewButton;
	private Button button_publist;
	private Button button_21;
	private Button btnexcel;
	private MenuItem menuItem_1;
	private MenuItem menuItem_vipdeleterule;
	private MenuItem menuItem_vipdeleteproduct;
	private MenuItem menuItem_pubrulepense;
	private MenuItem menuItem_pubproductepsene;
	private MenuItem menuItem_vipToTable;
	private MenuItem menuItem_clrToTable;
	private Combo combo_vipselectway;
	private Combo combo_expenseselectway;
	private Combo combo_mealtype;
	private Combo combo_vipaugmentedproduct;
	private Combo combo_producttype;
	private Button button_vipPwdUpdate;
	private Button button_vipAddMoney;
	private Button button_addvipexpense;
	private Button button_addproductexpense;
	private Button button_3;
	private Label label_addviptel;
	private Label label_addvippwd1;
	private Label label_addvippwd2;
	private Label label_vipnewpwd;
	private Label label_vipnewpwd2;
	private Label label_viploginpaytel;
	private Label lbl_vipallmoney;
	private Label label_puballmoney;
	private Composite composite_show;
	private Composite composite_vippay;
	private Composite composite_pubpay;
	private Composite composite_addvip;
	private Composite composite_selectvip;
	private Composite composite_payvip;
	private Composite composite_consumrecord;
	private Composite composite_updatePwd;
	private Composite composite_logOff;
	private Composite composite_vipRcg;
	private Text text_expensenum;
	private Table table_vipproductexpense;
	private Text text_mealbuynum;
	private Table table_rulemeal;
	private Text text_3;
	private Text text_vippaytel;
	private Text text_vippaypwd;
	private Text text_addvipname;
	private Text text_addviptel;
	private Text text_addvippwd;
	private Text text_addvippwd2;
	private Text text_addvipmoney;
	private Text text_selectvipmes;
	private Text text_payvipname;
	private Text text_payviptel;
	private Table table_vipmessage;
	private Text text_12;
	private Text text_13;
	private Text text_vipnewpwd;
	private Text text_vipnewpwd2;
	private Text text_payvipmoney;
	private Text text_selectvipbuymes;
	private Table table_viprulebuymes;
	private Combo combo_vipmealtype;
	private Text text_rulenum;
	private Table table_vipruleexpense;
	private Text text_productbuynum;
	private Table table_productmeal;
	private Table table_vipproductbuymes;
	private Label label_userinfoName;
	private Label label_oldPwd;
	private Label label_uname;
	private Text text_old;
	private Label label_newPwd ;
	private Text text_newAgain;
	private Label label_name;
	private  Label label_old;
	private Label label_newAgain;
	private  Label label_med;
	private  Label label_weak;
	private  Label label_str ;
	private  Text text_new;
	private Button button;
	private Button button_1;
	private Label label_ensureNewPwd ;
	private Label lblNewLabel;
	private Label lblNewLabel_2;
	private Label label ;
	private Label label_1;
	private Label label_2 ;
	private Label lblNewLabel_3 ;
	private  Label label_email ;
	private Button btnNewButton_updEmail;
	private Label lblNewLabel_logOff;
	private Label label_emailLabel;
	private Group group_rule;
	private TableColumn tcitem_1;
	private TableColumn tcitem_2 ;
	private TableColumn tcitem_3 ;
	private TableColumn tcitem_4;
	private TableColumn tcitem_5;
	private Menu menu_tc;
	private Group group_ku ;
	private TableColumn kuitem_1;
	private TableColumn kuitem_2;
	private TableColumn kuitem_3;
	private TableColumn kuitem_4 ;
	private TableColumn kuitem_5 ;
	private Menu menu ;

	private Table table_tc;
	private Table table_ku;
	private Composite composite_rule;
	private Composite  composite_ku;
	private MenuItem menuItem_tc;
	private MenuItem menuItem_ku;
	private MenuItem menuItem_costToExcel;
	private ReSelect  tableflush = new ReSelect();
	Date date=new Date();
	@SuppressWarnings("deprecation")
	String time= date.getMinutes()+"";
	String userName;
	private Label label_vipFunction;
	private Combo combo_vipSelect;
	private DateTime dateTime_vipTableTime;
	private Button button_vipFind;
	private Table table_vipTable;
	private TableColumn tableColumn_29;
	private TableColumn tableColumn_30;
	private TableColumn tableColumn_31;
	private TableColumn tableColumn_32;
	private TableColumn tableColumn_33;
	private TableColumn tableColumn_34;
	private TableItem tableItem_1;
	
	private int ListSize=0;
	private int ListSize2=0;
	private Composite composite_nVipRcg;
	private Label label_NVipFunction;
	private Combo combo_NvipSelect;
	private DateTime dateTime_NvipTableTime;
	private Button button_NvipFind;
	private Table table_NvipTableOfProduct;
	private TableColumn tableColumn_36;
	private TableColumn tableColumn_37;
	private TableColumn tableColumn_38;
	private TableColumn tableColumn_39;
	private TableColumn tblclmnNewColumn_10;
	private TableColumn tblclmnNewColumn_11;
	private Table table_NvipTableOfRule;
	private TableColumn tableColumn_35;
	private TableColumn tableColumn_40;
	private TableColumn tableColumn_41;
	private TableColumn tableColumn_42;
	private TableColumn tableColumn_43;
	private TableColumn tableColumn_44;
	private Composite composite_cost;
	private Label label_costFunction;
	private Combo combo_costSelect;
	private DateTime dateTime_costTableTime;
	private Button button_costFind;
	private Table table_costTable;
	private TableColumn tableColumn_46;
	private TableColumn tableColumn_47;
	private TableColumn tableColumn_49;
	private TableColumn tableColumn_50;
	private TreeItem treeItem_7;
	private Composite composite_clr;
	private Label label_clrFunction;
	private Combo combo_clrSelect;
	private DateTime dateTime_clrTableTime;
	private Button button_clrFind;
	private Table table_clrTable;
	private TableColumn tableColumn_45;
	private TableColumn tableColumn_48;
	private TableColumn tableColumn_51;
	private TableColumn tableColumn_52;
	private TableColumn tableColumn_53;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
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
		shell.setImage(SWTResourceManager.getImage(Main.class, "/images/logo.jpg"));
		shell.setSize(959, 619);
		shell.setText("舒美墨玉管理系统");
		// 主窗体居中
		// Dimension：标出尺寸 Toolkit：工具包
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗口居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2,
				(dem.height - shell.getSize().y) / 2);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite main_composite = new Composite(shell, SWT.NONE);
		main_composite.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		main_composite.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_BACKGROUND));
		main_composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(main_composite, SWT.NONE);

		Composite composite_chooice = new Composite(sashForm, SWT.NONE);
		composite_chooice.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));

		tree = new Tree(composite_chooice, SWT.NONE);
		tree.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tree.setBounds(10, 53, 143, 498);

		TreeItem treeItem = new TreeItem(tree, SWT.NONE);
		treeItem.setText("  收银台");

		treeItem_5 = new TreeItem(treeItem, SWT.NONE);
		treeItem_5.setText("会员收费");

		TreeItem trtmNewTreeitem_1 = new TreeItem(treeItem, SWT.NONE);
		trtmNewTreeitem_1.setText("非会员收费");
		treeItem.setExpanded(true);

		TreeItem trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		trtmNewTreeitem.setText("  会员管理");

		TreeItem trtmNewTreeitem_2 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_2.setText("会员信息添加");

		TreeItem trtmNewTreeitem_3 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_3.setText("会员信息查询");

		TreeItem trtmNewTreeitem_4 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_4.setText("会员充值管理");

		TreeItem trtmNewTreeitem_5 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_5.setText("消费信息查询");
		trtmNewTreeitem.setExpanded(true);

		TreeItem treeItem_1 = new TreeItem(tree, SWT.NONE);
		treeItem_1.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		treeItem_1.setText("  套餐管理");

		TreeItem trtmNewTreeitem_6 = new TreeItem(treeItem_1, SWT.NONE);
		trtmNewTreeitem_6.setText("查看套餐信息");

		TreeItem trtmNewTreeitem_7 = new TreeItem(treeItem_1, SWT.NONE);
		trtmNewTreeitem_7.setText("添加套餐信息");
		treeItem_1.setExpanded(true);

		TreeItem treeItem_2 = new TreeItem(tree, SWT.NONE);
		treeItem_2.setText("  产品管理");

		TreeItem trtmNewTreeitem_9 = new TreeItem(treeItem_2, SWT.NONE);
		trtmNewTreeitem_9.setText("库存查看");

		TreeItem trtmNewTreeitem_10 = new TreeItem(treeItem_2, SWT.NONE);
		trtmNewTreeitem_10.setText("产品进货");

		TreeItem treeItem_6 = new TreeItem(treeItem_2, SWT.NONE);
		treeItem_6.setText("其他支出");
		treeItem_2.setExpanded(true);

		TreeItem treeItem_3 = new TreeItem(tree, SWT.NONE);
		treeItem_3.setText("  统计报表");

		TreeItem trtmNewTreeitem_11 = new TreeItem(treeItem_3, SWT.NONE);
		trtmNewTreeitem_11.setText("会员充值报表");

		TreeItem trtmNewTreeitem_12 = new TreeItem(treeItem_3, SWT.NONE);
		trtmNewTreeitem_12.setText("非会员消费报表");

		TreeItem trtmNewTreeitem_13 = new TreeItem(treeItem_3, SWT.NONE);
		trtmNewTreeitem_13.setText("支出报表");
		
		treeItem_7 = new TreeItem(treeItem_3, 0);
		treeItem_7.setText("纯收入报表");
		treeItem_3.setExpanded(true);

		TreeItem treeItem_4 = new TreeItem(tree, SWT.NONE);
		treeItem_4.setText("  系统管理");

		TreeItem trtmNewTreeitem_14 = new TreeItem(treeItem_4, SWT.NONE);
		trtmNewTreeitem_14.setText("修改密码和邮箱");

		TreeItem trtmNewTreeitem_15 = new TreeItem(treeItem_4, SWT.NONE);
		trtmNewTreeitem_15.setText("退出系统");
		treeItem_4.setExpanded(true);

		composite_show = new Composite(sashForm, SWT.NONE);
		composite_show.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_BACKGROUND));
		stackLayout = new StackLayout();
		composite_show.setLayout(stackLayout);

		composite_vippay = new Composite(composite_show, SWT.NONE);
		composite_vippay.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite_vippay.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite_vippay, SWT.VERTICAL);
		sashForm_1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_BACKGROUND));

		Group group = new Group(sashForm_1, SWT.NONE);

		Label lblNewLabel_tel = new Label(group, SWT.NONE);
		lblNewLabel_tel.setBounds(127, 30, 61, 17);
		lblNewLabel_tel.setText("手机号码：");

		text_vippaytel = new Text(group, SWT.BORDER);
		text_vippaytel.setBounds(194, 27, 101, 23);

		Label label_vipPwd = new Label(group, SWT.NONE);
		label_vipPwd.setText("密码：");
		label_vipPwd.setBounds(378, 30, 36, 17);

		text_vippaypwd = new Text(group, SWT.BORDER | SWT.PASSWORD);
		text_vippaypwd.setBounds(420, 27, 101, 23);

		button_viplogin = new Button(group, SWT.NONE);

		button_viplogin.setBounds(542, 25, 61, 27);
		button_viplogin.setText("登录");

		label_viploginpaytel = new Label(group, SWT.NONE);
		label_viploginpaytel.setBounds(654, 30, 101, 17);

		Group group_1 = new Group(sashForm_1, SWT.NONE);

		table_vipproductexpense = new Table(group_1, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_vipproductexpense.setBounds(128, 286, 504, 156);
		table_vipproductexpense.setHeaderVisible(true);
		table_vipproductexpense.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table_vipproductexpense,
				SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("产品编号");

		TableColumn tableColumn_1 = new TableColumn(table_vipproductexpense,
				SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("产品名称");

		TableColumn tableColumn_2 = new TableColumn(table_vipproductexpense,
				SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("产品单价");

		TableColumn tableColumn_3 = new TableColumn(table_vipproductexpense,
				SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("消费数量");

		TableColumn tableColumn_4 = new TableColumn(table_vipproductexpense,
				SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("消费金额");

		Menu menu_3 = new Menu(table_vipproductexpense);
		table_vipproductexpense.setMenu(menu_3);

		menuItem_vipdeleteproduct = new MenuItem(menu_3, SWT.NONE);
		menuItem_vipdeleteproduct.setText("删除");

		Label label_vipBuyMoney = new Label(group_1, SWT.NONE);
		label_vipBuyMoney.setBounds(168, 471, 61, 17);
		label_vipBuyMoney.setText("消费总额：");

		btnNewButton = new Button(group_1, SWT.NONE);

		btnNewButton.setBounds(529, 466, 103, 27);
		btnNewButton.setText("结算付款");

		Label label_vipProductType = new Label(group_1, SWT.NONE);
		label_vipProductType.setBounds(128, 247, 61, 17);
		label_vipProductType.setText("附加产品：");

		combo_vipaugmentedproduct = new Combo(group_1, SWT.NONE);
		combo_vipaugmentedproduct.setBounds(195, 244, 88, 25);

		Label label_productNum = new Label(group_1, SWT.NONE);
		label_productNum.setBounds(354, 247, 61, 17);
		label_productNum.setText("消费数量：");

		text_expensenum = new Text(group_1, SWT.BORDER);
		text_expensenum.setBounds(421, 244, 88, 23);

		button_addproductexpense = new Button(group_1, SWT.NONE);

		button_addproductexpense.setText("添加消费");
		button_addproductexpense.setBounds(544, 242, 88, 27);

		button_addvipexpense = new Button(group_1, SWT.NONE);
		button_addvipexpense.setBounds(544, 24, 88, 27);
		button_addvipexpense.setText("添加消费");

		Label label_rulenum = new Label(group_1, SWT.NONE);
		label_rulenum.setBounds(354, 29, 61, 17);
		label_rulenum.setText("消费数量：");

		text_rulenum = new Text(group_1, SWT.BORDER);
		text_rulenum.setBounds(421, 26, 88, 23);

		Label label_vipRuleType = new Label(group_1, SWT.NONE);
		label_vipRuleType.setBounds(128, 29, 61, 17);
		label_vipRuleType.setText("套餐类别：");

		combo_vipmealtype = new Combo(group_1, SWT.NONE);
		combo_vipmealtype.setBounds(195, 26, 88, 25);

		table_vipruleexpense = new Table(group_1, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_vipruleexpense.setLinesVisible(true);
		table_vipruleexpense.setHeaderVisible(true);
		table_vipruleexpense.setBounds(128, 66, 504, 156);

		TableColumn tableColumn_11 = new TableColumn(table_vipruleexpense,
				SWT.NONE);
		tableColumn_11.setWidth(100);
		tableColumn_11.setText("套餐编号");

		TableColumn tableColumn_12 = new TableColumn(table_vipruleexpense,
				SWT.NONE);
		tableColumn_12.setWidth(100);
		tableColumn_12.setText("套餐名称");

		TableColumn tableColumn_13 = new TableColumn(table_vipruleexpense,
				SWT.NONE);
		tableColumn_13.setWidth(100);
		tableColumn_13.setText("套餐单价");

		TableColumn tableColumn_14 = new TableColumn(table_vipruleexpense,
				SWT.NONE);
		tableColumn_14.setWidth(100);
		tableColumn_14.setText("消费数量");

		TableColumn tableColumn_16 = new TableColumn(table_vipruleexpense,
				SWT.NONE);
		tableColumn_16.setWidth(100);
		tableColumn_16.setText("消费金额");

		Menu menu_2 = new Menu(table_vipruleexpense);
		table_vipruleexpense.setMenu(menu_2);

		menuItem_vipdeleterule = new MenuItem(menu_2, SWT.NONE);

		menuItem_vipdeleterule.setText("删除");

		lbl_vipallmoney = new Label(group_1, SWT.NONE);
		lbl_vipallmoney.setBounds(235, 471, 97, 17);
		sashForm_1.setWeights(new int[] { 66, 511 });

		composite_pubpay = new Composite(composite_show, SWT.NONE);
		composite_pubpay.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));

		SashForm sashForm_2 = new SashForm(composite_pubpay, SWT.VERTICAL);
		sashForm_2.setBounds(0, 0, 779, 560);

		Group group_2 = new Group(sashForm_2, SWT.NONE);

		Label label_pubRuleType = new Label(group_2, SWT.NONE);
		label_pubRuleType.setText("套餐类别：");
		label_pubRuleType.setBounds(111, 32, 61, 17);

		combo_mealtype = new Combo(group_2, SWT.NONE);
		combo_mealtype.setBounds(178, 29, 88, 25);

		Label label_pubRuleNum = new Label(group_2, SWT.NONE);
		label_pubRuleNum.setText("消费数量：");
		label_pubRuleNum.setBounds(309, 32, 61, 17);

		text_mealbuynum = new Text(group_2, SWT.BORDER);
		text_mealbuynum.setBounds(386, 29, 88, 23);

		button_addPubRule = new Button(group_2, SWT.NONE);

		button_addPubRule.setText("添加消费");
		button_addPubRule.setBounds(530, 27, 88, 27);

		table_rulemeal = new Table(group_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_rulemeal.setBounds(111, 73, 504, 171);
		table_rulemeal.setLinesVisible(true);
		table_rulemeal.setHeaderVisible(true);

		TableColumn tableColumn_5 = new TableColumn(table_rulemeal, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("套餐编号");

		TableColumn tableColumn_6 = new TableColumn(table_rulemeal, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("套餐名称");

		TableColumn tableColumn_7 = new TableColumn(table_rulemeal, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("套餐单价");

		TableColumn tableColumn_8 = new TableColumn(table_rulemeal, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("消费数量");

		TableColumn tableColumn_9 = new TableColumn(table_rulemeal, SWT.NONE);
		tableColumn_9.setWidth(100);
		tableColumn_9.setText("消费金额");

		Menu menu_4 = new Menu(table_rulemeal);
		table_rulemeal.setMenu(menu_4);

		menuItem_pubrulepense = new MenuItem(menu_4, SWT.NONE);
		menuItem_pubrulepense.setText("删除");

		Group group_3 = new Group(sashForm_2, SWT.NONE);

		Label label_11 = new Label(group_3, SWT.NONE);
		label_11.setText("消费总额：");
		label_11.setBounds(168, 337, 61, 17);

		text_3 = new Text(group_3, SWT.BORDER);
		text_3.setBounds(235, 334, 94, 23);

		Button button_2 = new Button(group_3, SWT.NONE);
		button_2.setText("结算付款");
		button_2.setBounds(528, 332, 103, 27);

		Label label_pubProductType = new Label(group_3, SWT.NONE);
		label_pubProductType.setText("附加产品：");
		label_pubProductType.setBounds(115, 27, 61, 17);

		combo_producttype = new Combo(group_3, SWT.NONE);
		combo_producttype.setBounds(182, 24, 88, 25);

		Label label_pubProductNum = new Label(group_3, SWT.NONE);
		label_pubProductNum.setText("消费数量：");
		label_pubProductNum.setBounds(326, 27, 61, 17);

		text_productbuynum = new Text(group_3, SWT.BORDER);
		text_productbuynum.setBounds(394, 24, 88, 23);

		button_addPubProduct = new Button(group_3, SWT.NONE);

		button_addPubProduct.setText("添加消费");
		button_addPubProduct.setBounds(531, 22, 88, 27);

		table_productmeal = new Table(group_3, SWT.BORDER | SWT.FULL_SELECTION);
		table_productmeal.setLinesVisible(true);
		table_productmeal.setHeaderVisible(true);
		table_productmeal.setBounds(115, 68, 504, 171);

		TableColumn tableColumn_17 = new TableColumn(table_productmeal,
				SWT.NONE);
		tableColumn_17.setWidth(100);
		tableColumn_17.setText("产品编号");

		TableColumn tableColumn_18 = new TableColumn(table_productmeal,
				SWT.NONE);
		tableColumn_18.setWidth(100);
		tableColumn_18.setText("产品名称");

		TableColumn tableColumn_19 = new TableColumn(table_productmeal,
				SWT.NONE);
		tableColumn_19.setWidth(100);
		tableColumn_19.setText("产品单价");

		TableColumn tableColumn_20 = new TableColumn(table_productmeal,
				SWT.NONE);
		tableColumn_20.setWidth(100);
		tableColumn_20.setText("消费数量");

		TableColumn tableColumn_21 = new TableColumn(table_productmeal,
				SWT.NONE);
		tableColumn_21.setWidth(100);
		tableColumn_21.setText("消费金额");

		Menu menu_5 = new Menu(table_productmeal);
		table_productmeal.setMenu(menu_5);

		menuItem_pubproductepsene = new MenuItem(menu_5, SWT.NONE);
		menuItem_pubproductepsene.setText("删除");

		Label label_10 = new Label(group_3, SWT.NONE);
		label_10.setBounds(152, 260, 61, 17);
		label_10.setText("消费总额：");

		button_publist = new Button(group_3, SWT.NONE);

		button_publist.setBounds(528, 260, 91, 27);
		button_publist.setText("消费结算");

		label_puballmoney = new Label(group_3, SWT.NONE);
		label_puballmoney.setBounds(219, 260, 94, 17);
		sashForm_2.setWeights(new int[] { 257, 300 });

		composite_addvip = new Composite(composite_show, SWT.NONE);
		composite_addvip.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite_addvip.setBackgroundMode(SWT.INHERIT_FORCE);

		Label lblNewLabel_vipAddName = new Label(composite_addvip, SWT.NONE);
		lblNewLabel_vipAddName.setBounds(263, 104, 61, 17);
		lblNewLabel_vipAddName.setText("会员名称：");

		Label label_vipAddTel = new Label(composite_addvip, SWT.NONE);
		label_vipAddTel.setText("电话号码：");
		label_vipAddTel.setBounds(263, 172, 61, 17);

		Label label_vipAddPwd1 = new Label(composite_addvip, SWT.NONE);
		label_vipAddPwd1.setText("会员密码：");
		label_vipAddPwd1.setBounds(263, 247, 61, 17);

		Label label_vipAddPwd2 = new Label(composite_addvip, SWT.NONE);
		label_vipAddPwd2.setText("确认密码：");
		label_vipAddPwd2.setBounds(263, 325, 61, 17);

		text_addvipname = new Text(composite_addvip, SWT.BORDER);
		text_addvipname.setBounds(342, 101, 125, 23);

		text_addviptel = new Text(composite_addvip, SWT.BORDER);

		text_addviptel.setBounds(342, 169, 125, 23);

		text_addvippwd = new Text(composite_addvip, SWT.BORDER | SWT.PASSWORD);

		text_addvippwd.setBounds(342, 244, 125, 23);

		text_addvippwd2 = new Text(composite_addvip, SWT.BORDER | SWT.PASSWORD);

		text_addvippwd2.setBounds(342, 322, 125, 23);

		label_addviptel = new Label(composite_addvip, SWT.NONE);
		label_addviptel.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_RED));
		label_addviptel.setBounds(512, 172, 229, 17);

		label_addvippwd2 = new Label(composite_addvip, SWT.NONE);
		label_addvippwd2.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_RED));
		label_addvippwd2.setBounds(512, 325, 229, 17);

		Label label_vipAddMoney = new Label(composite_addvip, SWT.NONE);
		label_vipAddMoney.setBounds(263, 397, 61, 17);
		label_vipAddMoney.setText("充值余额：");

		text_addvipmoney = new Text(composite_addvip, SWT.BORDER);
		text_addvipmoney.setBounds(342, 394, 125, 23);

		button_vipReg = new Button(composite_addvip, SWT.NONE);
		button_vipReg.setBounds(387, 479, 80, 27);
		button_vipReg.setText("注册");

		label_addvippwd1 = new Label(composite_addvip, SWT.NONE);
		label_addvippwd1.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_RED));
		label_addvippwd1.setBounds(512, 247, 229, 17);

		composite_selectvip = new Composite(composite_show, SWT.NONE);
		composite_selectvip.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite_selectvip.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_3 = new SashForm(composite_selectvip, SWT.VERTICAL);

		Group group_4 = new Group(sashForm_3, SWT.NONE);

		Label lblNewLabel_SelectType = new Label(group_4, SWT.NONE);
		lblNewLabel_SelectType.setBounds(105, 48, 61, 17);
		lblNewLabel_SelectType.setText("查询方式：");

		combo_vipselectway = new Combo(group_4, SWT.NONE);
		combo_vipselectway.add("");
		combo_vipselectway.add("会员名称");
		combo_vipselectway.add("手机号码");
		combo_vipselectway.select(0);

		combo_vipselectway.setBounds(187, 45, 88, 25);

		button_vipSelectType = new Button(group_4, SWT.NONE);

		button_vipSelectType.setBounds(534, 43, 80, 27);
		button_vipSelectType.setText("查询");

		text_selectvipmes = new Text(group_4, SWT.BORDER);
		text_selectvipmes.setBounds(333, 45, 167, 23);

		Group group_5 = new Group(sashForm_3, SWT.NONE);

		Label label_15 = new Label(group_5, SWT.NONE);
		label_15.setBounds(10, 10, 61, 17);
		label_15.setText("基本信息");

		table_vipmessage = new Table(group_5, SWT.BORDER | SWT.FULL_SELECTION);
		table_vipmessage.setBounds(90, 36, 588, 144);
		table_vipmessage.setHeaderVisible(true);
		table_vipmessage.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table_vipmessage,
				SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("会员编号");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table_vipmessage,
				SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("会员名称");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table_vipmessage,
				SWT.NONE);
		tblclmnNewColumn_2.setWidth(141);
		tblclmnNewColumn_2.setText("手机号码");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table_vipmessage,
				SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("账号余额");

		TableColumn tableColumn_15 = new TableColumn(table_vipmessage, SWT.NONE);
		tableColumn_15.setWidth(143);
		tableColumn_15.setText("注册时间");

		Menu menu_1 = new Menu(table_vipmessage);
		table_vipmessage.setMenu(menu_1);

		menuItem_1 = new MenuItem(menu_1, SWT.NONE);

		menuItem_1.setText("信息修改");

		Group group_6 = new Group(sashForm_3, SWT.NONE);

		Label lblNewLabel_selectVipName = new Label(group_6, SWT.NONE);
		lblNewLabel_selectVipName.setBounds(215, 34, 61, 17);
		lblNewLabel_selectVipName.setText("会员名称：");

		Label lblNewLabel_selectVipPwd = new Label(group_6, SWT.NONE);
		lblNewLabel_selectVipPwd.setBounds(215, 179, 61, 17);
		lblNewLabel_selectVipPwd.setText("确认密码：");

		Label lblNewLabel_selectVipTel = new Label(group_6, SWT.NONE);
		lblNewLabel_selectVipTel.setBounds(215, 82, 61, 17);
		lblNewLabel_selectVipTel.setText("手机号码：");

		Label lblNewLabel_selectVipNewPwd = new Label(group_6, SWT.NONE);
		lblNewLabel_selectVipNewPwd.setBounds(215, 133, 61, 17);
		lblNewLabel_selectVipNewPwd.setText("新 密 码 ：");

		button_vipPwdUpdate = new Button(group_6, SWT.NONE);

		button_vipPwdUpdate.setBounds(432, 230, 80, 27);
		button_vipPwdUpdate.setText("确认修改");

		text_12 = new Text(group_6, SWT.BORDER);
		text_12.setEditable(false);
		text_12.setBounds(296, 31, 132, 23);

		text_13 = new Text(group_6, SWT.BORDER);
		text_13.setEditable(false);
		text_13.setBounds(296, 79, 132, 23);

		text_vipnewpwd = new Text(group_6, SWT.BORDER | SWT.PASSWORD);

		text_vipnewpwd.setBounds(296, 130, 132, 23);

		text_vipnewpwd2 = new Text(group_6, SWT.BORDER | SWT.PASSWORD);

		text_vipnewpwd2.setBounds(296, 176, 132, 23);

		Label label_16 = new Label(group_6, SWT.NONE);
		label_16.setBounds(10, 10, 61, 17);
		label_16.setText("信息修改");

		label_vipnewpwd = new Label(group_6, SWT.NONE);
		label_vipnewpwd.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_RED));
		label_vipnewpwd.setBounds(481, 133, 246, 17);

		label_vipnewpwd2 = new Label(group_6, SWT.NONE);
		label_vipnewpwd2.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_RED));
		label_vipnewpwd2.setBounds(481, 179, 246, 17);
		sashForm_3.setWeights(new int[] { 96, 191, 267 });

		composite_payvip = new Composite(composite_show, SWT.NONE);
		composite_payvip.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));

		Label label_vipPayName = new Label(composite_payvip, SWT.NONE);
		label_vipPayName.setBounds(254, 154, 61, 17);
		label_vipPayName.setText("会员名称：");

		text_payvipname = new Text(composite_payvip, SWT.BORDER);
		text_payvipname.setBounds(335, 148, 145, 23);

		Label label_vipPayTel = new Label(composite_payvip, SWT.NONE);
		label_vipPayTel.setText("手机号码：");
		label_vipPayTel.setBounds(254, 245, 61, 17);

		text_payviptel = new Text(composite_payvip, SWT.BORDER);
		text_payviptel.setBounds(335, 242, 145, 23);

		button_vipAddMoney = new Button(composite_payvip, SWT.NONE);

		button_vipAddMoney.setBounds(400, 411, 80, 27);
		button_vipAddMoney.setText("确认充值");

		Label label_vipPayMoney = new Label(composite_payvip, SWT.NONE);
		label_vipPayMoney.setText("充值金额：");
		label_vipPayMoney.setBounds(254, 332, 61, 17);

		text_payvipmoney = new Text(composite_payvip, SWT.BORDER);
		text_payvipmoney.setBounds(335, 329, 145, 23);

		composite_consumrecord = new Composite(composite_show, SWT.NONE);
		composite_consumrecord.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite_consumrecord.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_4 = new SashForm(composite_consumrecord, SWT.VERTICAL);

		Group group_7 = new Group(sashForm_4, SWT.NONE);

		Label label_comsunSelectType = new Label(group_7, SWT.NONE);
		label_comsunSelectType.setBounds(115, 32, 61, 17);
		label_comsunSelectType.setText("查询方式：");

		combo_expenseselectway = new Combo(group_7, SWT.NONE);
		combo_expenseselectway.add("");
		combo_expenseselectway.add("会员名称");
		combo_expenseselectway.add("手机号码");
		combo_vipselectway.select(0);
		combo_expenseselectway.setBounds(205, 29, 88, 25);

		text_selectvipbuymes = new Text(group_7, SWT.BORDER);
		text_selectvipbuymes.setBounds(350, 29, 164, 23);

		button_comsunSelectType = new Button(group_7, SWT.NONE);

		button_comsunSelectType.setBounds(578, 27, 80, 27);
		button_comsunSelectType.setText("查询");

		Group group_8 = new Group(sashForm_4, SWT.NONE);

		table_viprulebuymes = new Table(group_8, SWT.BORDER | SWT.FULL_SELECTION);
		table_viprulebuymes.setBounds(27, 63, 726, 170);
		table_viprulebuymes.setHeaderVisible(true);
		table_viprulebuymes.setLinesVisible(true);

		TableColumn tblclmnNewColumn_4 = new TableColumn(table_viprulebuymes,
				SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("会员编号");

		TableColumn tblclmnNewColumn_5 = new TableColumn(table_viprulebuymes,
				SWT.NONE);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("会员名称");

		TableColumn tblclmnNewColumn_6 = new TableColumn(table_viprulebuymes,
				SWT.NONE);
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("套餐名称");

		TableColumn tblclmnNewColumn_7 = new TableColumn(table_viprulebuymes,
				SWT.NONE);
		tblclmnNewColumn_7.setText("套餐单价");
		tblclmnNewColumn_7.setWidth(100);

		TableColumn tblclmnNewColumn_8 = new TableColumn(table_viprulebuymes,
				SWT.NONE);
		tblclmnNewColumn_8.setWidth(100);
		tblclmnNewColumn_8.setText("消费数量");

		TableColumn tblclmnNewColumn_9 = new TableColumn(table_viprulebuymes,
				SWT.NONE);
		tblclmnNewColumn_9.setWidth(100);
		tblclmnNewColumn_9.setText("消费金额");

		TableColumn tableColumn_10 = new TableColumn(table_viprulebuymes, SWT.NONE);
		tableColumn_10.setWidth(120);
		tableColumn_10.setText("消费时间");

		Label label_comsunSelectRule = new Label(group_8, SWT.NONE);
		label_comsunSelectRule.setBounds(10, 27, 84, 17);
		label_comsunSelectRule.setText("套餐消费详情：");

		Label label_comsunSelectProduct = new Label(group_8, SWT.NONE);
		label_comsunSelectProduct.setText("产品消费详情：");
		label_comsunSelectProduct.setBounds(10, 254, 84, 17);

		table_vipproductbuymes = new Table(group_8, SWT.BORDER | SWT.FULL_SELECTION);
		table_vipproductbuymes.setLinesVisible(true);
		table_vipproductbuymes.setHeaderVisible(true);
		table_vipproductbuymes.setBounds(27, 287, 726, 170);

		TableColumn tableColumn_22 = new TableColumn(table_vipproductbuymes, SWT.NONE);
		tableColumn_22.setWidth(100);
		tableColumn_22.setText("会员编号");

		TableColumn tableColumn_23 = new TableColumn(table_vipproductbuymes, SWT.NONE);
		tableColumn_23.setWidth(100);
		tableColumn_23.setText("会员名称");

		TableColumn tableColumn_24 = new TableColumn(table_vipproductbuymes, SWT.NONE);
		tableColumn_24.setWidth(100);
		tableColumn_24.setText("产品名称");

		TableColumn tableColumn_25 = new TableColumn(table_vipproductbuymes, SWT.NONE);
		tableColumn_25.setWidth(100);
		tableColumn_25.setText("产品单价");

		TableColumn tableColumn_26 = new TableColumn(table_vipproductbuymes, SWT.NONE);
		tableColumn_26.setWidth(100);
		tableColumn_26.setText("消费数量");

		TableColumn tableColumn_27 = new TableColumn(table_vipproductbuymes, SWT.NONE);
		tableColumn_27.setWidth(100);
		tableColumn_27.setText("消费金额");

		TableColumn tableColumn_28 = new TableColumn(table_vipproductbuymes, SWT.NONE);
		tableColumn_28.setWidth(120);
		tableColumn_28.setText("消费时间");
		sashForm_4.setWeights(new int[] {83, 494});

		sashForm.setWeights(new int[] { 161, 779 });


		//以下为修改密码
		composite_updatePwd = new Composite(composite_show, SWT.NONE);
		composite_updatePwd.setBackgroundMode(SWT.INHERIT_FORCE);
		composite_updatePwd.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		sashForm.setWeights(new int[] { 161, 779 });

		label_userinfoName = new Label(composite_updatePwd, SWT.NONE);
		label_userinfoName.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_userinfoName.setAlignment(SWT.RIGHT);
		label_userinfoName.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		label_userinfoName.setBounds(165, 119, 72, 29);
		label_userinfoName.setText("用户名：");

		label_oldPwd = new Label(composite_updatePwd, SWT.NONE);
		label_oldPwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_oldPwd.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		label_oldPwd.setBounds(165, 177, 85, 29);
		label_oldPwd.setText(" 旧密码：");

		label_uname = new Label(composite_updatePwd, SWT.NONE);
		label_uname.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_uname.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_uname.setBounds(256, 122, 181, 29);
		label_uname.setText(UserinfoUtil.map.get("NAME").toString());


		text_old = new Text(composite_updatePwd, SWT.BORDER|SWT.PASSWORD);
		text_old.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text_old.setBounds(256, 179, 181, 29);

		label_newPwd = new Label(composite_updatePwd, SWT.NONE);
		label_newPwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_newPwd.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		label_newPwd.setBounds(165, 240, 85, 29);
		label_newPwd.setText(" 新密码：");


		text_newAgain = new Text(composite_updatePwd, SWT.BORDER|SWT.PASSWORD);

		text_newAgain.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text_newAgain.setBounds(255, 301, 180, 29);

		label_name = new Label(composite_updatePwd, SWT.NONE);
		label_name.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_name.setBounds(459, 130, 151, 17);

		label_old = new Label(composite_updatePwd, SWT.NONE);
		label_old.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_old.setBounds(459, 184, 151, 17);

		label_newAgain = new Label(composite_updatePwd, SWT.NONE);
		label_newAgain.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_newAgain.setBounds(459, 304, 151, 17);

		label_med = new Label(composite_updatePwd, SWT.NONE);
		label_med.setAlignment(SWT.CENTER);
		label_med.setBounds(507, 252, 61, 17);

		label_weak = new Label(composite_updatePwd, SWT.NONE);
		label_weak.setAlignment(SWT.CENTER);
		label_weak.setBounds(459, 252, 50, 17);

		label_str = new Label(composite_updatePwd, SWT.NONE);
		label_str.setAlignment(SWT.CENTER);
		label_str.setBounds(559, 252, 61, 17);

		text_new = new Text(composite_updatePwd, SWT.BORDER|SWT.PASSWORD);

		text_new.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text_new.setBounds(256, 242, 181, 29);

		button = new Button(composite_updatePwd, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		button.setBounds(412, 435, 80, 36);
		button.setText("提交");

		button_1 = new Button(composite_updatePwd, SWT.NONE);

		button_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		button_1.setBounds(530, 435, 80, 36);
		button_1.setText("返回");

		label_ensureNewPwd = new Label(composite_updatePwd, SWT.NONE);
		label_ensureNewPwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_ensureNewPwd.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		label_ensureNewPwd.setBounds(151, 299, 85, 29);
		label_ensureNewPwd.setText("确认密码：");

		lblNewLabel = new Label(composite_updatePwd, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel.setBounds(156, 124, 11, 17);
		lblNewLabel.setText("*");

		lblNewLabel_2 = new Label(composite_updatePwd, SWT.NONE);
		lblNewLabel_2.setBounds(165, 43, 420, 17);
		lblNewLabel_2.setText("温馨提示，当您未连接网络时，不可以进行密码修改。请连接网络后再进行操作");

		label = new Label(composite_updatePwd, SWT.NONE);
		label.setText("*");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(158, 181, 11, 17);

		label_1 = new Label(composite_updatePwd, SWT.NONE);
		label_1.setText("*");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setBounds(156, 243, 11, 17);

		label_2 = new Label(composite_updatePwd, SWT.NONE);
		label_2.setText("*");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setBounds(138, 303, 11, 17);

		lblNewLabel_3 = new Label(composite_updatePwd, SWT.NONE);
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_3.setBounds(151, 488, 135, 17);
		lblNewLabel_3.setText("注：*为必填项");
		//将用户的邮箱显示在label框中
		userName=UserinfoUtil.map.get("NAME").toString();

		label_emailLabel = new Label(composite_updatePwd, SWT.NONE);
		label_emailLabel.setText("邮箱：");
		label_emailLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_emailLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		label_emailLabel.setBounds(188, 354, 51, 29);

		label_email = new Label(composite_updatePwd, SWT.NONE);
		label_email.setBounds(256, 354, 181, 29);

		label_email.setText(UserinfoUtil.map.get("NAME").toString());

		btnNewButton_updEmail = new Button(composite_updatePwd, SWT.NONE);

		btnNewButton_updEmail.setBounds(474, 354, 94, 27);
		btnNewButton_updEmail.setText("点击修改邮箱");


		//退出系统
		composite_logOff = new Composite(composite_show, SWT.NONE);

		lblNewLabel_logOff = new Label(composite_logOff, SWT.NONE);
		lblNewLabel_logOff.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblNewLabel_logOff.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.ITALIC));
		lblNewLabel_logOff.setBounds(305, 140, 203, 42);
		lblNewLabel_logOff.setText("您真的要退出系统吗？");

		button_21 = new Button(composite_logOff, SWT.NONE);

		button_21.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		button_21.setBounds(246, 210, 80, 27);
		button_21.setText("是");

		button_3 = new Button(composite_logOff, SWT.NONE);

		button_3.setText("否");
		button_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		button_3.setBounds(464, 210, 80, 27);


		composite_rule = new Composite(composite_show, SWT.NONE);
		composite_rule.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite_rule.setLayout(new FillLayout(SWT.BORDER));

		group_rule = new Group(composite_rule, SWT.NONE);
		group_rule.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		group_rule.setText("套餐信息");
		FillLayout fl_group_rule = new FillLayout(SWT.HORIZONTAL);
		fl_group_rule.marginHeight = 15;
		fl_group_rule.marginWidth = 10;
		group_rule.setLayout(fl_group_rule);

		table_tc = new Table(group_rule, SWT.NONE | SWT.FULL_SELECTION);
		table_tc.setHeaderVisible(true);
		table_tc.setLinesVisible(true);

		tcitem_1 = new TableColumn(table_tc, SWT.NONE);
		tcitem_1.setWidth(61);
		tcitem_1.setText("套餐编号");

		tcitem_2 = new TableColumn(table_tc, SWT.NONE);
		tcitem_2.setWidth(94);
		tcitem_2.setText("套餐名称");

		tcitem_3 = new TableColumn(table_tc, SWT.NONE);
		tcitem_3.setWidth(481);
		tcitem_3.setText("套餐详情");

		tcitem_4 = new TableColumn(table_tc, SWT.NONE);
		tcitem_4.setWidth(59);
		tcitem_4.setText("会员价");

		tcitem_5 = new TableColumn(table_tc, SWT.NONE);
		tcitem_5.setWidth(66);
		tcitem_5.setText("非会员价");

		menu_tc = new Menu(table_tc);
		table_tc.setMenu(menu_tc);

		menuItem_tc = new MenuItem(menu_tc, SWT.NONE);
		menuItem_tc.setText("修改");

		composite_ku = new Composite(composite_show, SWT.NONE);
		composite_ku.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite_ku.setLayout(new FillLayout(SWT.HORIZONTAL));

		group_ku = new Group(composite_ku, SWT.NONE);
		group_ku.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		group_ku.setText("库存信息");
		FillLayout fl_group_ku = new FillLayout(SWT.HORIZONTAL);
		fl_group_ku.marginHeight = 15;
		fl_group_ku.marginWidth = 10;
		group_ku.setLayout(fl_group_ku);

		table_ku = new Table(group_ku, SWT.FULL_SELECTION);
		table_ku.setHeaderVisible(true);
		table_ku.setLinesVisible(true);

		kuitem_1 = new TableColumn(table_ku, SWT.NONE);
		kuitem_1.setWidth(90);
		kuitem_1.setText("产品编号");

		kuitem_2 = new TableColumn(table_ku, SWT.NONE);
		kuitem_2.setWidth(166);
		kuitem_2.setText("产品名称");

		kuitem_3 = new TableColumn(table_ku, SWT.NONE);
		kuitem_3.setWidth(138);
		kuitem_3.setText("库存数量");

		kuitem_4 = new TableColumn(table_ku, SWT.NONE);
		kuitem_4.setWidth(143);
		kuitem_4.setText("计量单位");

		kuitem_5 = new TableColumn(table_ku, SWT.NONE);
		kuitem_5.setWidth(152);
		kuitem_5.setText("单价");

		menu = new Menu(table_ku);
		table_ku.setMenu(menu);

		menuItem_ku = new MenuItem(menu, SWT.NONE);
		menuItem_ku.setText("产品维护");
		
		composite_clr = new Composite(composite_show, SWT.NONE);
		
		label_clrFunction = new Label(composite_clr, SWT.NONE);
		label_clrFunction.setText("统计方式：");
		label_clrFunction.setBounds(55, 58, 61, 17);
		
		combo_clrSelect = new Combo(composite_clr, SWT.NONE);
		combo_clrSelect.setBounds(122, 55, 96, 25);
		combo_clrSelect.add("按年统计");
		combo_clrSelect.add("按月统计");
		combo_clrSelect.add("按日统计");
		
		dateTime_clrTableTime = new DateTime(composite_clr, SWT.BORDER);
		dateTime_clrTableTime.setBounds(239, 55, 124, 24);
		
		button_clrFind = new Button(composite_clr, SWT.NONE);
		
		button_clrFind.setText("查询");
		button_clrFind.setBounds(394, 53, 80, 27);
		
		table_clrTable = new Table(composite_clr, SWT.BORDER | SWT.FULL_SELECTION);
		table_clrTable.setLinesVisible(true);
		table_clrTable.setHeaderVisible(true);
		table_clrTable.setBounds(50, 117, 637, 418);
		
		tableColumn_45 = new TableColumn(table_clrTable, SWT.NONE);
		tableColumn_45.setWidth(131);
		tableColumn_45.setText("总收入金额");
		
		tableColumn_48 = new TableColumn(table_clrTable, SWT.NONE);
		tableColumn_48.setWidth(132);
		tableColumn_48.setText("总支出金额");
		
		tableColumn_51 = new TableColumn(table_clrTable, SWT.NONE);
		tableColumn_51.setWidth(120);
		tableColumn_51.setText("统计开始时间");
		
		tableColumn_52 = new TableColumn(table_clrTable, SWT.NONE);
		tableColumn_52.setWidth(111);
		tableColumn_52.setText("统计结束时间");
		
		tableColumn_53 = new TableColumn(table_clrTable, SWT.NONE);
		tableColumn_53.setWidth(140);
		tableColumn_53.setText("纯收入");
		
		Menu menu_7 = new Menu(table_clrTable);
		table_clrTable.setMenu(menu_7);
		
		menuItem_clrToTable = new MenuItem(menu_7, SWT.NONE);
		menuItem_clrToTable.setText("导出详情到Excel");
		
		composite_vipRcg = new Composite(composite_show, SWT.NONE);
	
		
		label_vipFunction = new Label(composite_vipRcg, SWT.NONE);
		label_vipFunction.setBounds(55, 58, 61, 17);
		label_vipFunction.setText("统计方式：");
		
		combo_vipSelect = new Combo(composite_vipRcg, SWT.NONE);
		combo_vipSelect.setBounds(122, 55, 96, 25);
		combo_vipSelect.add("按年统计");
		combo_vipSelect.add("按月统计");
		combo_vipSelect.add("按日统计");
		
		dateTime_vipTableTime = new DateTime(composite_vipRcg, SWT.BORDER|SWT.DROP_DOWN);
		dateTime_vipTableTime.setBounds(239, 55, 124, 24);
		
		button_vipFind = new Button(composite_vipRcg, SWT.NONE);
		
		button_vipFind.setBounds(394, 53, 80, 27);
		button_vipFind.setText("查询");
		
		table_vipTable = new Table(composite_vipRcg, SWT.BORDER | SWT.FULL_SELECTION);
		table_vipTable.setBounds(50, 117, 719, 436);
		table_vipTable.setHeaderVisible(true);
		table_vipTable.setLinesVisible(true);
		
		tableColumn_29 = new TableColumn(table_vipTable, SWT.NONE);
		tableColumn_29.setWidth(80);
		tableColumn_29.setText("会员编号");
		
		tableColumn_30 = new TableColumn(table_vipTable, SWT.NONE);
		tableColumn_30.setWidth(100);
		tableColumn_30.setText("会员名称");
		
		tableColumn_31 = new TableColumn(table_vipTable, SWT.NONE);
		tableColumn_31.setWidth(135);
		tableColumn_31.setText("手机号码");
		
		tableColumn_32 = new TableColumn(table_vipTable, SWT.NONE);
		tableColumn_32.setWidth(100);
		tableColumn_32.setText("充值金额");
		
		tableColumn_33 = new TableColumn(table_vipTable, SWT.NONE);
		tableColumn_33.setWidth(178);
		tableColumn_33.setText("充值时间");
		
		tableColumn_34 = new TableColumn(table_vipTable, SWT.NONE);
		tableColumn_34.setWidth(123);
		tableColumn_34.setText("操作员");
		
		Menu menu_vipToExcel = new Menu(table_vipTable);
		table_vipTable.setMenu(menu_vipToExcel);
		
		menuItem_vipToTable = new MenuItem(menu_vipToExcel, SWT.NONE);
		menuItem_vipToTable.setText("导出详情到Excel");
		
		composite_nVipRcg = new Composite(composite_show, SWT.NONE);
		
		label_NVipFunction = new Label(composite_nVipRcg, SWT.NONE);
		label_NVipFunction.setText("统计方式：");
		label_NVipFunction.setBounds(56, 38, 61, 17);
		
		combo_NvipSelect = new Combo(composite_nVipRcg, SWT.NONE);
		combo_NvipSelect.setBounds(123, 35, 96, 25);
		combo_NvipSelect.add("按年统计");
		combo_NvipSelect.add("按月统计");
		combo_NvipSelect.add("按日统计");
		
		dateTime_NvipTableTime = new DateTime(composite_nVipRcg, SWT.BORDER|SWT.DROP_DOWN);
		dateTime_NvipTableTime.setBounds(240, 35, 124, 24);
		
		button_NvipFind = new Button(composite_nVipRcg, SWT.NONE);
		
		button_NvipFind.setText("查询");
		button_NvipFind.setBounds(395, 33, 80, 27);
		
		table_NvipTableOfProduct = new Table(composite_nVipRcg, SWT.BORDER | SWT.FULL_SELECTION);
		table_NvipTableOfProduct.setLinesVisible(true);
		table_NvipTableOfProduct.setHeaderVisible(true);
		table_NvipTableOfProduct.setBounds(50, 305, 719, 204);
		
		tableColumn_36 = new TableColumn(table_NvipTableOfProduct, SWT.NONE);
		tableColumn_36.setWidth(123);
		tableColumn_36.setText("消费产品项目名称");
		
		tableColumn_37 = new TableColumn(table_NvipTableOfProduct, SWT.NONE);
		tableColumn_37.setWidth(95);
		tableColumn_37.setText("消费产品数量");
		
		tableColumn_38 = new TableColumn(table_NvipTableOfProduct, SWT.NONE);
		tableColumn_38.setWidth(110);
		tableColumn_38.setText("消费产品项目单价");
		
		tableColumn_39 = new TableColumn(table_NvipTableOfProduct, SWT.NONE);
		tableColumn_39.setWidth(95);
		tableColumn_39.setText("消费金额");
		
		tblclmnNewColumn_10 = new TableColumn(table_NvipTableOfProduct, SWT.NONE);
		tblclmnNewColumn_10.setText("消费产品时间");
		tblclmnNewColumn_10.setWidth(170);
		
		tblclmnNewColumn_11 = new TableColumn(table_NvipTableOfProduct, SWT.NONE);
		tblclmnNewColumn_11.setWidth(120);
		tblclmnNewColumn_11.setText("操作员");
		
		table_NvipTableOfRule = new Table(composite_nVipRcg, SWT.BORDER | SWT.FULL_SELECTION);
		table_NvipTableOfRule.setLinesVisible(true);
		table_NvipTableOfRule.setHeaderVisible(true);
		table_NvipTableOfRule.setBounds(50, 77, 719, 204);
		
		tableColumn_35 = new TableColumn(table_NvipTableOfRule, SWT.NONE);
		tableColumn_35.setWidth(123);
		tableColumn_35.setText("消费套餐项目名称");
		
		tableColumn_40 = new TableColumn(table_NvipTableOfRule, SWT.NONE);
		tableColumn_40.setWidth(95);
		tableColumn_40.setText("消费套餐数量");
		
		tableColumn_41 = new TableColumn(table_NvipTableOfRule, SWT.NONE);
		tableColumn_41.setWidth(110);
		tableColumn_41.setText("消费套餐单价");
		
		tableColumn_42 = new TableColumn(table_NvipTableOfRule, SWT.NONE);
		tableColumn_42.setWidth(95);
		tableColumn_42.setText("消费金额");
		
		tableColumn_43 = new TableColumn(table_NvipTableOfRule, SWT.NONE);
		tableColumn_43.setWidth(170);
		tableColumn_43.setText("消费套餐时间");
		
		tableColumn_44 = new TableColumn(table_NvipTableOfRule, SWT.NONE);
		tableColumn_44.setWidth(120);
		tableColumn_44.setText("操作员");
		
		
		btnexcel = new Button(composite_nVipRcg, SWT.NONE);
		btnexcel.setBounds(639, 532, 80, 27);
		btnexcel.setText("导出Excel");
		
		composite_cost = new Composite(composite_show, SWT.NONE);
		
		label_costFunction = new Label(composite_cost, SWT.NONE);
		label_costFunction.setText("统计方式：");
		label_costFunction.setBounds(55, 58, 61, 17);
		
		combo_costSelect = new Combo(composite_cost, SWT.NONE);
		combo_costSelect.setBounds(122, 55, 96, 25);
		combo_costSelect.add("按年统计");
		combo_costSelect.add("按月统计");
		combo_costSelect.add("按日统计");
		
		dateTime_costTableTime = new DateTime(composite_cost, SWT.BORDER |SWT.DROP_DOWN);
		dateTime_costTableTime.setBounds(239, 55, 124, 24);
		
		button_costFind = new Button(composite_cost, SWT.NONE);
		button_costFind.setText("查询");
		button_costFind.setBounds(394, 53, 80, 27);
		
		table_costTable = new Table(composite_cost, SWT.BORDER | SWT.FULL_SELECTION);
		table_costTable.setLinesVisible(true);
		table_costTable.setHeaderVisible(true);
		table_costTable.setBounds(50, 117, 719, 453);
		
		tableColumn_46 = new TableColumn(table_costTable, SWT.NONE);
		tableColumn_46.setWidth(270);
		tableColumn_46.setText("支出详情");
		
		tableColumn_47 = new TableColumn(table_costTable, SWT.NONE);
		tableColumn_47.setWidth(144);
		tableColumn_47.setText("支出金额");
		
		tableColumn_49 = new TableColumn(table_costTable, SWT.NONE);
		tableColumn_49.setWidth(183);
		tableColumn_49.setText("支出时间");
		
		tableColumn_50 = new TableColumn(table_costTable, SWT.NONE);
		tableColumn_50.setWidth(123);
		tableColumn_50.setText("操作员");
		
		Menu menu_6 = new Menu(table_costTable);
		table_costTable.setMenu(menu_6);
		
		menuItem_costToExcel = new MenuItem(menu_6, SWT.NONE);
		menuItem_costToExcel.setText("导出详情到Excel");
		
		logOut();

		updatePwd();

		statisticalForm();

		listenThing();

		vipOparetor();

		vipPay();

		pubPay();

		productShow();
	}

	//退出系统

	private void logOut(){
		//选择退出系统时
		button_21.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean bl=MessageDialog.openConfirm(shell, "温馨提示", "您确定要退出吗");
				if(bl){
					System.exit(0);
				}
			}
		});

		//选择否时
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = composite_show;
				composite_show.layout();
			}
		});
	}

	//修改密码
	private void updatePwd() {
		//对文本框判断
		text_newAgain.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String newAgain=text_newAgain.getText();
				if(newAgain==null||"".equals(newAgain)){
					SwtUtil.showMessageBox(shell, "提示", "确认密码不能为空！");
				}
			}
		});

		//判断密码强度
		text_new.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String newPwd=text_new.getText();
				if(newPwd.matches("^[a-zA-Z_0-9]{1,6}$")){
					label_weak.setText("弱");
					label_weak.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
					label_med.setText("");
					label_med.setBackground(null);
					label_str.setBackground(null);
					label_str.setText("");
				}
				else if(newPwd.matches("^[a-zA-Z_0-9]{7,10}$")){
					label_weak.setText("");
					label_str.setText("");
					label_weak.setBackground(null);
					label_str.setBackground(null);
					label_weak.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					label_med.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					label_med.setText("中");
				}
				else if(newPwd.matches("^[a-zA-Z_0-9]{11,16}$")){
					label_weak.setText("");
					label_med.setText("");
					label_weak.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
					label_med.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
					label_str.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
					label_str.setText("强");
				}
			}
		});

		//点击修改邮箱
		btnNewButton_updEmail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UpdateEmail tv=new UpdateEmail(shell, 0);
				tv.open();
				/*stackLayout.topControl = composite_updatePwd;
								composite_show.layout();*/
				try {
					userinfo.setName(UserinfoUtil.map.get("EMAIL").toString());
					Map<String, Object> map=userinfoDAO.findByEmail(userinfo);
					label_email.setText(map.get("EMAIL").toString());
				} catch (SQLException | IOException e2) {
					e2.printStackTrace();
					SwtUtil.showMessageBox(shell, "错误提示", e2.getMessage());
				}

			}
		});

		//判断文本框中的内容是否和数据库中的一致，如果一致，则允许修改，不一致则不允许修改
		text_old.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String uname=UserinfoUtil.map.get("NAME").toString();
				String oldPwd=text_old.getText();
				userinfo=new Userinfo();
				userinfo.setName(uname);
				userinfo.setPwd(oldPwd);
				try {
					Map<String, Object>map=userinfoDAO.login(userinfo);
					if (map != null && map.size() > 0) {
						label_old.setText("√");
					}else{
						SwtUtil.showMessageBox(shell, "错误提示", "密码与旧密码不匹配！");
					}
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		//提交修改密码
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name=UserinfoUtil.map.get("NAME").toString();
				String newpwd=text_new.getText();
				String surename=text_newAgain.getText();

				if(label_old.getText().equals("√")){
					if(newpwd.equals(surename)  ){
						Userinfo userinfo=new Userinfo();
						userinfo.setName(name);
						userinfo.setPwd(newpwd);
						try {
							boolean flag=userinfoDAO.updatePwd(userinfo);
							if(flag){
								SwtUtil.showMessageBox(shell, "温馨提示", "修改成功！请重新登录");
								shell.dispose();
								Login login=new Login();
								login.open();
							}else{
								SwtUtil.showMessageBox(shell, "温馨提示", "修改失败！");
								text_new.setText("");
								text_newAgain.setText("");
							}
						} catch (FileNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}

					}else{
						SwtUtil.showMessageBox(shell, "温馨提示", "两次输入的密码不相同，请重新输入");
					}
				}else{
					SwtUtil.showMessageBox(shell, "错误提示", "密码与旧密码不匹配！");
				}
			}
		});

		//返回
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = composite_show;
				composite_show.layout();
			}
		});
	}

	//统计报表
	private void statisticalForm() {
		//支出报表
		button_costFind.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table_costTable.removeAll();
				//按年份查询
				String option =combo_costSelect.getText();
				Map<String, String> timeMap=getStartAndEnd(option, dateTime_costTableTime);
				String start=timeMap.get("start");
				String end=timeMap.get("end");
				
				List<Map<String, Object>> listDetailsToTable =expendDAO.outputTable(start, end) ;
				
				if (null != listDetailsToTable && listDetailsToTable.size() > 0) {
					for (Map<String, Object> map : listDetailsToTable) {
						tableItem_1 = new TableItem(table_costTable, SWT.NONE);
						tableItem_1.setText(new String[] {
								map.get("EX_NAME").toString(),
								map.get("EX_MONEY").toString(), 
								map.get("EX_DATE").toString(),
								map.get("UNAME").toString()
						});
					}
					
					//导出
					menuItem_costToExcel.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							String option =combo_costSelect.getText();
							Map<String, String> timeMap=getStartAndEnd(option, dateTime_costTableTime);
							String start=timeMap.get("start");
							String end=timeMap.get("end");
							
							try {
								List<Map<String, Object>> listDetails = expendDAO.outputTable(start, end);
								List<Map<String,Object>> list=expendDAO.createTable(start,end);

								if(list.get(0).get("MONEY")==null){
									MessageUtil.promt(shell, "提示", "没有数据");
								}else{
									DirectoryDialog dd=new DirectoryDialog(shell,SWT.OPEN);
									//文件对话框
									@SuppressWarnings("unused")
									FileDialog  fd=new FileDialog(shell,SWT.OPEN);
									dd.setFilterPath(System.getProperty("user.home")+File.separator);
									String dirPath=dd.open();
									
									if(dirPath==null || "".equals(dirPath)){
										return ;
									}
									
									WritableWorkbook wwb = null;
//									Date date=new Date();
//									String time= date.getMinutes()+"";
									// 创建可写入的Excel工作簿    		根据所选的统计方式，生成不同的文件名
									
									String fileName="";
									if("按年统计".equals(option)){
										fileName = dirPath+File.separator+"支出年报表("+time+").xls";
									}else if("按月统计".equals(option)){
										fileName = dirPath+File.separator+"支出月报表("+time+").xls";
									}else if("按日统计".equals(option)){
										fileName = dirPath+File.separator+"支出日报表("+time+").xls";
									}
									
									File file=new File(fileName);
									if (!file.exists()) {
										file.createNewFile();
									}
									//以fileName为文件名来创建一个Workbook
									wwb = Workbook.createWorkbook(file);
									// 创建工作表
									WritableSheet ws = wwb.createSheet("Test Shee 1", 0);

									//要插入到的Excel表格的行号，默认从0开始
									jxl.write.Label labelid= new jxl.write.Label(0, 0, "支出详情"); 
									jxl.write.Label labelvpid= new jxl.write.Label(2, 0, "支出金额");
									jxl.write.Label labelVName= new jxl.write.Label(4, 0, "支出时间");
									jxl.write.Label labelMoney= new jxl.write.Label(6, 0, "操作人");
									
									ws.addCell(labelid);
									ws.addCell(labelvpid);
									ws.addCell(labelVName);
									ws.addCell(labelMoney);
									int j=0;
									for(j=0;j<listDetails.size();j++){
										
										jxl.write.Label labelVid= new jxl.write.Label(0, j+1,listDetails.get(j).get("EX_NAME")+"");
										jxl.write.Label labelVVid= new jxl.write.Label(2, j+1,listDetails.get(j).get("EX_MONEY")+"");
										jxl.write.Label labelMName= new jxl.write.Label(4, j+1,listDetails.get(j).get("EX_DATE")+"");
										jxl.write.Label labelVMoney= new jxl.write.Label(6, j+1, listDetails.get(j).get("UNAME")+"");

										ws.addCell(labelVid);
										ws.addCell(labelVVid);
										ws.addCell(labelMName);
										ws.addCell(labelVMoney);
										
									}
									while(j>=listDetails.size()){
										jxl.write.Label labelStartTime= new jxl.write.Label(0, listDetails.size()+2, "起始时间"); 
										jxl.write.Label labelEndTime= new jxl.write.Label(2, listDetails.size()+2, "结束时间");
										jxl.write.Label labelTime= new jxl.write.Label(4, listDetails.size()+2, "支出金额(总计)");  
										
										ws.addCell(labelStartTime);
										ws.addCell(labelEndTime);
										ws.addCell(labelTime);
										for (int i = 0; i < list.size(); i++) {
											jxl.write.Label labelStartTime_i= new jxl.write.Label(0, listDetails.size()+3,start);
											jxl.write.Label labelEndTime_i= new jxl.write.Label(2, listDetails.size()+3,end);
											jxl.write.Label labelCName_i= new jxl.write.Label(4, listDetails.size()+3, list.get(i).get("MONEY")+"");

											ws.addCell(labelCName_i);
											ws.addCell(labelStartTime_i);
											ws.addCell(labelEndTime_i);
										}
										j=0;
									}
									
									//写进文档
									wwb.write();
									if(ws.getColumns()>0){
										file.setReadOnly();
										MessageUtil.promt(shell, "温馨提示", "导出成功,位置为:"+fileName);
									}else{
										MessageUtil.promt(shell, "温馨提示", "导出失败");
									}
									// 关闭Excel工作簿对象
									wwb.close();
								}
								
							} catch (Exception e1) {
								e1.printStackTrace();
							} 
						}
					});
					
				}else{
					MessageUtil.promt(shell, "温馨提示", "没有数据");
				}
			}
		});
		
		//非会员消费查询和报表
		button_NvipFind.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table_NvipTableOfRule.removeAll();
				table_NvipTableOfProduct.removeAll();
				//按年份查询
				String option =combo_NvipSelect.getText();
				Map<String, String> timeMap=getStartAndEnd(option, dateTime_NvipTableTime);
				String start=timeMap.get("start");
				String end=timeMap.get("end");
				
				List<Map<String, Object>> listDetailsToTable = total_consumeDAO.findProduct(start, end);
				List<Map<String, Object>> listDetailsToTable2 = total_consumeDAO.findRule(start, end);
				ListSize=listDetailsToTable.size();
				ListSize2=listDetailsToTable2.size();
				
				if( (null == listDetailsToTable || ListSize <= 0) &&
						(null == listDetailsToTable2 || ListSize2 <= 0)){
					MessageUtil.promt(shell, "温馨提示", "没有数据");
				}else{
					if (null != listDetailsToTable && ListSize > 0) {
						for (Map<String, Object> map : listDetailsToTable) {
							tableItem_1 = new TableItem(table_NvipTableOfProduct, SWT.NONE);
							tableItem_1.setText(new String[] {
									map.get("PNAME").toString(),
									map.get("CS_NUM").toString(), 
									map.get("PRICE").toString(),
									map.get("MONEY").toString(),
									map.get("TC_DATE").toString(),
									map.get("UNAME").toString()
							});
						}
					}else{
						MessageUtil.promt(shell, "温馨提示", "在此阶段没有消费产品");
					}
					if (null != listDetailsToTable2 && ListSize2 > 0) {
						for (Map<String, Object> map : listDetailsToTable2) {
							TableItem tableItem = new TableItem(table_NvipTableOfRule, SWT.NONE);
							tableItem.setText(new String[] {
									map.get("RNAME").toString(),
									map.get("CNUM").toString(), 
									map.get("PUB_MONEY").toString(),
									map.get("MONEY").toString(),
									map.get("TC_DATE").toString(),
									map.get("UNAME").toString()
							});
						}
					}else{
						MessageUtil.promt(shell, "温馨提示", "在此阶段没有消费套餐");
					}
				}
			}
		});
		//导出非会员消费记录报表
		btnexcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String option =combo_NvipSelect.getText();
				Map<String, String> timeMap=getStartAndEnd(option, dateTime_NvipTableTime);
				String start=timeMap.get("start");
				String end=timeMap.get("end");
				String fileName="";
				
				try {
					//查看在当前时间段内消费的产品和套餐详情
					List<Map<String, Object>> listDetailsToTable = total_consumeDAO.findProduct(start, end);//这是产品
					List<Map<String, Object>> listDetailsToTable2 = total_consumeDAO.findRule(start, end);//这是套餐
					ListSize=listDetailsToTable.size();
					ListSize2=listDetailsToTable2.size();
					
					List<Map<String, Object>> list = total_consumeDAO.createTable(start, end);
					if((list.get(0).get("MONEY")==null)){
						MessageUtil.promt(shell, "提示", "没有数据");
					}else{
						DirectoryDialog dd=new DirectoryDialog(shell,SWT.OPEN);
						//文件对话框
						@SuppressWarnings("unused")
						FileDialog  fd=new FileDialog(shell,SWT.OPEN);
						dd.setFilterPath(System.getProperty("user.home")+File.separator);
						String dirPath=dd.open();
						
						if(dirPath==null || "".equals(dirPath)){
							return ;
						}
						
						WritableWorkbook wwb = null;
//						Date date=new Date();
//						String time= date.getMinutes()+"";
						// 创建可写入的Excel工作簿    		根据所选的统计方式，生成不同的文件名
					
						if("按年统计".equals(option)){
							fileName = dirPath+File.separator+"非会员消费年报表("+time+").xls";
						}else if("按月统计".equals(option)){
							fileName = dirPath+File.separator+"非会员消费月报表("+time+").xls";
						}else if("按日统计".equals(option)){
							fileName = dirPath+File.separator+"非会员消费日报表("+time+").xls";
						}
						
						File file=new File(fileName);
						if (!file.exists()) {
							file.createNewFile();
						}
						//以fileName为文件名来创建一个Workbook
						wwb = Workbook.createWorkbook(file);
						// 创建工作表
						WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
						
						//要插入到的Excel表格的行号，默认从0开始
						jxl.write.Label labelid= new jxl.write.Label(0, 0, "非会员消费项目名称"); 
						jxl.write.Label labelNvpid= new jxl.write.Label(2, 0, "非会员消费数量");
						jxl.write.Label labelNVName= new jxl.write.Label(4, 0, "非会员消费项目单价");
						jxl.write.Label labelNMoney= new jxl.write.Label(6, 0, "非会员消费金额");
						jxl.write.Label labelNPayTime= new jxl.write.Label(8, 0, "非会员消费时间");
						jxl.write.Label labelUser= new jxl.write.Label(10, 0, "操作人姓名");
						
						ws.addCell(labelid);
						ws.addCell(labelNvpid);
						ws.addCell(labelNVName);
						ws.addCell(labelNMoney);
						ws.addCell(labelNPayTime);
						ws.addCell(labelUser);
						
						
						int j=0;
						for(j=0;j<ListSize2;j++){
							jxl.write.Label labelNVid= new jxl.write.Label(0, j+1,listDetailsToTable2.get(j).get("RNAME")+"");
							jxl.write.Label labelNVVid= new jxl.write.Label(2, j+1,listDetailsToTable2.get(j).get("CNUM")+"");
							jxl.write.Label labelNMName= new jxl.write.Label(4, j+1,listDetailsToTable2.get(j).get("PUB_MONEY")+"");
							jxl.write.Label labelNVMoney= new jxl.write.Label(6, j+1, listDetailsToTable2.get(j).get("MONEY")+"");
							jxl.write.Label labelNVTime= new jxl.write.Label(8, j+1, listDetailsToTable2.get(j).get("TC_DATE")+"");
							jxl.write.Label labelUserName= new jxl.write.Label(10, j+1, listDetailsToTable2.get(j).get("UNAME")+"");

							ws.addCell(labelNVid);
							ws.addCell(labelNVVid);
							ws.addCell(labelNMName);
							ws.addCell(labelNVMoney);
							ws.addCell(labelNVTime);
							ws.addCell(labelUserName);
						}

						if(j>=ListSize2){
							
							int x=0;
							for(x=0;x<ListSize;x++){
								
								jxl.write.Label labelNVid= new jxl.write.Label(0, j+x+1,listDetailsToTable.get(x).get("PNAME")+"");
								jxl.write.Label labelNVVid= new jxl.write.Label(2, j+x+1,listDetailsToTable.get(x).get("CS_NUM")+"");
								jxl.write.Label labelNMName= new jxl.write.Label(4, j+x+1,listDetailsToTable.get(x).get("PRICE")+"");
								jxl.write.Label labelNVMoney= new jxl.write.Label(6, j+x+1, listDetailsToTable.get(x).get("MONEY")+"");
								jxl.write.Label labelNVTime= new jxl.write.Label(8, j+x+1, listDetailsToTable.get(x).get("TC_DATE")+"");
								jxl.write.Label labelUserName= new jxl.write.Label(10, j+x+1, listDetailsToTable.get(x).get("UNAME")+"");
								
								ws.addCell(labelNVid);
								ws.addCell(labelNVVid);
								ws.addCell(labelNMName);
								ws.addCell(labelNVMoney);
								ws.addCell(labelNVTime);
								ws.addCell(labelUserName);
								
							}
							if(x>=ListSize){//listDetailsToTable.size()+listDetailsToTable2.size()+3
								jxl.write.Label labelStartTime= new jxl.write.Label(0,listDetailsToTable.size()+listDetailsToTable2.size()+3, "起始时间"); 
								jxl.write.Label labelEndTime= new jxl.write.Label(2, listDetailsToTable.size()+listDetailsToTable2.size()+3, "结束时间");
								jxl.write.Label labelTime= new jxl.write.Label(4, listDetailsToTable.size()+listDetailsToTable2.size()+3, "非会员消费金额(总计)");  
								
								ws.addCell(labelStartTime);
								ws.addCell(labelEndTime);
								ws.addCell(labelTime);
								
								for (int i = 0; i < list.size(); i++) {
									jxl.write.Label labelStartTime_i= new jxl.write.Label(0, listDetailsToTable.size()+listDetailsToTable2.size()+4,start);
									jxl.write.Label labelEndTime_i= new jxl.write.Label(2, listDetailsToTable.size()+listDetailsToTable2.size()+4,end);
									jxl.write.Label labelCName_i= new jxl.write.Label(4, listDetailsToTable.size()+listDetailsToTable2.size()+4, list.get(i).get("MONEY")+"");

									ws.addCell(labelCName_i);
									ws.addCell(labelStartTime_i);
									ws.addCell(labelEndTime_i);
								}
							}
							x=0;
							j=0;
						}
						
						//写进文档
						wwb.write();
						if(ws.getColumns()>0){
							file.setReadOnly();
							MessageUtil.promt(shell, "温馨提示", "导出成功,位置为:"+ fileName);
						}else{
							MessageUtil.promt(shell, "温馨提示", "导出失败");
						}
						// 关闭Excel工作簿对象
						wwb.close();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//会员充值报表查询和导出
		button_vipFind.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table_vipTable.removeAll();
				//按年份查询
				String option =combo_vipSelect.getText();
				Map<String, String> timeMap=getStartAndEnd(option, dateTime_vipTableTime);
				String start=timeMap.get("start");
				String end=timeMap.get("end");
				List<Map<String, Object>> listDetailsToTable = pay_vipDAO.outputTable(start, end);
				ListSize=listDetailsToTable.size();
				if (null != listDetailsToTable && ListSize > 0) {
					for (Map<String, Object> map : listDetailsToTable) {
						tableItem_1 = new TableItem(table_vipTable, SWT.NONE);
						tableItem_1.setText(new String[] {
								map.get("VID").toString(),
								map.get("UNAME").toString(),
								map.get("MTEL").toString(), 
								map.get("CH_MONEY").toString(),
								map.get("CH_DATE").toString(),
								map.get("UNAME").toString()
						});
					}
						
					//导出
					menuItem_vipToTable.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							String option =combo_vipSelect.getText();
							Map<String, String> timeMap=getStartAndEnd(option, dateTime_vipTableTime);
							String start=timeMap.get("start");
							String end=timeMap.get("end");
							
							try {
								List<Map<String, Object>> listDetails = pay_vipDAO.outputTable(start, end);
								List<Map<String,Object>> list=pay_vipDAO.payCreateTable(start,end);

								if(list.get(0).get("MONEY")==null){
									MessageUtil.promt(shell, "提示", "没有数据");
								}else{
									DirectoryDialog dd=new DirectoryDialog(shell,SWT.OPEN);
									//文件对话框
									@SuppressWarnings("unused")
									FileDialog  fd=new FileDialog(shell,SWT.OPEN);
									dd.setFilterPath(System.getProperty("user.home")+File.separator);
									String dirPath=dd.open();
									
									if(dirPath==null || "".equals(dirPath)){
										return ;
									}
									
									WritableWorkbook wwb = null;
//									Date date=new Date();
//									String time= date.getMinutes()+"";
									// 创建可写入的Excel工作簿    		根据所选的统计方式，生成不同的文件名
									
									String fileName="";
									if("按年统计".equals(option)){
										fileName = dirPath+File.separator+"会员充值年报表("+time+").xls";
									}else if("按月统计".equals(option)){
										fileName = dirPath+File.separator+"会员充值月报表("+time+").xls";
									}else if("按日统计".equals(option)){
										fileName = dirPath+File.separator+"会员充值日报表("+time+").xls";
									}
									
									File file=new File(fileName);
									if (!file.exists()) {
										file.createNewFile();
									}
									//以fileName为文件名来创建一个Workbook
									wwb = Workbook.createWorkbook(file);
									// 创建工作表
									WritableSheet ws = wwb.createSheet("Test Shee 1", 0);

									//要插入到的Excel表格的行号，默认从0开始
									jxl.write.Label labelid= new jxl.write.Label(0, 0, "编号"); 
									jxl.write.Label labelvpid= new jxl.write.Label(1, 0, "会员编号");
									jxl.write.Label labelVName= new jxl.write.Label(2, 0, "会员姓名");
									jxl.write.Label labelMoney= new jxl.write.Label(3, 0, "充值余额");
									jxl.write.Label labelPayTime= new jxl.write.Label(4, 0, "充值具体时间");
									jxl.write.Label labelUser= new jxl.write.Label(6, 0, "操作人姓名");
									
									ws.addCell(labelid);
									ws.addCell(labelvpid);
									ws.addCell(labelVName);
									ws.addCell(labelMoney);
									ws.addCell(labelPayTime);
									ws.addCell(labelUser);
									int j=0;
									for(j=0;j<ListSize;j++){
										
										jxl.write.Label labelVid= new jxl.write.Label(0, j+1,listDetails.get(j).get("PID")+"");
										jxl.write.Label labelVVid= new jxl.write.Label(1, j+1,listDetails.get(j).get("VID")+"");
										jxl.write.Label labelMName= new jxl.write.Label(2, j+1,listDetails.get(j).get("MNAME")+"");
										jxl.write.Label labelVMoney= new jxl.write.Label(3, j+1, listDetails.get(j).get("CH_MONEY")+"");
										jxl.write.Label labelVTime= new jxl.write.Label(4, j+1, listDetails.get(j).get("CH_DATE")+"");
										jxl.write.Label labelUserName= new jxl.write.Label(6, j+1, listDetails.get(j).get("UNAME")+"");

										ws.addCell(labelVid);
										ws.addCell(labelVVid);
										ws.addCell(labelMName);
										ws.addCell(labelVMoney);
										ws.addCell(labelVTime);
										ws.addCell(labelUserName);
										
									}
									while(j>=listDetails.size()){
										jxl.write.Label labelStartTime= new jxl.write.Label(0, listDetails.size()+1, "起始时间"); 
										jxl.write.Label labelEndTime= new jxl.write.Label(2, listDetails.size()+1, "结束时间");
										jxl.write.Label labelTime= new jxl.write.Label(4, listDetails.size()+1, "会员充值金额(总计)");  
										
										ws.addCell(labelStartTime);
										ws.addCell(labelEndTime);
										ws.addCell(labelTime);
										for (int i = 0; i < list.size(); i++) {
											jxl.write.Label labelStartTime_i= new jxl.write.Label(0, listDetails.size()+2,start);
											jxl.write.Label labelEndTime_i= new jxl.write.Label(2, listDetails.size()+2,end);
											jxl.write.Label labelCName_i= new jxl.write.Label(4, listDetails.size()+2, list.get(i).get("MONEY")+"");

											ws.addCell(labelCName_i);
											ws.addCell(labelStartTime_i);
											ws.addCell(labelEndTime_i);
										}
										j=0;
									}
									
									//写进文档
									wwb.write();
									if(ws.getColumns()>0){
										file.setReadOnly();
										MessageUtil.promt(shell, "温馨提示", "导出成功,位置为:"+ fileName);
									}else{
										MessageUtil.promt(shell, "温馨提示", "导出失败");
									}
									// 关闭Excel工作簿对象
									wwb.close();
								}
								
							} catch (Exception e1) {
								e1.printStackTrace();
							} 
						}
					});	
					
				} else {
					MessageUtil.promt(shell, "温馨提示", "没有数据");
				}
			}
		});
		
		//纯收入报表
		button_clrFind.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table_clrTable.removeAll();
				//按年份查询
				String option =combo_clrSelect.getText();
				Map<String, String> timeMap=getStartAndEnd(option, dateTime_clrTableTime);
				String start=timeMap.get("start");
				String end=timeMap.get("end");
				
				//得到金额总收入
				try {
					Map<String, Object> listGetMoney = total_consumeDAO.getMoney(start, end);
					Map<String, Object> listCostMoney = expendDAO.getMoney(start, end);
					List<Map<String,Object>> listPayVip=pay_vipDAO.payCreateTable(start, end);
					
					String getMoney="";
					String costMoney="";
					String vipMoney="";
					float pureMoney=0;
					if(listPayVip.get(0).get("MONEY")!=null && !"".equals(listPayVip.get(0).get("MONEY"))){
						vipMoney=listPayVip.get(0).get("MONEY").toString();
					}else{
						vipMoney=0+"";
					}
					if ( listCostMoney.get("MONEY")!=null  && !"".equals(listCostMoney.get("MONEY"))  
							&&  listGetMoney.get("MONEY")!=null  && !"".equals(listGetMoney.get("MONEY"))  ) {
						getMoney= listGetMoney.get("MONEY").toString();
						costMoney= listCostMoney.get("MONEY").toString();
						
					}else if(listGetMoney.get("MONEY")==null &&
							(listCostMoney.get("MONEY")!=null  && !"".equals(listCostMoney.get("MONEY")) )){
						
						getMoney= 0+"";
						costMoney= listCostMoney.get("MONEY").toString();
						
					}else if (listCostMoney.get("MONEY")==null &&
							(listGetMoney.get("MONEY")!=null  && !"".equals(listGetMoney.get("MONEY")) )){
						getMoney= listGetMoney.get("MONEY").toString();
						costMoney= 0+"";
						
					}else{
						MessageUtil.promt(shell, "温馨提示", "没有数据");
						getMoney=  0+"";
						costMoney= 0+"";
					}
					
					BigDecimal gMoney=new BigDecimal(getMoney);
					BigDecimal vMpney=new BigDecimal(vipMoney);
					BigDecimal cMoney=new BigDecimal(costMoney);
					gMoney=gMoney.add(vMpney);
					pureMoney=gMoney.subtract(cMoney).floatValue();
					
					tableItem_1 = new TableItem(table_clrTable, SWT.NONE);
					tableItem_1.setText(new String[] {
							 gMoney.toString(),
							(String) costMoney,
							start,
							end,
							pureMoney+""
					});
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				//导出
				menuItem_clrToTable.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						
						String option =combo_clrSelect.getText();
						Map<String, String> timeMap=getStartAndEnd(option, dateTime_clrTableTime);
						String start=timeMap.get("start");
						String end=timeMap.get("end");
						
						try {
							
							Map<String, Object> listGetMoney = total_consumeDAO.getMoney(start, end);
							Map<String, Object> listCostMoney = expendDAO.getMoney(start, end);
							List<Map<String,Object>> listPayVip=pay_vipDAO.payCreateTable(start, end);
							
							String getMoney="";
							String costMoney="";
							String vipMoney="";
							float pureMoney=0;
							if(listPayVip.get(0).get("MONEY")!=null && !"".equals(listPayVip.get(0).get("MONEY"))){
								vipMoney=listPayVip.get(0).get("MONEY").toString();
							}else{
								vipMoney=0+"";
							}
							
							if ( listCostMoney.get("MONEY")!=null  && !"".equals(listCostMoney.get("MONEY"))  
									&&  listGetMoney.get("MONEY")!=null  && !"".equals(listGetMoney.get("MONEY"))  ) {
								getMoney= listGetMoney.get("MONEY").toString();
								costMoney= listCostMoney.get("MONEY").toString();
							}else if(listGetMoney.get("MONEY")==null &&
									(listCostMoney.get("MONEY")!=null  && !"".equals(listCostMoney.get("MONEY")) )){
								
								getMoney= 0+"";
								costMoney= listCostMoney.get("MONEY").toString();
								
							}else if (listCostMoney.get("MONEY")==null &&
									(listGetMoney.get("MONEY")!=null  && !"".equals(listGetMoney.get("MONEY")) )){
								getMoney= listGetMoney.get("MONEY").toString();
								costMoney= 0+"";
								
							}else{
								MessageUtil.promt(shell, "温馨提示", "没有数据");
								getMoney=  0+"";
								costMoney= 0+"";
							}
							
							BigDecimal gMoney=new BigDecimal(getMoney);
							BigDecimal vMpney=new BigDecimal(vipMoney);
							BigDecimal cMoney=new BigDecimal(costMoney);
							gMoney=gMoney.add(vMpney);
							pureMoney=gMoney.subtract(cMoney).floatValue();
							
							DirectoryDialog dd=new DirectoryDialog(shell,SWT.OPEN);
							//文件对话框
							@SuppressWarnings("unused")
							FileDialog  fd=new FileDialog(shell,SWT.OPEN);
							dd.setFilterPath(System.getProperty("user.home")+File.separator);
							String dirPath=dd.open();
							
							if(dirPath==null || "".equals(dirPath)){
								return ;
							}
								
							WritableWorkbook wwb = null;
//							Date date=new Date();
//							String time= date.getMinutes()+"";
							// 创建可写入的Excel工作簿    		根据所选的统计方式，生成不同的文件名
							
							String fileName="";
							if("按年统计".equals(option)){
								fileName = dirPath+File.separator+"纯利润年报表("+time+").xls";
							}else if("按月统计".equals(option)){
								fileName = dirPath+File.separator+"纯利润月报表("+time+").xls";
							}else if("按日统计".equals(option)){
								fileName = dirPath+File.separator+"纯利润日报表("+time+").xls";
							}
							
							File file=new File(fileName);
							if (!file.exists()) {
								file.createNewFile();
							}
							//以fileName为文件名来创建一个Workbook
							wwb = Workbook.createWorkbook(file);
							// 创建工作表
							WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
							
							//要插入到的Excel表格的行号，默认从0开始
							jxl.write.Label labelGetMoney= new jxl.write.Label(0, 0, "总收入金额");
							jxl.write.Label labelCostMoney= new jxl.write.Label(2, 0, "总支出金额");
							jxl.write.Label labelStartTime= new jxl.write.Label(4, 0, "统计开始时间"); 
							jxl.write.Label labelEndTime= new jxl.write.Label(6, 0, "统计结束时间");
							jxl.write.Label labelTime= new jxl.write.Label(8, 0, "纯收入金额(总计)");  

							ws.addCell(labelGetMoney);
							ws.addCell(labelCostMoney);
							ws.addCell(labelStartTime);
							ws.addCell(labelEndTime);
							ws.addCell(labelTime);
							
							jxl.write.Label labelGetMoney_i= new jxl.write.Label(0, 1,gMoney+"");
							jxl.write.Label labelCostMoney_i= new jxl.write.Label(2, 1,cMoney+"");
							jxl.write.Label labelStartTime_i= new jxl.write.Label(4, 1,start);
							jxl.write.Label labelEndTime_i= new jxl.write.Label(6, 1,end);
							jxl.write.Label labelCName_i= new jxl.write.Label(8, 1, pureMoney+"");

							ws.addCell(labelGetMoney_i);
							ws.addCell(labelCostMoney_i);
							ws.addCell(labelCName_i);
							ws.addCell(labelStartTime_i);
							ws.addCell(labelEndTime_i);
							
							//写进文档
							wwb.write();
							if(ws.getColumns()>0){
								MessageUtil.promt(shell, "温馨提示", "导出成功,位置为:"+fileName);
							}else{
								MessageUtil.promt(shell, "温馨提示", "导出失败");
							}
							// 关闭Excel工作簿对象
							wwb.close();
							
						}catch (Exception e1) {
							e1.printStackTrace();
						} 
						
					}
				});
			}
		});
	}

	//获取起始时间
	private Map<String, String> getStartAndEnd(String option,DateTime dateTime) {
		Map<String, String> map=new HashMap<String, String>();
		String start="";
		String end="";
		if(option!=null && !"".equals(option)){
			String month=(dateTime.getMonth()+1)+"";
			if("按年统计".equals(option)){
				start=dateTime.getYear()+"-01-01";
				end=(dateTime.getYear()+1)+"-01-01";
			}else if("按月统计".equals(option)){
				int year=dateTime.getYear();
				if(month.length()==1){
					month="0"+month;
				}
				start=year+"-"+month+"-01";
				end=year+"-"+month+"-30";
				if("02".equals(month)){
					boolean flags=TimeUtils.isRun(year);

					if(flags){
						end=end.substring(0, 4)+"-"+month+"-29";
					}else{
						end=end.substring(0, 4)+"-"+month+"-28";
					}
				}else{
					end=end.substring(0, 4)+"-"+month+"-30";
				}
			}else if("按日统计".equals(option)){
				if(month.length()==1){
					month="0"+month;
				}
				start=dateTime_vipTableTime.getYear()+"-"+month+"-"
						+dateTime_vipTableTime.getDay();
				end=dateTime_vipTableTime.getYear()+"-"+month+"-"
						+(dateTime_vipTableTime.getDay()+1);
			}
		}else{
			MessageUtil.promt(shell, "温馨提示", "您还没有选择统计方式");
		}
		
		map.put("start", start);
		map.put("end", end);
		return map;
	}

	private void productShow() {
		combo_vipmealtype.removeAll();
		combo_vipaugmentedproduct.removeAll();
		combo_mealtype.removeAll();
		combo_producttype.removeAll();
		// 会员收银消费套餐类型
		try {
			combo_vipmealtype.add("");
			List<Map<String, Object>> list = memberDAO.findAllRule();
			if (null != list && list.size() > 0) {
				for (Map<String, Object> map : list) {
					combo_vipmealtype.add(map.get("RNAME").toString());
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// 会员收银消费其他产品类型
		try {
			combo_vipaugmentedproduct.add("");
			List<Map<String, Object>> list = memberDAO.findAllProduct();
			if (null != list && list.size() > 0) {
				for (Map<String, Object> map : list) {
					combo_vipaugmentedproduct.add(map.get("PNAME").toString());
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// 非会员收银消费套餐类型
		try {
			combo_mealtype.add("");
			List<Map<String, Object>> list = memberDAO.findAllRule();
			if (null != list && list.size() > 0) {
				for (Map<String, Object> map : list) {
					combo_mealtype.add(map.get("RNAME").toString());
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// 非会员收银消费其他产品类型
		try {
			combo_producttype.add("");
			List<Map<String, Object>> list = memberDAO.findAllProduct();
			if (null != list && list.size() > 0) {
				for (Map<String, Object> map : list) {
					combo_producttype.add(map.get("PNAME").toString());
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		combo_vipmealtype.select(0);
		combo_vipaugmentedproduct.select(0);
		combo_mealtype.select(0);
		combo_producttype.select(0);

	}

	// 非会员消费结算
		private void pubPay() {
			// 非会员消费套餐添加
			button_addPubRule.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String rname = combo_mealtype.getText();
					String num = text_mealbuynum.getText();
					if ((rname.trim() == "" || rname.trim() == null)) {
						MessageUtil.promt(shell, "温馨提示", "请填写消费信息！！！");
					} else if ((rname.trim() != "" || rname.trim() != null)
							&& (num.trim() == "" || num.trim() == null)) {
						MessageUtil.promt(shell, "温馨提示", "套餐消费数量不能为空！！！");
					} else if ((rname.trim() != "" || rname.trim() != null)
							&& (num.trim() != "" || num.trim() != null)) {
						try {
							int rulenum = Integer.parseInt(num);
							TableItem tableItem_2 = new TableItem(table_rulemeal,SWT.NONE);
							List<Map<String, Object>> list = memberDAO.selectRnameNews(rulenum, rname);
							if (null != list && list.size() > 0) {
								for (Map<String, Object> map : list) {
									tableItem_2.setText(new String[] {
											map.get("RID").toString(),
											map.get("RNAME").toString(),
											map.get("PUB_MONEY").toString(), num,
											map.get("MONEY").toString() });
								}
								combo_mealtype.select(0);
								text_mealbuynum.setText("");
								int tableLength = table_rulemeal.getItemCount();
								String name = "table_rulemeal";
								selectAllPubMoney(name, tableLength);
							} else {
								MessageUtil.promt(shell, "温馨提示", "添加非会员消费记录失败");
							}
						} catch (Exception e1) {
							table_vipproductexpense.removeAll();
							MessageUtil.promt(shell, "温馨提示",
									"添加会员套餐消费记录失败！请填写正确的消费数量！！！");
						}
					}
				}
			});

			//非会员消费套餐记录删除
			menuItem_pubrulepense.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int i=table_rulemeal.getSelectionIndex();
					table_rulemeal.remove(i);
					int tableLength = table_rulemeal.getItemCount();
					String name = "table_rulemeal";
					updateAllMoney(name,tableLength);
				}
			});
			// 非会员附加产品消费添加
			button_addPubProduct.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String pname = combo_producttype.getText();
					String num = text_productbuynum.getText();
					if ((pname.trim() == "" || pname.trim() == null)) {
						MessageUtil.promt(shell, "温馨提示", "附加产品消费信息不能为空！！！");
					} else if ((pname.trim() != "" || pname.trim() != null)
							&& (num.trim() == "" || num.trim() == null)) {
						MessageUtil.promt(shell, "温馨提示", "附加产品消费数量不能为空！！！");
					} else if ((pname.trim() != "" || pname.trim() != null)
							&& (num.trim() != "" || num.trim() != null)) {
						try {
							int expensenum = Integer.parseInt(num);
							TableItem tableItem_3 = new TableItem(
									table_productmeal, SWT.NONE);
							List<Map<String, Object>> list = memberDAO
									.selectProductNew(expensenum, pname);
							if (null != list && list.size() > 0) {
								for (Map<String, Object> map : list) {
									tableItem_3.setText(new String[] {
											map.get("PID").toString(),
											map.get("PNAME").toString(),
											map.get("PRICE").toString(), num,
											map.get("MONEY").toString() });
								}
								combo_producttype.select(0);
								text_productbuynum.setText("");
								int tableLength = table_productmeal.getItemCount();
								String name = "table_productmeal";
								selectAllPubMoney(name, tableLength);

							} else {
								MessageUtil.promt(shell, "温馨提示", "添加非会员产品消费记录失败");
							}
						} catch (Exception e1) {
							table_vipproductexpense.removeAll();
							MessageUtil.promt(shell, "温馨提示",
									"添加非会员产品消费记录失败！请填写正确的消费数量！！！");
						}
					}
				}
			});

			//非会员消费附加产品记录删除
			menuItem_pubproductepsene.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int i=table_productmeal.getSelectionIndex();
					table_productmeal.remove(i);
					int tableLength = table_productmeal.getItemCount();
					String name = "table_productmeal";
					updateAllMoney(name,tableLength);
				}
			});
			//非会员结账
			button_publist.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String money;
					@SuppressWarnings("unused")
					String printStr = "";
					int len = label_puballmoney.getText().length();
					if (len != 0) {
						money = label_puballmoney.getText().substring(0, len - 1);
					} else {
						money = "0";
					}
					if (money.equals("0")) {
						MessageUtil.promt(shell, "温馨提示","请先进行消费再结账！！！");
					}else {
						// 添加消费总单
						try {
							int ys_money = Integer.parseInt(money);
							int sh_money = Integer.parseInt(money);
							int userid = Integer.parseInt(UserinfoUtil.map.get("ID").toString());
							@SuppressWarnings("unused")
							boolean flag = memberDAO.addTotalConsumes(ys_money, sh_money,userid);
						} catch (Exception e2) {
							MessageUtil.promt(shell, "温馨提示","添加非会员消费记录失败！消费记录不正确！！！");
						}
					}
					// 插入套餐详情表
					int num = table_rulemeal.getItemCount();
					if (num > 0) {
						TableItem item;
						for (int i = 0; i < num; i++) {
							item = table_rulemeal.getItem(i);
							String id = item.getText(0);
							String rname = item.getText(1);
							String pubmoney = item.getText(2);
							String number = item.getText(3);
							printStr+=id+"--"+rname+"--"+pubmoney+"--"+number+"\n";
							try {
								int rid = Integer.parseInt(id);
								int cnum = Integer.parseInt(number);
								@SuppressWarnings("unused")
								boolean flag1 = memberDAO.addConsume(rid,cnum);
							} catch (Exception e2) {
								MessageUtil.promt(shell, "温馨提示","添加非会员套餐消费记录失败！套餐消费记录不正确！！！");
							}
						}

						// 插入附加产品详情表
						int num2 = table_productmeal.getItemCount();
						if (num2 != 0) {
							TableItem items;
							for (int j = 0; j < num2; j++) {
								items = table_productmeal.getItem(j);
								String ids = items.getText(0);
								String pname = items.getText(1);
								String price = items.getText(2);
								String numbers = items.getText(3);
								printStr+=ids+"--"+pname+"--"+price+"--"+numbers+"\n";
								try {
									int pid = Integer.parseInt(ids);
									int cs_num = Integer.parseInt(numbers);
									boolean flag = memberDAO.addConsumeCP(pid, cs_num);
									if (flag) {
										try {
											@SuppressWarnings("unused")
											boolean flag1 = memberDAO.updateProductPnum(cs_num,pid);
										} catch (Exception e2) {
											MessageUtil.promt(shell, "温馨提示","添加非会员产品消费记录失败！产品库存修改不正确！！！");
										}
									}
								} catch (Exception e2) {
									MessageUtil.promt(shell, "温馨提示","添加非会员产品消费记录失败！附加产品消费记录不正确！！！");
								}
							}
						}
						String allMonet = label_puballmoney.getText();
						printStr+="消费总额：          "+allMonet+"\n";
						MessageBox mb = new MessageBox(shell,SWT.ICON_QUESTION | SWT.OK| SWT.CANCEL);
						mb.setText("提示");
						mb.setMessage("是否打印小票?");
						int rc = mb.open();
						if (e.doit == (rc == SWT.OK)) {
						//做打印小票事件
							//LocatePrint.getStr(printStr);
							MessageUtil.promt(shell, "温馨提示","功能暂不开放！");
						}
						////else if(e.doit == (rc == SWT.CANCEL)) {
							
				//	}
						
						MessageUtil.promt(shell, "温馨提示","非会员消费结算成功！！！");
						label_puballmoney.setText("");
						table_rulemeal.removeAll();
						table_productmeal.removeAll();
					}
				}
			});
		}

		// 会员消费结算
		private void vipPay() {

			// 会员验证
			button_viplogin.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String tel = text_vippaytel.getText();
					String pwd = text_vippaypwd.getText();
					List<Object> params = new ArrayList<Object>();
					params.add(tel);
					params.add(pwd);

					try {
						List<Map<String, Object>> list = memberDAO.memberLogin(params);
						if (null != list && list.size() > 0) {
							MessageUtil.promt(shell, "温馨提示", "登录成功");
							for (Map<String, Object> map : list) {
								label_viploginpaytel.setText(map.get("TEL").toString());
							}
							// 清楚页面上数据
							text_vippaytel.setText("");
							text_vippaypwd.setText("");
						} else {
							MessageUtil.promt(shell, "温馨提示", "登录失败，手机号码与密码不匹配！！！");
							text_vippaytel.setText("");
							text_vippaypwd.setText("");
						}

					} catch (SQLException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					} catch (IOException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					}
				}
			});

			// 会员套餐消费添加

			button_addvipexpense.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String rname = combo_vipmealtype.getText();
					String num = text_rulenum.getText();
					if ((rname.trim() == "" || rname.trim() == null)) {
						MessageUtil.promt(shell, "温馨提示", "请填写消费信息！！！");
					} else if ((rname.trim() != "" || rname.trim() != null)
							&& (num.trim() == "" || num.trim() == null)) {
						MessageUtil.promt(shell, "温馨提示", "套餐消费数量不能为空！！！");
					} else if ((rname.trim() != "" || rname.trim() != null)
							&& (num.trim() != "" || num.trim() != null)) {
						try {
							int rulenum = Integer.parseInt(num);
							TableItem tableItem_2 = new TableItem(table_vipruleexpense, SWT.NONE);
							List<Map<String, Object>> list = memberDAO.selectRnameNew(rulenum, rname);
							if (null != list && list.size() > 0) {
								for (Map<String, Object> map : list) {
									tableItem_2.setText(new String[] {
											map.get("RID").toString(),
											map.get("RNAME").toString(),
											map.get("MEM_MONEY").toString(), num,
											map.get("MONEY").toString() });
								}
								combo_vipmealtype.select(0);
								text_rulenum.setText("");
								int tableLength = table_vipruleexpense
										.getItemCount();
								String name = "table_vipruleexpense";
								selectAllVipMoney(name, tableLength);
							} else {
								MessageUtil.promt(shell, "温馨提示", "添加会员消费记录失败");
							}
						} catch (Exception e1) {
							table_vipproductexpense.removeAll();
							MessageUtil.promt(shell, "温馨提示",
									"添加会员套餐消费记录失败！请填写正确的消费数量！！！");
						}
					}

				}

			});

			// 会员产品消费添加
			button_addproductexpense.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String pname = combo_vipaugmentedproduct.getText();
					String num = text_expensenum.getText();
					if ((pname.trim() == "" || pname.trim() == null)) {
						MessageUtil.promt(shell, "温馨提示", "附加产品消费信息不能为空！！！");
					} else if ((pname.trim() != "" || pname.trim() != null)
							&& (num.trim() == "" || num.trim() == null)) {
						MessageUtil.promt(shell, "温馨提示", "附加产品消费数量不能为空！！！");
					} else if ((pname.trim() != "" || pname.trim() != null)
							&& (num.trim() != "" || num.trim() != null)) {
						try {
							int expensenum = Integer.parseInt(num);
							TableItem tableItem_3 = new TableItem(
									table_vipproductexpense, SWT.NONE);
							List<Map<String, Object>> list = memberDAO
									.selectProductNew(expensenum, pname);
							if (null != list && list.size() > 0) {
								for (Map<String, Object> map : list) {
									tableItem_3.setText(new String[] {
											map.get("PID").toString(),
											map.get("PNAME").toString(),
											map.get("PRICE").toString(), num,
											map.get("MONEY").toString() });
								}
								combo_vipaugmentedproduct.select(0);
								text_expensenum.setText("");
								int tableLength = table_vipproductexpense
										.getItemCount();
								String name = "table_vipproductexpense";
								selectAllVipMoney(name, tableLength);

							} else {
								MessageUtil.promt(shell, "温馨提示", "添加会员产品消费记录失败");
							}
						} catch (Exception e1) {
							MessageUtil.promt(shell, "温馨提示",
									"添加会员产品消费记录失败！请填写正确的消费数量！！！");
						}
					}
				}
			});

			//会员套餐消费记录删除
			menuItem_vipdeleterule.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int i=table_vipruleexpense.getSelectionIndex();
					table_vipruleexpense.remove(i);
					int tableLength = table_vipruleexpense.getItemCount();
					String name = "table_vipruleexpense";
					updateAllVipMoney(name,tableLength);
				}
			});

			//会员附加产品消费记录删除
			menuItem_vipdeleteproduct.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int i=table_vipproductexpense.getSelectionIndex();
					table_vipproductexpense.remove(i);
					int tableLength = table_vipproductexpense.getItemCount();
					String name = "table_vipproductexpense";
					updateAllVipMoney(name,tableLength);
				}
			});

			// 会员消费结算
			btnNewButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String tel = label_viploginpaytel.getText();
					String money;
					int len = lbl_vipallmoney.getText().length();
					if (len != 0) {
						money = lbl_vipallmoney.getText().substring(0, len - 1);
					} else {
						money = "0";
					}
					if (tel.length() <= 0) {
						MessageUtil.promt(shell, "温馨提示", "请先登录后在进行消费结算！！！");
					} else if (money.equals("0")) {
						MessageUtil.promt(shell, "温馨提示","请先进行消费再结账！！！");
					}else {
						try {
							int mon = Integer.parseInt(money);
							Map<String, Object> map = memberDAO.selectMemberMoney(tel);
							int mone = Integer.parseInt(map.get("MONEY").toString());
							if (mone >= mon) {
								// 添加消费总单
								try {
									int ys_money = Integer.parseInt(money);
									int sh_money = Integer.parseInt(money);
									int userid = Integer.parseInt(UserinfoUtil.map.get("ID").toString());
									@SuppressWarnings("unused")
									boolean flag = memberDAO.addTotalConsume(tel,ys_money, sh_money,userid);
								} catch (Exception e2) {
									MessageUtil.promt(shell, "温馨提示","添加会员消费记录失败！消费记录不正确！！！");
								}
								// 插入套餐详情表
								int num = table_vipruleexpense.getItemCount();
								if (num != 0) {
									TableItem item;
									for (int i = 0; i < num; i++) {
										item = table_vipruleexpense.getItem(i);
										String id = item.getText(0);
										String number = item.getText(3);
										try {
											int rid = Integer.parseInt(id);
											int cnum = Integer.parseInt(number);
											@SuppressWarnings("unused")
											boolean flag1 = memberDAO.addConsume(rid,cnum);
										} catch (Exception e2) {
											MessageUtil.promt(shell, "温馨提示","添加会员套餐消费记录失败！套餐消费记录不正确！！！");
										}
									}
								}
								// 插入附加产品详情表
								int num2 = table_vipproductexpense.getItemCount();
								if (num2 != 0) {
									TableItem items;
									for (int j = 0; j < num2; j++) {
										items = table_vipproductexpense.getItem(j);
										String ids = items.getText(0);
										String numbers = items.getText(3);
										try {
											int pid = Integer.parseInt(ids);
											int cs_num = Integer.parseInt(numbers);
											boolean flag = memberDAO.addConsumeCP(pid, cs_num);
											if (flag) {
												try {
													@SuppressWarnings("unused")
													boolean flag1 = memberDAO.updateProductPnum(cs_num,pid);
												} catch (Exception e2) {
													MessageUtil.promt(shell, "温馨提示","添加会员产品消费记录失败！产品库存修改不正确！！！");
												}
											}
										} catch (Exception e2) {
											MessageUtil.promt(shell, "温馨提示","添加会员产品消费记录失败！附加产品消费记录不正确！！！");
										}
									}
								}
								// 扣除余额
								try {
									boolean flag3 = memberDAO.updateMemberMoney(mon, tel);
									if (flag3) {
										MessageUtil.promt(shell, "温馨提示","会员消费结算成功！！！");
										label_viploginpaytel.setText("");
										lbl_vipallmoney.setText("");
										table_vipruleexpense.removeAll();
										table_vipproductexpense.removeAll();
									}else {
										MessageUtil.promt(shell, "温馨提示","会员消费结算失败！！！");
									}
								} catch (Exception e2) {
									MessageUtil.promt(shell, "温馨提示","添加会员产品消费记录失败！附加产品消费记录不正确！！！");
								}
							} else {
								MessageUtil.promt(shell, "温馨提示", "账户余额不足！请先充值！！！");
							}
						} catch (Exception e2) {
							MessageUtil.promt(shell, "温馨提示","会员消费结算失败！！！");
						}
					}
				}
			});
		}

		// 会员基本操作
		private void vipOparetor() {
			// 会员注册功能
			// 电话号码验证
			text_addviptel.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					String tel = text_addviptel.getText();
					if (!checkTel(tel)) {
						label_addviptel.setText("* 请输入正确的电话号码！！！");
					} else {
						label_addviptel.setText("");
					}
				}
			});
			// 会员密码验证
			text_addvippwd.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					String pwd = text_addvippwd.getText();
					if (pwd.trim() == "" || pwd == null) {
						label_addvippwd1.setText("* 密码不能为空！！！");
					} else if (pwd.trim().length() < 6) {
						label_addvippwd1.setText("* 密码不能少于6个字符！！！");
					} else {
						label_addvippwd1.setText("");
					}
				}
			});
			// 二次密码验证
			text_addvippwd2.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					String pwd = text_addvippwd.getText();
					String pwd2 = text_addvippwd2.getText();
					if (!pwd.equals(pwd2)) {
						label_addvippwd2.setText("* 两次密码输入不一致！！！");
					} else {
						label_addvippwd2.setText("");
					}
				}
			});
			// 会员注册
			button_vipReg.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String name = text_addvipname.getText();
					String tel = text_addviptel.getText();
					String pwd = text_addvippwd.getText();
					String pwd2 = text_addvippwd2.getText();
					String money = text_addvipmoney.getText();
					int userid = Integer.parseInt(UserinfoUtil.map.get("ID").toString());
					int error1 = label_addviptel.getText().length();
					int error2 = label_addvippwd1.getText().length();
					int error3 = label_addvippwd2.getText().length();
					if (money.trim() == "" || money == null) {
						money = "0";
					}
					if (pwd.trim() == "" || pwd == null || pwd2.trim() == ""
							|| pwd2 == null) {
						MessageUtil.promt(shell, "温馨提示", "请填写正确信息！！！");
					} else if (error1 > 0 || error2 > 0 || error3 > 0) {
						MessageUtil.promt(shell, "温馨提示", "请填写正确信息！！！");
					} else {
						List<Object> params = new ArrayList<Object>();
						params.add(name);
						params.add(tel);
						params.add(pwd);
						params.add(money);
						params.add(userid);
						try {
							boolean flag = memberDAO.addMember(params);
							if (flag) {
								if (Float.parseFloat(money) > 0) {
									boolean flag2 = memberDAO.addVipMoney(tel,Float.parseFloat(money),userid);
									if (flag2) {
										MessageUtil.promt(shell, "温馨提示", "注册成功");
										// 清楚页面上数据
										text_addvipname.setText("");
										text_addviptel.setText("");
										text_addvippwd.setText("");
										text_addvippwd2.setText("");
										text_addvipmoney.setText("");
									} else {
										MessageUtil.promt(shell, "温馨提示", "注册失败");
									}
								} else {
									MessageUtil.promt(shell, "温馨提示", "注册成功");
									// 清楚页面上数据
									text_addvipname.setText("");
									text_addviptel.setText("");
									text_addvippwd.setText("");
									text_addvippwd2.setText("");
									text_addvipmoney.setText("");
								}
							} else {
								MessageUtil.promt(shell, "温馨提示", "注册失败");
							}

						} catch (SQLException e1) {
							MessageUtil.promt(shell, "出错了", e1.getMessage());
						} catch (IOException e1) {
							MessageUtil.promt(shell, "出错了", e1.getMessage());
						}
					}
				}
			});

			// 会员信息查询
			button_vipSelectType.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String info = text_selectvipmes.getText().trim();
					// 表示文本框没有输入查询条件
					if (null == info || "".equals(info)) {
						// 第一种方法：提示错误信息
						// MessageUtil.promt(shell, "错误提示", "必须输入班级或学号信息");
						// 第二种方法：查看所有信息
						try {
							List<Map<String, Object>> list = memberDAO.findAllMember();
							table_vipmessage.removeAll();
							if (null != list && list.size() > 0) {
								for (Map<String, Object> map : list) {
									TableItem tableItem_2 = new TableItem(
											table_vipmessage, SWT.NONE);
									tableItem_2.setText(new String[] {
											map.get("ID").toString(),
											map.get("NAME").toString(),
											map.get("TEL").toString(),
											map.get("MONEY").toString(),
											map.get("REG_DATE").toString() });
								}
							} else {
								MessageUtil.promt(shell, "温馨提示", "无会员信息");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						return;
					}

					String type = combo_vipselectway.getText();
					if ("会员名称".equals(type.trim())) {
						// 根据会员名称查询
						try {
							List<Map<String, Object>> list = memberDAO.findMemberByName(info);
							table_vipmessage.removeAll();
							if (null != list && list.size() > 0) {
								for (Map<String, Object> map : list) {
									// System.out.println(list.size());
									TableItem tableItem_2 = new TableItem(
											table_vipmessage, SWT.NONE);
									tableItem_2.setText(new String[] {
											map.get("ID").toString(),
											map.get("NAME").toString(),
											map.get("TEL").toString(),
											map.get("MONEY").toString(),
											map.get("REG_DATE").toString() });
								}
							} else {
								MessageUtil.promt(shell, "温馨提示", "查无此会员名称信息");
							}
						} catch (SQLException e1) {
							LogUtil.logger.info(e1.getMessage() + new Date());
						} catch (IOException e1) {
							LogUtil.logger.info(e1.getMessage() + new Date());
						}
					} else if ("手机号码".equals(type.trim())) {
						// 根据手机号码进行查询
						try {
							List<Map<String, Object>> list = memberDAO
									.findMemberByTel(info);
							table_vipmessage.removeAll();
							if (null != list && list.size() > 0) {
								for (Map<String, Object> map : list) {
									TableItem tableItem_2 = new TableItem(
											table_vipmessage, SWT.NONE);
									tableItem_2.setText(new String[] {
											map.get("ID").toString(),
											map.get("NAME").toString(),
											map.get("TEL").toString(),
											map.get("MONEY").toString(),
											map.get("REG_DATE").toString() });
								}
							} else {
								MessageUtil.promt(shell, "温馨提示", "查无此会员名称信息");
							}
						} catch (SQLException e1) {
							LogUtil.logger.info(e1.getMessage() + new Date());
						} catch (IOException e1) {
							LogUtil.logger.info(e1.getMessage() + new Date());
						}
					}
				}
			});

			// 会员信息修改显示
			menuItem_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					TableItem[] item = table_vipmessage.getSelection();
					for (TableItem t : item) {
						// 获取到被选中的会员编号 id
						String id = t.getText(0);
						try {
							List<Map<String, Object>> list = memberDAO
									.findMemberById(id);
							if (null != list && list.size() > 0) {
								for (Map<String, Object> map : list) {
									text_12.setText(map.get("NAME").toString());
									text_13.setText(map.get("TEL").toString());
								}
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			});

			// 密码验证
			// 新密码验证
			text_vipnewpwd.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					String newPwd = text_vipnewpwd.getText();
					if (newPwd.trim() == "" || newPwd == null) {
						label_vipnewpwd.setText("* 密码不能为空！！！");
					} else if (newPwd.trim().length() < 6) {
						label_vipnewpwd.setText("* 密码不能少于6个字符！！！");
					} else {
						label_vipnewpwd.setText("");
					}
				}
			});
			// 确认密码验证
			text_vipnewpwd2.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					String newPwd = text_vipnewpwd.getText();
					String newPwd2 = text_vipnewpwd2.getText();
					if (!newPwd.equals(newPwd2)) {
						label_vipnewpwd2.setText("* 两次密码输入不一致！！！");
					} else {
						label_vipnewpwd2.setText("");
					}
				}
			});

			// 会员密码修改
			button_vipPwdUpdate.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String pwd = text_vipnewpwd.getText();
					String pwd2 = text_vipnewpwd2.getText();
					String tel = text_13.getText();
					int error1 = label_vipnewpwd.getText().length();
					int error2 = label_vipnewpwd2.getText().length();
					if (pwd.trim() == "" || pwd == null || pwd2.trim() == ""
							|| pwd2 == null) {
						MessageUtil.promt(shell, "温馨提示", "请填写正确信息！！！");
					} else if (error1 > 0 || error2 > 0) {
						MessageUtil.promt(shell, "温馨提示", "请填写正确信息！！！");
					} else {
						List<Object> params = new ArrayList<Object>();
						params.add(pwd);
						params.add(tel);
						try {
							boolean flag = memberDAO.updateMemberPwd(params);
							if (flag) {
								MessageUtil.promt(shell, "温馨提示", "修改成功");
								// 清楚页面上数据
								text_vipnewpwd.setText("");
								text_vipnewpwd2.setText("");
							} else {
								MessageUtil.promt(shell, "温馨提示", "修改失败");
							}
						} catch (SQLException e1) {
							MessageUtil.promt(shell, "出错了", e1.getMessage());
						} catch (IOException e1) {
							MessageUtil.promt(shell, "出错了", e1.getMessage());
						}
					}
				}
			});

			// 会员余额充值
			button_vipAddMoney.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String name = text_payvipname.getText();
					String tel = text_payviptel.getText();
					String money = text_payvipmoney.getText();
					int userid = Integer.parseInt(UserinfoUtil.map.get("ID").toString());
					if (money.trim() == "" || money == null) {
						money = "0";
					}
					try {
						Float mon = Float.parseFloat(money);
						boolean flag = memberDAO.updeteMemberMoney(mon, name, tel);
						if (flag) {
							boolean flag2 = memberDAO.payMemberMoney(name, tel, mon,userid);
							if (flag2) {
								MessageUtil.promt(shell, "温馨提示", "充值成功");
								// 清楚页面上数据
								text_payvipname.setText("");
								text_payviptel.setText("");
								text_payvipmoney.setText("");
							} else {
								MessageUtil.promt(shell, "温馨提示", "充值失败");
							}
						} else {
							MessageUtil.promt(shell, "温馨提示", "充值失败");
						}

					} catch (Exception e1) {
						MessageUtil.promt(shell, "出错了", "请填写正确的充值金额！！！");
					}
				}
			});

			// 会员消费记录查询
			button_comsunSelectType.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					table_viprulebuymes.removeAll();
					table_vipproductbuymes.removeAll();
					String info = text_selectvipbuymes.getText().trim();
					// 表示文本框没有输入查询条件
					if (null == info || "".equals(info)) {
						MessageUtil.promt(shell, "温馨提示", "请输入查询条件！！！");
					}
					String type = combo_expenseselectway.getText();
					// 根据会员名称查询
					if ("会员名称".equals(type.trim())) {
						try {
							boolean flag = memberDAO.findMemberBuyByName(info);
							if (flag) {
								//套餐消费记录
								try {
									List<Map<String, Object>> list = memberDAO.findMemberRuleBuyByName(info);
									if (null != list && list.size() > 0) {
										for (Map<String, Object> map : list) {
											TableItem tableItem_2 = new TableItem(table_viprulebuymes, SWT.NONE);
											tableItem_2.setText(new String[] {
													map.get("ID").toString(),
													map.get("NAME").toString(),
													map.get("RNAME").toString(),
													map.get("MEM_MONEY").toString(),
													map.get("CNUM").toString(),
													map.get("ALLMONEY").toString(),
													map.get("TC_DATE").toString(),
													map.get("USNAME").toString() });
										}
									}
								} catch (SQLException e1) {
									LogUtil.logger.info(e1.getMessage() + new Date());
								} catch (IOException e1) {
									LogUtil.logger.info(e1.getMessage() + new Date());
								}
								//产品消费记录
								try {
									List<Map<String, Object>> list = memberDAO.findMemberProductBuyByName(info);
									if (null != list && list.size() > 0) {
										for (Map<String, Object> map : list) {
											TableItem tableItem_2 = new TableItem(table_vipproductbuymes, SWT.NONE);
											tableItem_2.setText(new String[] {
													map.get("ID").toString(),
													map.get("NAME").toString(),
													map.get("PNAME").toString(),
													map.get("PRICE").toString(),
													map.get("CS_NUM").toString(),
													map.get("ALLMONEY").toString(),
													map.get("TC_DATE").toString(),
													map.get("USNAME").toString() });
										}
									}
								} catch (SQLException e1) {
									LogUtil.logger.info(e1.getMessage() + new Date());
								} catch (IOException e1) {
									LogUtil.logger.info(e1.getMessage() + new Date());
								}
							}else {
								MessageUtil.promt(shell, "温馨提示", "查无此会员消费记录");
							}
						} catch (Exception e2) {
							MessageUtil.promt(shell, "温馨提示", "查无此会员消费记录");
						}

					} else if ("手机号码".equals(type.trim())) {
						// 根据手机号码进行查询
						try {
							boolean flag = memberDAO.findMemberBuyByTel(info);
							if (flag) {
								//套餐消费记录
								try {
									List<Map<String, Object>> list = memberDAO.findMemberRuleBuyByTel(info);
									table_viprulebuymes.removeAll();
									if (null != list && list.size() > 0) {
										for (Map<String, Object> map : list) {
											TableItem tableItem_2 = new TableItem(table_viprulebuymes, SWT.NONE);
											tableItem_2.setText(new String[] {
													map.get("ID").toString(),
													map.get("NAME").toString(),
													map.get("RNAME").toString(),
													map.get("MEM_MONEY").toString(),
													map.get("CNUM").toString(),
													map.get("ALLMONEY").toString(),
													map.get("TC_DATE").toString(),
													map.get("USNAME").toString() });
										}
									}
								} catch (SQLException e1) {
									LogUtil.logger.info(e1.getMessage() + new Date());
								} catch (IOException e1) {
									LogUtil.logger.info(e1.getMessage() + new Date());
								}
								//产品消费记录
								try {
									List<Map<String, Object>> list = memberDAO.findMemberProductBuyByTel(info);
									table_vipproductbuymes.removeAll();
									if (null != list && list.size() > 0) {
										for (Map<String, Object> map : list) {
											TableItem tableItem_2 = new TableItem(table_vipproductbuymes, SWT.NONE);
											tableItem_2.setText(new String[] {
													map.get("ID").toString(),
													map.get("NAME").toString(),
													map.get("PNAME").toString(),
													map.get("PRICE").toString(),
													map.get("CS_NUM").toString(),
													map.get("ALLMONEY").toString(),
													map.get("TC_DATE").toString(),
													map.get("USNAME").toString() });
										}
									}
								} catch (SQLException e1) {
									LogUtil.logger.info(e1.getMessage() + new Date());
								} catch (IOException e1) {
									LogUtil.logger.info(e1.getMessage() + new Date());
								}
							}else {
								MessageUtil.promt(shell, "温馨提示", "查无此会员消费记录");
							}
						} catch (Exception e2) {
							MessageUtil.promt(shell, "温馨提示", "查无此会员消费记录");
						}
					}
				}
			});
		}
		
		// 手机号码验证方法
		private boolean checkTel(String tel) {
			Pattern pattern = Pattern
					.compile("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))\\d{8})$");// ^1[3|4|5|7|8][0-9]\\d{4,8}$
			Matcher matcher = pattern.matcher(tel);
			if (matcher.matches()) {
				return true;
			}
			return false;
		}

		private void listenThing() {
			tree.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					TreeItem item = (TreeItem) e.item;
					String str = item.getText().trim();
					choicePanul(str);
				}
			});

			menuItem_tc.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					TableItem[] item = table_tc.getSelection();
					for (TableItem t : item) {
						String rid = t.getText(0);
						String rname = t.getText(1);
						String rnote = t.getText(2);
						String money_vip = t.getText(3);
						String money_pub = t.getText(4);
						String[] str = new String[] { rname, rnote, money_vip, money_pub,rid};
						TcModifyView tf = new TcModifyView(shell, SWT.NONE);
						tf.open(str);
						while(tf.shell.isDisposed()){
							tableflush.RuleSelect(table_tc);
							return;
						}
					}

				}
			});

			menuItem_ku.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					TableItem[] item = table_ku.getSelection();
					for (TableItem t : item) {
						String pid = t.getText(0);
						String pname = t.getText(1);
						String ptype = t.getText(3);
						String price = t.getText(4);
						String[] str = new String[] { pname, ptype, price,pid};
						KuModifyView tf = new KuModifyView(shell, SWT.NONE);
						tf.open(str);
						while(tf.shell.isDisposed()){
							tableflush.KuSelect(table_ku);
							return;
						}
					}
				}
			});
		}

		// 会员消费总额
		private void selectAllVipMoney(String name, int num) {
			TableItem item;
			String money;
			int len = lbl_vipallmoney.getText().length();
			if (len != 0) {
				money = lbl_vipallmoney.getText().substring(0, len - 1);
			} else {
				money = "0";
			}
			int count = Integer.parseInt(money);
			if (name.equals("table_vipproductexpense")) {
				item = table_vipproductexpense.getItem(num - 1);
				String col = item.getText(4);
				count += Integer.parseInt(col);
			} else if (name.equals("table_vipruleexpense")) {
				item = table_vipruleexpense.getItem(num - 1);
				String col = item.getText(4);
				count += Integer.parseInt(col);
			}
			lbl_vipallmoney.setText(count + "元");
		}

		// 非会员消费总额
		private void selectAllPubMoney(String name, int num) {
			TableItem item;
			String money;
			int len = label_puballmoney.getText().length();
			if (len != 0) {
				money = label_puballmoney.getText().substring(0, len - 1);
			} else {
				money = "0";
			}
			int count = Integer.parseInt(money);
			if (name.equals("table_productmeal")) {
				item = table_productmeal.getItem(num - 1);
				String col = item.getText(4);
				count += Integer.parseInt(col);
			} else if (name.equals("table_rulemeal")) {
				item = table_rulemeal.getItem(num - 1);
				String col = item.getText(4);
				count += Integer.parseInt(col);
			}
			label_puballmoney.setText(count + "元");
		}

		//会员消费总额修改
		private void updateAllVipMoney(String name, int num) {
			TableItem item;
			int count = 0;
			if (name.equals("table_vipproductexpense")) {
				for (int i = 0; i < num; i++) {
					item = table_vipproductexpense.getItem(i);
					String col = item.getText(4);
					count += Integer.parseInt(col);
				}
				for (int i = 0; i < table_vipruleexpense.getItemCount(); i++) {
					item = table_vipruleexpense.getItem(i);
					String col = item.getText(4);
					count += Integer.parseInt(col);
				}
			} else if (name.equals("table_vipruleexpense")) {
				for (int i = 0; i < num; i++) {
					item = table_vipruleexpense.getItem(i);
					String col = item.getText(4);
					count += Integer.parseInt(col);
				}
				for (int i = 0; i < table_vipproductexpense.getItemCount(); i++) {
					item = table_vipproductexpense.getItem(i);
					String col = item.getText(4);
					count += Integer.parseInt(col);
				}
			}
			lbl_vipallmoney.setText(count + "元");
		}

		//非会员消费总额修改
		private void updateAllMoney(String name, int num) {
			TableItem item;
			int count = 0;
			if (name.equals("table_productmeal")) {
				for (int i = 0; i < num; i++) {
					item = table_productmeal.getItem(i);
					String col = item.getText(4);
					count += Integer.parseInt(col);
				}
				for (int i = 0; i < table_rulemeal.getItemCount(); i++) {
					item = table_rulemeal.getItem(i);
					String col = item.getText(4);
					count += Integer.parseInt(col);
				}
			} else if (name.equals("table_rulemeal")) {
				for (int i = 0; i < num; i++) {
					item = table_rulemeal.getItem(i);
					String col = item.getText(4);
					count += Integer.parseInt(col);
				}
				for (int i = 0; i < table_productmeal.getItemCount(); i++) {
					item = table_productmeal.getItem(i);
					String col = item.getText(4);
					count += Integer.parseInt(col);
				}
			}
			label_puballmoney.setText( count+"元");
		}
	

	/* 选择切换面板 */
	private void choicePanul(String str) {
		if (str.equalsIgnoreCase("会员收费")) {
			stackLayout.topControl = composite_vippay;
			composite_show.layout();
			table_vipproductexpense.removeAll();
			table_vipruleexpense.removeAll();
			lbl_vipallmoney.setText("");
		} else if (str.equalsIgnoreCase("非会员收费")) {
			stackLayout.topControl = composite_pubpay;
			composite_show.layout();
			table_rulemeal.removeAll();
			table_productmeal.removeAll();
			label_puballmoney.setText("");
		} else if (str.equalsIgnoreCase("会员信息添加")) {
			stackLayout.topControl = composite_addvip;
			composite_show.layout();
			text_addvipname.setText("");
			text_addviptel.setText("");
			text_addvippwd.setText("");
			text_addvippwd2.setText("");
			text_addvipmoney.setText("");
			label_addviptel.setText("");
			label_addvippwd1.setText("");
			label_addvippwd2.setText("");
		} else if (str.equalsIgnoreCase("会员信息查询")) {
			stackLayout.topControl = composite_selectvip;
			composite_show.layout();
			combo_vipselectway.select(0);
			text_selectvipmes.setText("");
			table_vipmessage.removeAll();
			text_vipnewpwd.setText("");
			text_vipnewpwd2.setText("");
			text_12.setText("");
			text_13.setText("");
			label_vipnewpwd.setText("");
			label_vipnewpwd2.setText("");
		} else if (str.equalsIgnoreCase("会员充值管理")) {
			stackLayout.topControl = composite_payvip;
			composite_show.layout();
			text_payvipname.setText("");
			text_payviptel.setText("");
			text_payvipmoney.setText("");
		} else if (str.equalsIgnoreCase("消费信息查询")) {
			stackLayout.topControl = composite_consumrecord;
			composite_show.layout();
			table_viprulebuymes.removeAll();
			table_vipproductbuymes.removeAll();
			text_selectvipbuymes.setText("");
			combo_expenseselectway.select(0);
		}else if(str.equalsIgnoreCase("修改密码和邮箱")){
			stackLayout.topControl = composite_updatePwd;
			composite_show.layout();
		} else if(str.equalsIgnoreCase("退出系统")){
			stackLayout.topControl = composite_logOff;
			composite_show.layout();
		}else if(str.equalsIgnoreCase("会员充值报表")){
			stackLayout.topControl = composite_vipRcg;
			composite_show.layout();
			table_vipTable.removeAll();
		}else if(str.equalsIgnoreCase("非会员消费报表")){
			stackLayout.topControl = composite_nVipRcg;
			composite_show.layout();
			table_NvipTableOfRule.removeAll();
			table_NvipTableOfProduct.removeAll();
		}else if(str.equalsIgnoreCase("支出报表")){
			stackLayout.topControl = composite_cost;
			composite_show.layout();
			table_costTable.removeAll();
		}else if(str.equalsIgnoreCase("纯收入报表")){
			stackLayout.topControl = composite_clr;
			composite_show.layout();
			table_clrTable.removeAll();
		} else if (str.equalsIgnoreCase("查看套餐信息")) {
			stackLayout.topControl = composite_rule;
			composite_show.layout();
			tableflush.RuleSelect(table_tc);
		}else if (str.equalsIgnoreCase("添加套餐信息")) {
			TcAddView ta = new TcAddView(shell, SWT.NONE);
			ta.open();
			while(ta.shell.isDisposed()){
				tableflush.RuleSelect(table_tc);
				productShow();
				return;
			}
		} else if (str.equalsIgnoreCase("库存查看")) {
			stackLayout.topControl = composite_ku;
			composite_show.layout();
			tableflush.KuSelect(table_ku);
		} else if (str.equalsIgnoreCase("产品进货")) {
			KuAddView ka = new KuAddView(shell, SWT.NONE);
			ka.open();
			while(ka.shell.isDisposed()){
				tableflush.KuSelect(table_ku);
				productShow();
				return;
			}
		} else if (str.equalsIgnoreCase("其他支出")) {
			ExAddView ea = new ExAddView(shell, SWT.NONE);
			ea.open();
			while(ea.shell.isDisposed()){
				return;
			}
		}
	}
}
