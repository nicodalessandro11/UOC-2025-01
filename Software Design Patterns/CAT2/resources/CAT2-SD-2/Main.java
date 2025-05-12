import java.util.*;

// COMMAND INTERFACE
interface Command {
    void execute();
    void undo();
}

// MEMENTO: STATE CLASS
class DeviceState {
    float temperature;
    boolean isWorking;
    String mode;

    DeviceState(float temperature, boolean isWorking, String mode) {
        this.temperature = temperature;
        this.isWorking = isWorking;
        this.mode = mode;
    }
}

// RECEIVER
class Device {
    private String name;
    private float temperature = 0;
    private boolean isWorking = false;
    private String mode = "off";

    Device(String name) {
        this.name = name;
    }

    public void start() {
        isWorking = true;
        mode = "on";
        System.out.println(name + ": STARTED");
    }

    public void stop() {
        isWorking = false;
        mode = "off";
        System.out.println(name + ": STOPPED");
    }

    public void preHeat(float temp) {
        temperature = temp;
        isWorking = true;
        mode = "preheat";
        System.out.println(name + ": PREHEATING to " + temp + "°C");
    }

    public void pressureCook(String mode) {
        isWorking = true;
        this.mode = mode;
        System.out.println(name + ": PRESSURE COOKING in " + mode + " mode");
    }

    public DeviceState getState() {
        return new DeviceState(temperature, isWorking, mode);
    }

    public void restoreState(DeviceState state) {
        this.temperature = state.temperature;
        this.isWorking = state.isWorking;
        this.mode = state.mode;
        System.out.println(name + ": STATE RESTORED → Temp: " + temperature + ", Mode: " + mode);
    }

    public void printStatus() {
        System.out.println("STATUS → Temp: " + temperature + ", Mode: " + mode + ", Working: " + isWorking);
    }
}

// COMMANDS

class StartCommand implements Command {
    private Device device;
    private DeviceState backup;

    StartCommand(Device device) {
        this.device = device;
    }

    public void execute() {
        backup = device.getState();
        device.start();
    }

    public void undo() {
        device.restoreState(backup);
    }
}

class StopCommand implements Command {
    private Device device;
    private DeviceState backup;

    StopCommand(Device device) {
        this.device = device;
    }

    public void execute() {
        backup = device.getState();
        device.stop();
    }

    public void undo() {
        device.restoreState(backup);
    }
}

class PreHeatCommand implements Command {
    private Device device;
    private float temp;
    private DeviceState backup;

    PreHeatCommand(Device device, float temp) {
        this.device = device;
        this.temp = temp;
    }

    public void execute() {
        backup = device.getState();
        device.preHeat(temp);
    }

    public void undo() {
        device.restoreState(backup);
    }
}

// INVOKER

class RemoteControl {
    private Stack<Command> history = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void executeCommand(Command cmd) {
        cmd.execute();
        history.push(cmd);
        redoStack.clear();
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command last = history.pop();
            last.undo();
            redoStack.push(last);
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            history.push(cmd);
        } else {
            System.out.println("Nothing to redo.");
        }
    }
}

// MAIN

public class Main {
    public static void main(String[] args) {
        Device oven = new Device("Oven");
        RemoteControl remote = new RemoteControl();

        Command start = new StartCommand(oven);
        Command preheat = new PreHeatCommand(oven, 180);
        Command stop = new StopCommand(oven);

        remote.executeCommand(start);
        oven.printStatus();

        remote.executeCommand(preheat);
        oven.printStatus();

        remote.undo();
        oven.printStatus();

        remote.redo();
        oven.printStatus();

        remote.executeCommand(stop);
        oven.printStatus();

        remote.undo();
        oven.printStatus();
    }
}
