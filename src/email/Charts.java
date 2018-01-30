package email;

import java.io.*;
import java.text.DateFormatSymbols;
import java.util.Random;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtilities;

public class Charts {
   
    public static void main(String[]args) throws Exception {
        
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (String month : new DateFormatSymbols().getMonths()) {
            if (!month.equals("")) {
                dataset.addValue(new Random().nextInt(90)+10, month.substring(0, 3), month.substring(0, 3));
            }
        }
        
        JFreeChart barChart = ChartFactory.createBarChart("Email Rates", "Month", "Email #", dataset, PlotOrientation.VERTICAL, true, true, false);

        int width = 1000;
        int height = 500;
        File BarChart = new File( "BarChart.png" ); 
        ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
    }
}