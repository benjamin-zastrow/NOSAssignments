package nos.bzastrow;

/**
* Abstract class that generally describes a motorized vehicle, which inherits from the abstract class Vehicle and implements common features such as engine, headlight, indicator and fuel operations.
*/
public abstract class MotorizedVehicle extends Vehicle {
    // SH: you miss javadoc
    // SH: Fields shall be private! Having them protected allows you to do bad
    // things, see my comment on initialization in Bicycle.
    protected float enginePower = 70.0f;
    protected String fuelType = "Benzin";
    protected double fuelLevel = 100.0;
    protected float coxPerKM = 100.0f;
    protected boolean engineRunning = false;
    protected boolean headlightsOn = false;
    protected char indicator = 'n'; //n -> no indicator, r -> right indicator, l -> left indicator, w -> warning indicator (both left and right)

    public float getEnginePower() {
        return enginePower;
    }
    public String getFuelType() {
        return fuelType;
    }
    public double getFuelLevel() {
        return fuelLevel;
    }
    public float getCOxPerKM() {
        return coxPerKM;
    }
    public boolean getEngineRunningStatus() {
        return engineRunning;
    }
    public boolean getHeadlightStatus() {
        return headlightsOn;
    }
    /**
     * @return  String that describes the current indicator status in a human-readable way ("disabled", "left", "right" or "warning")
     */
    public String getIndicatorStatus() {
        String retVal;
        switch(indicator) {
            case 'n': retVal = "disabled"; break;
            case 'l': retVal = "left"; break;
            case 'r': retVal = "right"; break;
            case 'w': retVal = "warning"; break;
            default: retVal = "--INTERNAL ERROR--"; break;
        }
        return retVal;
    }

    /**
     * Method to start the engine if it isn't already running
     * @return  'true' if the engine was not running and has been started; 'false' if the engine was already running and has not been started
     */
    public boolean startEngine() {
        if(!engineRunning) {
            engineRunning = true;
            System.out.println("The engine has been started.");
            return true;
        } else {
            System.out.println("The engine is already running and can therefore not be started again!");
            return false;
        }

        /* Consider this different style of code: Here I handle the
         * pathological case first. After that I have the "intended" case, the
         * "normal operation". It makes the code easier to read. It makes it
         * explicit what the exceptional cases are and what the expected case
         * is.

        // Cannot start engine again
        if(engineRunning) {
            System.out.println("The engine is already running and can therefore not be started again!");
            return false;
        }

        engineRunning = true;
        System.out.println("The engine has been started.");
        return true;
        */
    }
    /**
     * Method to stop the engine if it isn't already stopped
     * @return  'true' if the engine was running and has been stopped; 'false' if the engine was already stopped and has not been stopped
     */
    public boolean stopEngine() {
        if(engineRunning) {
            engineRunning = false;
            System.out.println("The engine has been stopped.");
            return true;
        } else {
            System.out.println("The engine is not running and can therefore not be stopped!");
            return false;
        }
    }
    /**
     * Method to enable the headlights if they aren't already on
     * @return  'true' if the headlights were not on and have been enabled; 'false' if the headlights were already on and have not been enabled
     */
    public boolean enableHeadlights() {
        if(!headlightsOn) {
            headlightsOn = true;
            System.out.println("The headlights have been enabled.");
            return true;
        } else {
            System.out.println("The headlights are already enabled.");
            return false;
        }
    }
    /**
     * Method to disable the headlights if they aren't already off
     * @return  'true' if the headlights were on and have been disabled; 'false' if the headlights were already off and have not been disabled
     */
    public boolean disableHeadlights() {
        if(headlightsOn) {
            headlightsOn = false;
            System.out.println("The headlights have been disabled.");
            return true;
        } else {
            System.out.println("The headlights are already disabled.");
            return false;
        }
    }
    /**
     * Method to set the indicator to the given state if it isn't already set to such state
     * @param indicator  char that describes the desired state of the indicator after the operation (possible options are: 'l' or 'r')
     * @return  'true' if the operation was successful; 'false' if the indicator is either already set to the desired state or the parameter is not allowed. 
     */
    public boolean setIndicator(char indicator) {
        if(this.indicator == indicator) {
            System.out.println("The indicator is already set to the desired state. Aborting!");
            return false;
        }
        switch(indicator) {
            case 'l': this.indicator = indicator; break;
            case 'r': this.indicator = indicator; break;
            default: System.out.println("The indicator can only be set either to left or right. Aborting!"); return false;
        }
        return true;
    }
    /**
     * Turns the indicator off
     * @return  'true' if the operation was successful; 'false' if the indicator has already been turned off
     */
    public boolean unsetIndicator() {
        if(indicator == 'n') {
            System.out.println("The indicator is already unset! Aborting.");
            return false;
        } else {
            indicator = 'n';
            System.err.println("The indicator has been unset");
            return true;
        }
    }

    /**
     * Turns the indicator to warning mode (quick-link method that does not require the setIndicator method)
     * @return  'true' if the operation was successful; 'false' if the indicator has already been set to warning mode
     */
    public boolean warningIndicator() {
        if(indicator == 'w') {
            System.out.println("The indicator is already set to warning! Aborting.");
            return false;
        } else {
            indicator = 'w';
            System.err.println("The indicator has been set to warning.");
            return true;
        }
    }
    /**
     * Sets the fuelLevel to 100
     */
    public void refuel() {
        fuelLevel = 100.0;
        System.out.println("The vehicle has been refueled");
    }

    /**
     * @return  a string containing all the fields of the class, as well as their current values.
     */
    @Override
    public String toString() {
        // SH: Use platform-independent %n instead of \n
        return super.toString() + "\nMotorizedVehicle [coxPerKM=" + coxPerKM + ", enginePower=" + enginePower + ", engineRunning="
                + engineRunning + ", fuelLevel=" + fuelLevel + ", fuelType=" + fuelType + ", headlightsOn="
                + headlightsOn + ", indicator=" + indicator + "]";
    }
}
