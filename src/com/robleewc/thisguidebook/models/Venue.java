/*******************************************************************************
 * Created: Jan 21, 2014
 * 
 * Venue.java 
 * 
 * Contributors:
 *     Robert Lee - initial API and implementation
 ******************************************************************************/

package com.robleewc.thisguidebook.models;

/**
 * @author Robert Lee
 */
public class Venue {
    protected String city;
    protected String state;

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

}
