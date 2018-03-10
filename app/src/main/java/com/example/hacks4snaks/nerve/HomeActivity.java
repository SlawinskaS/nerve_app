package com.example.hacks4snaks.nerve;

import android.os.Bundle;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class HomeActivity extends Header{
    LineGraphSeries<DataPoint> series;
    GraphView graph;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkSensor = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        View view = HomeActivity.this.findViewById(R.id.pulseHider);
//        PulseAnimation pulseAnimation = new PulseAnimation(view);
//        pulseAnimation.setDuration(2000);
//        AnimationSet animSet = new AnimationSet(true);
//        pulseAnimation.setRepeatMode(Animation.RESTART);
//        pulseAnimation.setRepeatCount(Animation.INFINITE);
//        view.startAnimation(pulseAnimation);
        // we get graph view instance
//        graph = (GraphView) findViewById(R.id.graph);
//        // data
////        graph.setV
//        series = new LineGraphSeries<DataPoint>();
//        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
//        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
//        graph.getGridLabelRenderer().setGridColor(255);
//        // customize a little bit viewport
//        Viewport viewport = graph.getViewport();
//        viewport.setYAxisBoundsManual(true);
//
//        viewport.setMinY(0);
//        viewport.setMaxY(100);
//        viewport.setScrollable(true);

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        // we're going to simulate real time with thread that append data to the graph
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                // we add 100 new entries
//                for (int i = 0; i < 10000000; i++) {
//                    runOnUiThread(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            addEntry();
//                        }
//                    });
//
//                    // sleep to slow down the add of entries
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        // manage error ...
//                    }
//                }
//            }
//        }).start();
//    }
//
//    // add random data to graph
//    private void addEntry() {
//        // here, we choose to display max 10 points on the viewport and we scroll to end
//        Random generator = new Random();
//        int y = generator.nextInt(100) + 1;
//        series.appendData(new DataPoint(i,  y), true, 10);
//        i++;
//        graph.addSeries(series );
//    }
}
