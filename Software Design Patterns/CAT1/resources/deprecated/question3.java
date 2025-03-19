// Abstract class for medical devices
public abstract class MedicalDevice {
    protected String name;
    protected String description;
    
    public MedicalDevice(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

// Abstract class for physiological parameters
public abstract class PhysiologicalParameter {
    protected String name;
    protected String unit;
    
    public PhysiologicalParameter(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }
}

// Concrete parameter classes
public class HeartRate extends PhysiologicalParameter {
    public HeartRate() {
        super("Heart Rate", "bpm");
    }
}

public class BloodPressure extends PhysiologicalParameter {
    public BloodPressure() {
        super("Blood Pressure", "mmHg");
    }
}

public class OxygenSaturation extends PhysiologicalParameter {
    public OxygenSaturation() {
        super("Oxygen Saturation", "%");
    }
}

// Class for measurement range
public class Range {
    private double min;
    private double max;
    
    public Range(double min, double max) {
        this.min = min;
        this.max = max;
    }
    
    public boolean isInRange(double value) {
        return value >= min && value <= max;
    }
}

// Device implementation
public class Device extends MedicalDevice {
    private String serialNumber;
    private PhysiologicalParameter parameter;
    private Range validRange;
    
    public Device(String id, String description, String serialNumber, 
                 PhysiologicalParameter parameter, Range validRange) {
        super(name, description);
        this.serialNumber = serialNumber;
        this.parameter = parameter;
        this.validRange = validRange;
    }
}

// Main class for CardioMax example
public class Main {
    public static void main(String[] args) {
        // Create parameter
        PhysiologicalParameter heartRate = new HeartRate();
        
        // Create Range
        Range cardioMaxRange = new Range(40, 200);

        // Create the devie
        Device cardiomax = new Device("CARDIOMAX", "Digital heart rate monitor", 
                                    "1234", heartRate, cardioMaxRange);
    }
}




