public class MeasurementUnit {
    private String unitName;

    public MeasurementUnit(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }
}

public class PhysiologicalParameter {
    private String parameterName;
    private MeasurementUnit unit;

    public PhysiologicalParameter(String parameterName, MeasurementUnit unit) {
        this.parameterName = parameterName;
        this.unit = unit;
    }

    public String getParameterName() {
        return parameterName;
    }

    public MeasurementUnit getUnit() {
        return unit;
    }
}

public class Range {
    private double minValue;
    private double maxValue;

    public Range(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public boolean isValid(double value) {
        return value >= minValue && value <= maxValue;
    }
}

public class MedicalDeviceType {
    private String identifierName;
    private String description;
    private PhysiologicalParameter parameter;
    private Range range;

    public MedicalDeviceType(String identifierName, String description, PhysiologicalParameter parameter, Range range) {
        this.identifierName = identifierName;
        this.description = description;
        this.parameter = parameter;
        this.range = range;
    }

    public PhysiologicalParameter getParameter() {
        return parameter;
    }

    public Range getRange() {
        return range;
    }

    public String getIdentifierName() {
        return identifierName;
    }
}


public class MedicalDevice {
    private int serialNumber;
    private MedicalDeviceType deviceType;

    public MedicalDevice(int serialNumber, MedicalDeviceType deviceType) {
        this.serialNumber = serialNumber;
        this.deviceType = deviceType;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public MedicalDeviceType getDeviceType() {
        return deviceType;
    }
}


import java.time.LocalDateTime;

public class MeasurementRecord {
    private LocalDateTime timestamp;
    private double measurementValue;
    private MedicalDevice device;

    public MeasurementRecord(LocalDateTime timestamp, double measurementValue, MedicalDevice device) {
        this.timestamp = timestamp;
        this.measurementValue = measurementValue;
        this.device = device;
    }

    public String getSummary() {
        return "Device: " + device.getDeviceType().getIdentifierName() + " #" + device.getSerialNumber() +
               "\nMeasured: " + device.getDeviceType().getParameter().getParameterName() +
               "\nValue: " + measurementValue + " " + device.getDeviceType().getParameter().getUnit().getUnitName() +
               "\nTimestamp: " + timestamp;
    }
}

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        // Unit (bpm)
        MeasurementUnit bpm = new MeasurementUnit("bpm");

        // Parameter (Heart Rate)
        PhysiologicalParameter heartRate = new PhysiologicalParameter("Heart Rate", bpm);

        // Valid Range (40 a 200 bpm)
        Range cardioRange = new Range(40, 200);

        // Device Type (CARDIOMAX)
        MedicalDeviceType cardiomaxType = new MedicalDeviceType(
            "CARDIOMAX",
            "Device measuring heart rate.",
            heartRate,
            cardioRange
        );

        // Specific device CARDIOMAX (#1234)
        MedicalDevice cardiomax1234 = new MedicalDevice(1234, cardiomaxType);

        // Historical Register (MeasurementRecord)
        MeasurementRecord record = new MeasurementRecord(
            LocalDateTime.of(2025, 1, 1, 10, 30),
            75.0,
            cardiomax1234
        );

        // Show Summarry
        System.out.println(record.getSummary());
    }
}

// Output of the code to demonstrate instantiation
Device: CARDIOMAX #1234
Measured: Heart Rate
Value: 75.0 bpm
Timestamp: 2025-01-01T10:30
