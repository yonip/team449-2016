package org.usfirst.frc.team449.robot;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * base class for the maps. holds basic map classes like Motor, Encoder, PID
 */
public abstract class RobotMap {

    /**
     * creates a new Map based on the configuration in the given json
     * any maps in here are to be shared across all subsystems
     * @param json a JSONObject containing the configuration for the maps in this object
     */
    public RobotMap(JSONObject json) {

    }

    /**
     * a getter for the path of the object in the json, such that any subclass of this class returns its unique identifier
     * appended to this path
     * @return a string that is the "root" for this map
     */
    public String getPath() {
        return "";
    }

    /**
     * an abstract class for any MapObject, for polymorphism and a constructor
     */
    public static abstract class MapObject {
    	/**
    	 * used to lock the component defined by this MapObject for any command using it
    	 */
    	public final Subsystem commandLock;
        /**
         * creates a Map based on the JSONObject given to it, and a path down to this object
         * @param json the JSONObject containing the values for this object
         * @param path the path to find this object in the JSONObject
         */
        public MapObject(JSONObject json, String path) {
        	commandLock = new Subsystem() { @Override protected void initDefaultCommand() {} };
        }
    }

    /**
     * a map of a Motor. Contains a port and a flag for inversion
     */
    public static class Motor extends MapObject {
        /** the port this motor is connected to */
        public final int PORT;
        /** whether this motor should be inverted or not */
        public final boolean INVERTED;

        public Motor(JSONObject json, String path) {
            super(json, path);
            this.PORT = getInt(path+".port", json);
            this.INVERTED = getBoolean(path+".inverted", json);
        }
    }

    /**
     * a map of an Encoder. Contains the two ports needed for the encoder (a and b) and the dpi of this encoder
     */
    public static class Encoder extends MapObject {
        /** port a of the encoder */
        public final int a;
        /** port be of the encoder */
        public final int b;
        /** the dpp (distance per pulse) of this encoder */
        public final double dpp;

        public Encoder(JSONObject json, String path) {
            super(json, path);
            this.a = getInt(path +".a", json);
            this.b = getInt(path+".b", json);
            this.dpp = getDouble(path + ".dpi", json);
        }
    }

    /**
     * a basic PID object, only contains the p, i and d values
     */
    public static abstract class PID extends MapObject {
        /** the p value for the pid controller */
        public final double p;
        /** the i value for the pid controller */
        public final double i;
        /** the d value for the pid controller */
        public final double d;

        public PID(JSONObject json, String path) {
            super(json, path);
            this.p = getDouble(path + ".p", json);
            this.i = getDouble(path + ".i", json);
            this.d = getDouble(path+".d", json);
        }
    }

    /**
     * a PID controller that uses an Encoder to control a Motor
     */
    public static class MotorPID extends PID {
        /** the motor controlled by this controller */
        public final Motor motor;
        /** the encoder controlling the motor */
        public final Encoder encoder;

        public MotorPID(JSONObject json, String path) {
            super(json, path);
            this.motor = new Motor(json, path);
            this.encoder = new Encoder(json, path);
        }
    }

    /**
     * a map for a limit switch
     */
    public static class LimitSwitch extends MapObject {
        public final int PORT;

        /**
         * creates a LimitSwitch Map based on the JSONObject given to it, and a path down to this object
         *
         * @param json the JSONObject containing the values for this object
         * @param path the path to find this object in the JSONObject
         */
        public LimitSwitch(JSONObject json, String path) {
            super(json, path);
            this.PORT = getInt(path + ".port", json);
        }
    }

    /**
     * get an int from the path or any "static" occurrences of the value mentioned. static occurrences are defined to be
     * any paths that are a version of this path with any number of ".instances.<anything>" cut out
     * @param path the path of the value at hand
     * @param obj the JSONObject to look through
     * @return the value found in the JSONObject if it is positive, or -1 if it was not found or it was negative
     */
    protected static int getInt(String path, JSONObject obj) {
        String[] split = path.split("\\.instances\\.\\w+"); // we can collapse the path around anything that's like .instances.<>
        String[] splitters = new String[split.length-1];
        Pattern p = Pattern.compile("\\.instances\\.\\w+"); // well now find what the <> is
        Matcher m = p.matcher(path);
        int count = 0;
        int start, end;
        while(m.find()) {
            start = m.start();
            end = m.end();
            System.out.println(start + " " + end + " " + path.substring(start, end));
            splitters[count] = path.substring(start, end);
            count++;
        }

        int mask = (1 << count) -1; // bits of the mask show what slots in the array are used. eg 11 means use last two, 110 means us 2nd and 3rd to last
        String test;
        int temp = -1;
        for(; mask > -1; mask--) {
            test = split[0];
            for (int i = 0; i <count; i++) {
                if(((1<<(count-1-i)) & mask) != 0) { // so that we go from the end of the array back, in order of precedence
                    test +=splitters[i];
                }
                test += split[i+1];
            }

            //System.out.println(test); // print the string to test here
            temp = strictGetInt(test, obj);
            //System.out.println(temp); // print the value from that string
            if(temp >= 0) {
                return temp;
            }
        }
        return -1;
    }

