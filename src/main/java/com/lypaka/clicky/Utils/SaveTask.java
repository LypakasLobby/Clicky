package com.lypaka.clicky.Utils;

import com.lypaka.clicky.Clicky;
import com.lypaka.clicky.ConfigGetters;

import java.util.Timer;
import java.util.TimerTask;

public class SaveTask {

    public static void startTimer() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                Clicky.configManager.getConfigNode(1, "Accounts").setValue(ConfigGetters.playerStorage);
                Clicky.configManager.save();

            }

        }, 10000, 10000);

    }

}
