package com.psl.sm.utils;
//


import java.awt.Font;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

@SuppressWarnings("serial")
public class JFreeChartMonth extends ApplicationFrame{
	public static Map<String, String> map;

	@SuppressWarnings("static-access")
	public JFreeChartMonth(String title,Map<String, String> map){
		super(title);
		this.map=map;
		this.setContentPane(createPanel()); //构造函数中自动创建Java的panel面板

	}
	
	public static CategoryDataset setValue(Map<String, String> map){
		DefaultCategoryDataset
		
		dataset=new DefaultCategoryDataset();
		Set<String> key=map.keySet();
		for (String k : key) {
			dataset.addValue(Integer.parseInt(map.get(k)), k, k);
		}
		return dataset;
	}

	public static JFreeChart createChart(CategoryDataset dataset){ //用数据库创建一个图表
		JFreeChart chart=ChartFactory.createBarChart("报表","时间","金额",dataset,PlotOrientation.VERTICAL, true, true, false); //创建一个JFreeChart
		chart.setTitle(new TextTitle("报表",new Font("黑体",Font.BOLD+Font.ITALIC,20)));//可以重新设置标题，替换“hi”标题
		CategoryPlot plot=(CategoryPlot)chart.getPlot();//获得图标中间部分，即plot
		CategoryAxis categoryAxis=plot.getDomainAxis();//获得横坐标
		categoryAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));//设置横坐标字体
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//设置角度
        
        NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis();
        numberAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));
		return chart;
	}

	public static JPanel createPanel(){
		JFreeChart chart =createChart(setValue(map));
		return new ChartPanel(chart); //将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
	}

	public void windowClosing(WindowEvent event) {
		this.setVisible(false);
	}
	

//	public static void main(String[] args){
//		Map<String, String> map=new HashMap<String, String>();
//		map.put("a", "10");
//		map.put("b", "20");
//		map.put("c", "30");
//		JFreeChartDay chart=new JFreeChartDay("某公司组织结构图",map);
//		chart.pack();//以合适的大小显示
//		chart.setVisible(true);
//	}
}