    /**
     * get a double from the path or any "static" occurrences of the value mentioned. static occurrences are defined to be
     * any paths that are a version of this path with any number of ".instances.<anything>" cut out
     * @param path the path of the value at hand
     * @param obj the JSONObject to look through
     * @return the value found in the JSONObject if it is positive, or -1 if it was not found or it was negative
     */
    protected static double getDouble(String path, JSONObject obj) {
        String[] split = path.split("\\.instances\\.\\w+"); // we can collapse the path around anything that's like .instances.<>
        String[] splitters = new String[split.length-1];
        Pattern p = Pattern.compile("\\.instances\\.\\w+"); // well now find what the <> is
        Matcher m = p.matcher(path);
        int count = 0;
        int start, end;
        while(m.find()) {
            start = m.start();
            end = m.end();
            System.out.println(start + " " + end + " " + path.substring(start, end));
            splitters[count] = path.substring(start, end);
            count++;
        }

        int mask = (1 << count) -1; // bits of the mask show what slots in the array are used. eg 11 means use last two, 110 means us 2nd and 3rd to last
        String test;
        double temp = -1;
        for(; mask > -1; mask--) {
            test = split[0];
            for (int i = 0; i <count; i++) {
                if(((1<<(count-1-i)) & mask) != 0) { // so that we go from the end of the array back, in order of precedence
                    test +=splitters[i];
                }
                test += split[i+1];
            }

            //System.out.println(test); // print the string to test here
            temp = strictGetDouble(test, obj);
            //System.out.println(temp); // print the value from that string
            if(temp >= 0) {
                return temp;
            }
        }
        return -1;
    }

    /**
     * get a boolean from the path or any "static" occurrences of the value mentioned. static occurrences are defined to be
     * any paths that are a version of this path with any number of ".instances.<anything>" cut out
     * @param path the path of the value at hand
     * @param obj the JSONObject to look through
     * @return the value found in the JSONObject if it is positive, or false if it was not found or it was false
     */
    protected static boolean getBoolean(String path, JSONObject obj) {
        String[] split = path.split("\\.instances\\.\\w+"); // we can collapse the path around anything that's like .instances.<>
        String[] splitters = new String[split.length-1];
        Pattern p = Pattern.compile("\\.instances\\.\\w+"); // well now find what the <> is
        Matcher m = p.matcher(path);
        int count = 0;
        int start, end;
        while(m.find()) {
            start = m.start();
            end = m.end();
            System.out.println(start + " " + end + " " + path.substring(start, end));
            splitters[count] = path.substring(start, end);
            count++;
        }

        int mask = (1 << count) -1; // bits of the mask show what slots in the array are used. eg 11 means use last two, 110 means us 2nd and 3rd to last
        String test;
        boolean temp = false;
        for(; mask > -1; mask--) {
            test = split[0];
            for (int i = 0; i <count; i++) {
                if(((1<<(count-1-i)) & mask) != 0) { // so that we go from the end of the array back, in order of precedence
                    test +=splitters[i];
                }
                test += split[i+1];
            }

            //System.out.println(test); // print the string to test here
            temp = strictGetBoolean(test, obj);
            //System.out.println(temp); // print the value from that string
            if(temp) {
                return temp;
            }
        }
        return false;
    }

    /**
     * tries to get an int from the specified path in the specified object. if it couldn't find an int, a negative number
     * is returned based on where it broke.
     * @param path the path to where the value might be
     * @param obj the object in which the value might be
     * @return the int in the JSONObject specified by the path, or a negative int between -5 and -1, inclusive.
     */
    private static int strictGetInt(String path, JSONObject obj) {
        String[] split = path.split("\\.");
        Object temp = obj;
        JSONArray arr = null;
        for (int i = 0; i < split.length-1; i++) {
            if(!(temp instanceof JSONObject)) { // only JSONObjects here. JSONArrays should be done and anything else is useless
                return -1;
            }
            obj = (JSONObject) temp;
            // ok so rn i have a JSONObject
            if(split[i].matches("\\w+\\[\\d\\]")) { // so like "arrayname[index]"
                arr = obj.optJSONArray(split[i].substring(0,split[i].indexOf('[')));
                if(arr == null) {
                    return -2;
                }
                temp = arr.opt(Integer.parseInt(split[i].substring(split[i].indexOf('[') + 1, split[i].indexOf(']')))); // get whatever object is there, if any.
                arr = null; // just in case...
            } else {
                temp = obj.opt(split[i]);
            }
            if(temp == null) {
                return -3;
            }
        }
        // if im here and it's an array, im just looking for length, right?
        if(temp instanceof JSONArray) {
            if (split[split.length - 1].equals("length")) {
                return ((JSONArray) temp).length();
            }
        }
        // ok so, not an array. maybe JSONObject?
        if(temp instanceof JSONObject) {
            obj = (JSONObject) temp;
            if (!obj.has(split[split.length - 1])) { // does the value im looking for exist?
                return -4;
            }
            return obj.optInt(split[split.length - 1], -4); // ok exists, now get me the int. or -1 if it's not there
        }
        // maybe it's an int, so I cant pretend it's an int?
        if(temp instanceof Number) {
            return ((Number) temp).intValue();
        }
        // maybe it's an int hiding as a string
        if(temp instanceof String && ((String) temp).matches("-?\\d+")) {
            return Integer.parseInt((String) temp);
        }
        // well ok how did you get here? what even are you?
        return -5;
    }

