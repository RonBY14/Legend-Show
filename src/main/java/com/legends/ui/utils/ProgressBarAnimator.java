package com.legends.ui.utils;

import javax.swing.*;

public class ProgressBarAnimator {

    public static SwingWorker getAnimationThread(JProgressBar progressBar, int stopAt, int speed) {
        return new SwingWorker<>() {

            @Override
            protected Object doInBackground() {
                for (int i = 1; i < stopAt && !isCancelled(); i++) {
                    progressBar.setValue(i);

                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
    }
}
