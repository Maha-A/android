package com.drtms;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;



/**
 * Multiple temperature demo chart.
 */
public class MultipleTemperatureChart extends AbstractDemoChart {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Temperature and sunshine";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "The average temperature and hours of sunshine in Crete (line chart with multiple Y scales and axis)";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
 
  public Intent execute(Context context) {
	  //setContentView(R.layout.grid);  
	//DataBase database= new DataBase(this);
	//double ComingFrom[] = getIntent().getDoubleArrayExtra("Results");
    String[] titles = new String[] { " " };
    List<double[]> x = new ArrayList<double[]>();
    for (int i = 0; i < titles.length; i++) {
      x.add(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}); //6.12, 6.12, 6.12, 6.12, 6.12, 6.12, 6.12, 6.12, 6.12, 6.12,6.12,  });
    }
    List<double[]> values = new ArrayList<double[]>();
    values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2,
        13.9 });
    //values.add(ComingFrom);
    
    int[] colors = new int[] { Color.LTGRAY/*, Color.YELLOW*/ };
    PointStyle[] styles = new PointStyle[] { PointStyle.POINT/*, PointStyle.POINT*/ };
    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer(2);
    setRenderer(renderer, colors, styles);
    int length = renderer.getSeriesRendererCount();
    for (int i = 0; i < length; i++) {
      XYSeriesRenderer r = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
      r.setLineWidth(3f);
    }
    setChartSettings(renderer, "  ", "Dates", "Results", 0.5, 12.5, 0, 32,
        Color.LTGRAY, Color.LTGRAY,Color.WHITE);
    renderer.setXLabels(6);
    renderer.setYLabels(10);
    renderer.setShowGrid(true);
    renderer.setXLabelsAlign(Align.RIGHT);
    renderer.setYLabelsAlign(Align.RIGHT);
    renderer.setZoomButtonsVisible(true);
    renderer.setPanLimits(new double[] { 0, 20, 0, 20 });
    renderer.setZoomLimits(new double[] { 0, 20, -10, 40 });
    renderer.setZoomRate(1.05f);
    renderer.setLabelsColor(Color.WHITE);
    renderer.setXLabelsColor(Color.WHITE);
    renderer.setYLabelsColor(0, colors[0]);
    renderer.setLabelsTextSize(50);
   // renderer.setBackgroundColor(Color.WHITE);
   
    //1 renderer.setYLabelsColor(1, colors[0]);

   //1 renderer.setYTitle("Hours", 1);
    renderer.setYAxisAlign(Align.RIGHT, 1);
    renderer.setYLabelsAlign(Align.LEFT, 1);

    XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
    values.clear();
   //1 values.add(new double[] { 4.3, 4.9, 5.9, 8.8, 10.8, 11.9, 13.6, 12.8, 11.4, 9.5, 7.5, 5.5 });
   //1 addXYSeries(dataset, new String[] { "Sunshine hours" }, x, values, 1);

    Intent intent = ChartFactory.getCubicLineChartIntent(context, dataset, renderer, 0.3f,
        "Diabetes tests");
    return intent;
  }
}