    /**
     * tries to get a double from the specified path in the specified object. if it couldn't find a double, a negative number
     * is returned based on where it broke.
     * @param path the path to where the value might be
     * @param obj the object in which the value might be
     * @return the double in the JSONObject specified by the path, or a negative number in the set {-1,-2,-3,-4,-5,-6}
     */
    private static double strictGetDouble(String path, JSONObject obj) {
        String[] split = path.split("\\.");
        Object temp = obj;
        JSONArray arr = null;
        for (int i = 0; i < split.length-1; i++) {
            if(!(temp instanceof JSONObject)) { // only JSONObjects here. JSONArrays should be done and anything else is useless
                return -1;
            }
            obj = (JSONObject) temp;
            // ok so rn i have a JSONObject
            if(split[i].matches("\\w+\\[\\d\\]")) { // so like "arrayname[index]"
                arr = obj.optJSONArray(split[i].substring(0,split[i].indexOf('[')));
                if(arr == null) {
                    return -2;
                }
                temp = arr.opt(Integer.parseInt(split[i].substring(split[i].indexOf('[') + 1, split[i].indexOf(']')))); // get whatever object is there, if any.
                arr = null; // just in case...
            } else {
                temp = obj.opt(split[i]);
            }
            if(temp == null) {
                return -3;
            }
        }
        // if im here and it's an array, what field would be a double?
        if(temp instanceof JSONArray) {
            return -4;
        }
        // ok so, not an array. maybe JSONObject?
        if(temp instanceof JSONObject) {
            obj = (JSONObject) temp;
            if (!obj.has(split[split.length - 1])) { // does the value im looking for exist?
                return -5;
            }
            return obj.optDouble(split[split.length - 1], -4); // ok exists, now get me the int. or -1 if it's not there
        }
        // maybe it's an int, so I cant pretend it's an double?
        if(temp instanceof Number) {
            return ((Number) temp).doubleValue();
        }
        // maybe it's an double hiding as a string
        if(temp instanceof String && ((String) temp).matches("-?\\d+(\\.\\d+)?")) {
            return Double.parseDouble((String) temp);
        }
        // well ok how did you get here? what even are you?
        return -6;
    }

    /**
     * tries to get a boolean from the specified path in the specified object. if it couldn't find a boolean, this method return false
     * @param path the path to where the value might be
     * @param obj the object in which the value might be
     * @return the boolean in the JSONObject specified by the path, or false
     */
    private static boolean strictGetBoolean(String path, JSONObject obj) {
        String[] split = path.split("\\.");
        Object temp = obj;
        JSONArray arr = null;
        for (int i = 0; i < split.length-1; i++) {
            if(!(temp instanceof JSONObject)) { // only JSONObjects here. JSONArrays should be done and anything else is useless
                return false; // should be an exception/error?
            }
            obj = (JSONObject) temp;
            // ok so rn i have a JSONObject
            if(split[i].matches("\\w+\\[\\d\\]")) { // so like "arrayname[index]"
                arr = obj.optJSONArray(split[i].substring(0,split[i].indexOf('[')));
                if(arr == null) {
                    return false; // should be an exception/error?
                }
                temp = arr.opt(Integer.parseInt(split[i].substring(split[i].indexOf('[') + 1, split[i].indexOf(']')))); // get whatever object is there, if any.
                arr = null; // just in case...
            } else {
                temp = obj.opt(split[i]);
            }
            if(temp == null) {
                return false; // should be an exception/error?
            }
        }
        // if im here and it's an array, what boolean would be here?
        if(temp instanceof JSONArray) {
            return false; // should be an exception/error?
        }
        // ok so, not an array. maybe JSONObject?
        if(temp instanceof JSONObject) {
            obj = (JSONObject) temp;
            if (!obj.has(split[split.length - 1])) { // does the value im looking for exist?
                return false; // should be an exception/error?
            }
            return obj.optBoolean(split[split.length - 1], false); // ok exists, now get me the int. or -1 if it's not there
        }
        // maybe it's an int, so I cant pretend it's an int?
        if (temp.equals(Boolean.FALSE) || (temp instanceof String && ((String) temp).equalsIgnoreCase("false"))) {
            return false;
        } else if (temp.equals(Boolean.TRUE) || (temp instanceof String && ((String) temp).equalsIgnoreCase("true"))) {
            return true;
        }
        // well ok how did you get here? what even are you?
        return false; // should be an exception/error?
    }
}
