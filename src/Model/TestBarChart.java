/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author TVD
 */
public class TestBarChart {

    public TestBarChart(){
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    
    public static JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ SỐ LƯỢNG HỌC SỊNH ĐẠT CHỈ TIÊU THEO TỪNG NĂM",
                "Năm", "HS đạt chỉ tiêu",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private static CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(3500, "HS đạt chỉ tiêu", "2018");
        dataset.addValue(4500, "HS đạt chỉ tiêu", "2019");
        dataset.addValue(5500, "HS đạt chỉ tiêu", "2020");
        dataset.addValue(6500, "HS đạt chỉ tiêu", "2021");
        return dataset;
    }
    
}

