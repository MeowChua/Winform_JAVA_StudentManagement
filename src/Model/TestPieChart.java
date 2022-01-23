/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author TVD
 */
public class TestPieChart {
  String s;
    public TestPieChart(int y, int tb , int k , int g , int xs,String s){
         JFreeChart pieChart = createChart(createDataset(y,tb,k,g,xs),s);
        ChartPanel chartPanel = new ChartPanel(pieChart);
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        this.s=s;
    }

    public String getS() {
        return s;
    }

    private static JFreeChart createChart(PieDataset dataset,String s) {
       String a ="Phân Loại Điểm Học Tập  Môn ";
       String b= a.concat(s);
        JFreeChart chart = ChartFactory.createPieChart(
                b , dataset, true, true, true);
        return chart;
    }

    private static PieDataset createDataset(double y , double tb , double k , double g , double xs) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Nhóm HS Khá(>6.5)", new Double(k));
        dataset.setValue("Nhóm HS Yếu(<5)", new Double(y));
        dataset.setValue("Nhóm HS Trung Bình (>5 && <6.5)", new Double(tb));
        dataset.setValue("Nhóm HS Giỏi (> 7.9)",new Double(g));
        dataset.setValue("Nhóm HS Xuất Sắc (> 8.9)",new Double(xs));
        return dataset;
    }

   

}

