package RotLA;

import java.io.*;
import java.util.ArrayList;

import org.jfree.chart.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ChartUtilities;
import org.jfree.data.category.DefaultCategoryDataset;

/*
 https://www.tutorialspoint.com/jfreechart/jfreechart_line_chart.htm
 This class is used to generate the line chart for RotLA game
*/
public class LineChart {
    private DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // This method is used to add new data points after each turn
    public void AddData(ArrayList<Integer> turnStats, String turn) {
        this.dataset.addValue( turnStats.get(0) , "Treasure found" , turn );
        this.dataset.addValue( turnStats.get(1) , "Adventurer damage" , turn );
        this.dataset.addValue( turnStats.get(2) , "Creature active" , turn );
    }

    // This method is used generate the Line chart and save it
    public void plotGameChart() throws Exception{
        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "Game Statistics for each turn","Turn",
                "Count",
                dataset,PlotOrientation.VERTICAL,
                true,true,false);

        int width = 1080;    /* Width of the image */
        int height = 560;   /* Height of the image */
        File lineChart = new File( "RotLASummary.jpeg" );
        ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
        System.out.println("RotLA turn summary chart created!!! Filename is -"+ lineChart);
    }
}
