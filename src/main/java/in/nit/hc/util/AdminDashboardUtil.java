package in.nit.hc.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class AdminDashboardUtil {
	
	public void generatePie(String path, List<Object []> list) {
		//1. create dataset using List
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(Object[] ob : list) {
						  //key-lable/status //val-data/count  
 			dataset.setValue(ob[0].toString(),Double.valueOf(ob[1].toString()));  
		}
		
		//2. create JFreeChart using Dataset and ChartFactor
		 JFreeChart chart = ChartFactory.createPieChart("SLOT DATA ADMIN", dataset);
		 
		//3. convert as Image
		 try {
			 //file, chart, width, height
			ChartUtils.saveChartAsJPEG(
					new File(path+"/adminA.jpg"),
					chart,
					300,
					300
					);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void generateBar(String path, List<Object[]> list) {
		//1. create dataset
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Object[] ob : list) {
			//value, rowKey, columnKey
			dataset.setValue(
					Double.valueOf(ob[1].toString()),
					ob[0].toString(),
					""
						);
		}
		
		//2. create JFreeChart from ChartFactory using Dataset and List 
					//title, categoryAxisLabel or x-Axis, valueAxisLabel or y-Axis, dataset
		JFreeChart chart = ChartFactory.createBarChart(
											"SLOT DATA AS BAR FOR ADMIN",
											"SLOT",
											"COUNT",
											dataset
													  );
		//3. convert as image
		try {
			//file, chart, width, height
			ChartUtils.saveChartAsJPEG(new File(path+"/adminB.jpg"),chart,300,300);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}































