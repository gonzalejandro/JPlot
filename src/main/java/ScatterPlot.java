package main.java;

import java.util.List;

import javafx.scene.chart.Axis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

/**
 * Adapter of ScatterChart JavaFX, it contains his own horizontal and vertical axes which 
 * can be set to a value dependent on the user input.
 * 
 * @author alejandro
 *
 * @param <T1>  Type of the parameter of the x axe.
 * @param <T2>  Type of the parameter of the y axe.
 */
public class ScatterPlot<T1, T2> {
  private Axis<T1> horAxis;
  private Axis<T2> verAxis;
  private ScatterChart<T1, T2> plot;

  /**
   * Builder of a ScatterPlot, receives two Axis which can be it's subclasses (NumberAxis or 
   * CategorverAxis).
   * @param horAxis   Horizontal axe of the ScatterPlot.
   * @param verAxis   Vertical axe of the ScatterPlot.
   */
  public ScatterPlot(Axis<T1> horAxis, Axis<T2> verAxis) {
    this.horAxis = horAxis;
    this.verAxis = verAxis;
    plot = new ScatterChart<T1, T2>(horAxis, verAxis);
  }
  
  /**
   * Receive two lists of data representing the horizontal and vertical coordinates and 
   * add it to the ScatterPlot as an independent series of points.
   * @param horData  Horizontal coordinates of the series of points.
   * @param verData  Vertical coordinates of the series of points. 
   *
   */
  public void addSeries(List<T1> horData, List<T2> verData) {
    addSeries(horData, verData, "");
  }

  /**
   * Receive two lists of data representing the horizontal and vertical coordinates and add it
   * to the ScatterPlot as an independent series of points. Includes the name of the series as 
   * third parameter.
   * @param horData   Horizontal coordinates of the series of points.
   * @param verData   Vertical coordinates of the series of points.
   * @param seriesName  Name of the series of points.
   */
  public void addSeries(List<T1> horData, List<T2> verData, String seriesName) {
    if (horData.size() != verData.size()) {
      throw new IllegalArgumentException("X and Y data must have the same size");
    }
    XYChart.Series<T1, T2> series = new XYChart.Series<>();
    for (int i = 0; i < horData.size(); i++) {
      series.getData().add(new XYChart.Data<>(horData.get(i), verData.get(i)));
    }
    series.setName(seriesName);
    plot.getData().add(series);
  }
  
  /**
   * Setter of the title of the ScatterPlot.
   * @param title String representative of the plot's title.
   */
  public void setTitle(String title) {
    plot.setTitle(title);
  }

  /**
   * ScatterPlot's horizontal label setter.
   * @param label   String representative of the plot's horizontal label.
   */
  public void setXLabel(String label) {
    horAxis.setLabel(label);
  }
  
  /**
   * ScatterPlot's vertical label setter.
   * @param label   String representative of the plot's vertical label.
   */
  public void setYLabel(String label) {
    verAxis.setLabel(label);
  }

  /**
   * Once the ScatterPlot is ready use this method to get the ScatterChart created using
   * ScatterPlot as adapter.
   * @return    ScatterChart chart.
   */  
  public ScatterChart<T1, T2> getPlot() {
    return plot;
  }
}
