package ru.ezhoff.geolocation.geoexplorer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExplorerActivity extends Activity {

    private Button startStopButton;
    private Button markerButton;
    private TextView outputView;
    private FileLogger logger;

    private boolean active = false;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        startStopButton = (Button) findViewById(R.id.startStopButton);
        markerButton    = (Button) findViewById(R.id.markerButton);
        outputView      = (TextView) findViewById(R.id.outputText);
        FileLogger.setOutputView(outputView);
        logger = FileLogger.getInstance();
        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeMonitoringState();
            }
        });
        markerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logger.warn("MARKER");
            }
        });
        logger.info("Loaded.");
    }

    private void changeMonitoringState() {
        active = !active;
        if (active) {
            activateMonitoring();
        } else {
            deactivateMonitoring();
        }
        startStopButton.setText(active ? R.string.stop : R.string.start);
    }

    private void activateMonitoring() {
        logger = FileLogger.getInstance();
        logger.info("Monitoring is activated.");
        //Activate modules here
    }

    private void deactivateMonitoring() {
        //Deactivate modules here
        logger.info("Monitoring is deactivated.");
        logger.close();
    }
}
