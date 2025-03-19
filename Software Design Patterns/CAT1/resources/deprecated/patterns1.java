// Abstract class for medical devices
public abstract class MedicalDevice {
    protected String name;
    protected String description;

    public MedicalDevice(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDetails() {
        return name + " - " + description;
    }
}


// Abstract class for physiological parameters
public abstract class PhysiologicalParameter {
    protected String name;
    protected String unit; // Fixed unit per parameter

    public PhysiologicalParameter(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public String getDetails() {
        return name + " (" + unit + ")";
    }
}

// Concrete subclass for Heart Rate
public class HeartRate extends PhysiologicalParameter {
    public HeartRate() {
        super("Heart Rate", "bpm"); // Fixed unit: "bpm"
    }
}

// Concrete subclass for Blood Pressure
public class BloodPressure extends PhysiologicalParameter {
    public BloodPressure() {
        super("Blood Pressure", "mmHg"); // Fixed unit: "mmHg"
    }
}

// Concrete subclass for Oxygen Saturation
public class OxygenSaturation extends PhysiologicalParameter {
    public OxygenSaturation() {
        super("Oxygen Saturation", "%"); // Fixed unit: "%"
    }
}

// Class for defining measurement range
public class Range {
    private double min;
    private double max;

    public Range(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public boolean isInRange(double value) {
        return value >= min && value <= max;
    }

    public String toString() {
        return min + " - " + max;
    }
}


// Concrete class for specific device instances
public class Device extends MedicalDevice {
    private String serialNumber;          // Unique per device
    private Range range;                  // Measurement limits
    private PhysiologicalParameter parameter; // What this device measures

    // Constructor calling the superclass attributes
    public Device(String name, String description, String serialNumber, 
                  PhysiologicalParameter parameter, Range range) {
        super(name, description); // Pass name and description to MedicalDevice
        this.serialNumber = serialNumber;
        this.parameter = parameter;
        this.range = range;
    }

    // Use super.getDetails() to retrieve the inherited attributes
    public String getDeviceDetails() {
        return super.getDetails() + " | Serial: " + serialNumber + 
               " | Measures: " + parameter.getDetails() + 
               " | Range: " + range.toString();
    }
}


// Main class to demonstrate CardioMax device
public class Main {
    public static void main(String[] args) {
        // Create a HeartRate physiological parameter
        PhysiologicalParameter heartRate = new HeartRate();

        // Define the valid range for CardioMax (40 - 200 bpm)
        Range heartRateRange = new Range(40, 200);

        // Create an instance of the CardioMax device
        Device cardioMax = new Device("CardioMax", "Digital heart rate monitor", 
                                      "12345", heartRate, heartRateRange);

        // Print device details
        System.out.println(cardioMax.getDeviceDetails());
    }
}
