package ru.ezhoff.geolocation.geoexplorer;

/**
 * Entity for monitors of environment and writes result to logs.
 *
 * @author e.ezhov
 * @version 1.0 05.06.13
 */
public interface Monitor {

    /**
     * Starts monitoring.
     */
    void start();


    /**
     * Stops monitoring.
     */
    void stop();
}
