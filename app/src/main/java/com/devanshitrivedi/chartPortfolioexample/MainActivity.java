package com.devanshitrivedi.chartPortfolioexample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    PieChart pieChart;
    private float[] yData = {25.3f, 44.32f, 66.76f, 10.6f, 46.01f};
    private String[] xData = {"A", "B", "C", "D", "E"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pieChart = findViewById(R.id.chart);

        pieChart.setRotationEnabled(true);

        pieChart.setHoleColor(Color.parseColor("#bdb76b"));

        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Menu");
        pieChart.setCenterTextSize(20);
        pieChart.animateY(2000);

        pieChart.setDrawEntryLabels(true);


        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {


                int pos1 = (int) h.getX();

                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("Type", xData[pos1]);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        List<PieEntry> pieEntries = new ArrayList<>();

        for (int i = 0; i < yData.length; i++) {
            pieEntries.add(new PieEntry(yData[i], xData[i]));
        }

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for (int i = 1; i < xData.length; i++) {
            xEntrys.add(xData[i]);
        }
        List<Integer> listCol = new ArrayList<Integer>();
        listCol.add(Color.BLACK);
        listCol.add(Color.BLACK);
        //create the data set
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Navigation");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(18);
        pieDataSet.setValueTextColor(Color.BLACK);

        pieDataSet.setValueLineColor(Color.BLACK);

        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setTextColor(Color.BLACK);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}