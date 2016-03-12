package org.usfirst.frc.team449.robot;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * base class for the maps. holds basic map classes like Motor, Encoder, PID
 */
public abstract class RobotMap {

	// TODO put this in cfg
	// TODO get these constants right
	public static final double leftGoalHeading = 0.523599;
	public static final double rightGoalHeading = -0.523599;

	/**
	 * creates a new Map based on the configuration in the given json any maps
	 * in here are to be shared across all subsystems
	 *
	 * @param json
	 *            a JSONObject containing the configuration for the maps in this
	 *            object
	 */
	public RobotMap(JSONObject json) {
		try {
			Field[] fields = this.getClass().getFields();
			Map<String, Class> inners = new HashMap<>();
			Class[] classes = this.getClass().getClasses();
			for (Class c : classes) {
				Class temp = inners.put(c.getSimpleName(), c);
				if (temp != null) {
					System.err.println("Replaced class "
							+ temp.getCanonicalName() + " with "
							+ c.getCanonicalName());
				}
			}
			for (Field f : fields) {
				String type = f.getType().getSimpleName();
				String path = this.getPath() + "components.";
				if (type.equals("double") || type.equals("boolean")
						|| type.equals("int")) {
					path += f.getName();
					switch (type) {
					case "double":
						f.setDouble(this, getDouble(path, json));
						break;
					case "boolean":
						f.setBoolean(this, getBoolean(path, json));
						break;
					case "int":
						f.setInt(this, getInt(path, json));
						break;
					}
				} else {
					Constructor moConst = inners.get(type).getConstructor(
							JSONObject.class, String.class, Class.class);
					path += type + ".instances." + f.getName();
					try {
						f.set(this, moConst.newInstance(json, path, getClass()));
					} catch (InstantiationException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}

			}
		} catch (IllegalAccessException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	/**
	 * a getter for the path of the object in the json, such that any subclass
	 * of this class returns its unique identifier appended to this path
	 *
	 * @return a string that is the "root" for this map
	 */
	public String getPath() {
		String ret = "";
		for (Class cl = getClass(); !cl.equals(RobotMap.class); cl = cl
				.getSuperclass()) {
			ret = cl.getSimpleName() + "." + ret;
		}
		return ret;
	}

	/**
	 * an abstract class for any MapObject, for polymorphism and a constructor
	 */
	public static abstract class MapObject {

		/**
		 * creates a Map based on the JSONObject given to it, and a path down to
		 * this object
		 *
		 * @param json
		 *            the JSONObject containing the values for this object
		 * @param objPath
		 *            the path to find this object in the JSONObject
		 */
		public MapObject(JSONObject json, String objPath, Class enclosing) {
			try {
				Field[] fields = this.getClass().getFields();
				Map<String, Class> inners = new HashMap<>();
				Class[] classes = enclosing.getClasses();
				for (Class c : classes) {
					Class temp = inners.put(c.getSimpleName(), c);
					if (temp != null) {
						System.err.println("Replaced class "
								+ temp.getCanonicalName() + " with "
								+ c.getCanonicalName());
					}
				}
				for (Field f : fields) {
					String type = f.getType().getSimpleName();
					String path = objPath;
					if (type.equals("double") || type.equals("boolean")
							|| type.equals("int")) {
						path += "." + f.getName();
						switch (type) {
						case "double":
							f.setDouble(this, getDouble(path, json));
							break;
						case "boolean":
							f.setBoolean(this, getBoolean(path, json));
							break;
						case "int":
							f.setInt(this, getInt(path, json));
							break;
						}
					} else if (type.endsWith("[]")) { // shit an array
						String name = type;
						type = type.substring(0, type.length() - 2);
						path += "." + name + ".instances.";
						int ln = getInt(path + f.getName() + ".length", json);
						Object[] arr = (Object[]) Array.newInstance(
								inners.get(type), ln);
						Constructor moConst = inners.get(type).getConstructor(
								JSONObject.class, String.class, Class.class);
						for (int i = 0; i < ln; i++) {
							arr[i] = moConst.newInstance(json,
									path + f.getName() + "[" + i + "]",
									enclosing);
						}
						f.set(this, arr);

					} else {
						Constructor moConst = inners.get(type).getConstructor(
								JSONObject.class, String.class, Class.class);
						path += "." + type + ".instances." + f.getName();
						f.set(this, moConst.newInstance(json, path, enclosing));
					}

				}
			} catch (IllegalAccessException | NoSuchMethodException
					| InstantiationException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * a map of a Motor. Contains a port and a flag for inversion
	 */
	public static class Motor extends MapObject {
		/**
		 * the port this motor is connected to
		 */
		public int PORT;
		/**
		 * whether this motor should be inverted or not
		 */
		public boolean INVERTED;

		public Motor(JSONObject json, String path, Class enclosing) {
			super(json, path, enclosing);
		}
	}

	/**
	 * a map of an Encoder. Contains the two ports needed for the encoder (a and
	 * b) and the dpi of this encoder
	 */
	public static class Encoder extends MapObject {
		/**
		 * port a of the encoder
		 */
		public int a;
		/**
		 * port be of the encoder
		 */
		public int b;
		/**
		 * the dpp (distance per pulse) of this encoder
		 */
		public double dpp;

		public Encoder(JSONObject json, String path, Class enclosing) {
			super(json, path, enclosing);
		}
	}

	/**
	 * a basic PID object, only contains the p, i and d values
	 */
	public static abstract class PID extends MapObject {
		/**
		 * the p value for the pid controller
		 */
		public double p;
		/**
		 * the i value for the pid controller
		 */
		public double i;
		/**
		 * the d value for the pid controller
		 */
		public double d;
		/**
		 * the percent error around the setpoint that is "close enough" and
		 * requires no more tuning (20 for 20%)
		 */
		public double percentTolerance;

		public PID(JSONObject json, String path, Class enclosing) {
			super(json, path, enclosing);
		}
	}

	public static abstract class VelocityPID extends PID {
		/**
		 * radius of the range of values around zero, that when read from the
		 * encoder when setpoint is 0 results in velocity being 0
		 */
		public double zeroTolerance;
		/**
		 * the maximum delta velocity from the controller
		 */
		public double outputRange;

		public VelocityPID(JSONObject json, String path, Class enclosing) {
			super(json, path, enclosing);
		}
	}

	/**
	 * a PID controller that uses an Encoder to control a Motor
	 */
	public static class MotorPID extends PID {
		/**
		 * the motor controlled by this controller
		 */
		public Motor motor;
		/**
		 * the encoder controlling the motor
		 */
		public Encoder encoder;

		public MotorPID(JSONObject json, String path, Class enclosing) {
			super(json, path, enclosing);
		}
	}

	/**
	 * a map for a limit switch
	 */
	public static class LimitSwitch extends MapObject {
		public int PORT;

		/**
		 * creates a LimitSwitch Map based on the JSONObject given to it, and a
		 * path down to this object
		 *
		 * @param json
		 *            the JSONObject containing the values for this object
		 * @param path
		 *            the path to find this object in the JSONObject
		 */
		public LimitSwitch(JSONObject json, String path, Class enclosing) {
			super(json, path, enclosing);
		}
	}

	/**
	 * get an int from the path or any "static" occurrences of the value
	 * mentioned. static occurrences are defined to be any paths that are a
	 * version of this path with any number of ".instances.<anything>" cut out
	 *
	 * @param path
	 *            the path of the value at hand
	 * @param obj
	 *            the JSONObject to look through
	 * @return the value found in the JSONObject if it is positive, or -1 if it
	 *         was not found or it was negative
	 */
	protected static int getInt(String path, JSONObject obj) {
		String[] split = path.split("\\.instances\\.\\w+"); // we can collapse
															// the path around
															// anything that's
															// like
															// .instances.<>
		String[] splitters = new String[split.length - 1];
		Pattern p = Pattern.compile("\\.instances\\.\\w+"); // well now find
															// what the <> is
		Matcher m = p.matcher(path);
		int count = 0;
		int start, end;
		while (m.find()) {
			start = m.start();
			end = m.end();
			splitters[count] = path.substring(start, end);
			count++;
		}

		int mask = (1 << count) - 1; // bits of the mask show what slots in the
										// array are used. eg 11 means use last
										// two, 110 means us 2nd and 3rd to last
		String test;
		int temp = -1;
		for (; mask > -1; mask--) {
			test = split[0];
			for (int i = 0; i < count; i++) {
				if (((1 << (count - 1 - i)) & mask) != 0) { // so that we go
															// from the end of
															// the array back,
															// in order of
															// precedence
					test += splitters[i];
				}

				test += split[i + 1];
			}

			try {
				temp = strictGetInt(test, obj);
				return temp;
			} catch (ParserException e) {
				// the value wasnt found so we keep going
			}
		}
		throw new FatalParserException("Didn't find the requested value: "
				+ path);
	}

	/**
	 * get a double from the path or any "static" occurrences of the value
	 * mentioned. static occurrences are defined to be any paths that are a
	 * version of this path with any number of ".instances.<anything>" cut out
	 *
	 * @param path
	 *            the path of the value at hand
	 * @param obj
	 *            the JSONObject to look through
	 * @return the value found in the JSONObject if it is positive, or -1 if it
	 *         was not found or it was negative
	 */
	protected static double getDouble(String path, JSONObject obj) {
		String[] split = path.split("\\.instances\\.\\w+"); // we can collapse
															// the path around
															// anything that's
															// like
															// .instances.<>
		String[] splitters = new String[split.length - 1];
		Pattern p = Pattern.compile("\\.instances\\.\\w+"); // well now find
															// what the <> is
		Matcher m = p.matcher(path);
		int count = 0;
		int start, end;
		while (m.find()) {
			start = m.start();
			end = m.end();
			splitters[count] = path.substring(start, end);
			count++;
		}

		int mask = (1 << count) - 1; // bits of the mask show what slots in the
										// array are used. eg 11 means use last
										// two, 110 means us 2nd and 3rd to last
		String test;
		double temp = -1;
		for (; mask > -1; mask--) {
			test = split[0];
			for (int i = 0; i < count; i++) {
				if (((1 << (count - 1 - i)) & mask) != 0) { // so that we go
															// from the end of
															// the array back,
															// in order of
															// precedence
					test += splitters[i];
				}
				test += split[i + 1];
			}

			try {
				temp = strictGetDouble(test, obj);
				return temp;
			} catch (ParserException e) {
				// the value wasnt found so we keep going
			}
		}
		throw new FatalParserException("Didn't find the requested value: "
				+ path);
	}

	/**
	 * get a boolean from the path or any "static" occurrences of the value
	 * mentioned. static occurrences are defined to be any paths that are a
	 * version of this path with any number of ".instances.<anything>" cut out
	 *
	 * @param path
	 *            the path of the value at hand
	 * @param obj
	 *            the JSONObject to look through
	 * @return the value found in the JSONObject if it is positive, or false if
	 *         it was not found or it was false
	 */
	protected static boolean getBoolean(String path, JSONObject obj) {
		String[] split = path.split("\\.instances\\.\\w+"); // we can collapse
															// the path around
															// anything that's
															// like
															// .instances.<>
		String[] splitters = new String[split.length - 1];
		Pattern p = Pattern.compile("\\.instances\\.\\w+"); // well now find
															// what the <> is
		Matcher m = p.matcher(path);
		int count = 0;
		int start, end;
		while (m.find()) {
			start = m.start();
			end = m.end();
			splitters[count] = path.substring(start, end);
			count++;
		}
		int mask = (1 << count) - 1; // bits of the mask show what slots in the
										// array are used. eg 11 means use last
										// two, 110 means us 2nd and 3rd to last
		String test;
		boolean temp = false;
		for (; mask > -1; mask--) {
			test = split[0];
			for (int i = 0; i < count; i++) {
				if (((1 << (count - 1 - i)) & mask) != 0) { // so that we go
															// from the end of
															// the array back,
															// in order of
															// precedence
					test += splitters[i];
				}
				test += split[i + 1];
			}
			try {
				temp = strictGetBoolean(test, obj);
				return temp;
			} catch (ParserException e) {
				// the value wasnt found so we keep going
			}
		}

		throw new FatalParserException("Didn't find the requested value: "
				+ path);
	}

	/**
	 * tries to get an int from the specified path in the specified object. if
	 * it couldn't find an int, a negative number is returned based on where it
	 * broke.
	 *
	 * @param path
	 *            the path to where the value might be
	 * @param obj
	 *            the object in which the value might be
	 * @return the int in the JSONObject specified by the path, or a negative
	 *         int between -5 and -1, inclusive.
	 * @throws org.usfirst.frc.team449.robot.RobotMap.ParserException
	 *             when the requested value doesn't exist
	 */
	private static int strictGetInt(String path, JSONObject obj)
			throws ParserException {
		String[] split = path.split("\\.");
		Object temp = obj;
		for (int i = 0; i < split.length - 1; i++) {
			if (!(temp instanceof JSONObject)) { // only JSONObjects here.
													// JSONArrays should be done
													// and anything else is
													// useless
				System.err
						.println("Reached an impossible state in strictGetInt "
								+ path + " " + split[i]);
				throw new FatalParserException(
						"Reached an impossible state while parsing (next object to parse is not a JSONObject)");
			}
			obj = (JSONObject) temp;
			// ok so rn i have a JSONObject
			if (split[i].matches("\\w+(\\[\\])*(\\[\\d+\\])+$")) { // so like
																	// "arrayname[index]"
																	// or
																	// "arrayname[2nd index][index]"
				String arrGet = split[i];
				Stack<Integer> indices = new Stack<>();
				while (arrGet.matches("\\w+(\\[\\])*(\\[\\d+\\])+$")) {
					indices.push(Integer.parseInt(arrGet.substring(
							arrGet.lastIndexOf('[') + 1,
							arrGet.lastIndexOf(']'))));
					arrGet = arrGet.substring(0, arrGet.lastIndexOf('['));
				}
				try {
					obj = obj.getJSONObject(arrGet);
					while (indices.size() > 1) {
						obj = obj.getJSONObject(indices.pop().toString());
					}
					temp = obj.get(indices.pop().toString());
				} catch (JSONException e) {
					throw new ParserException("Object doesn't exist");
				}
			} else {
				temp = obj.opt(split[i]);
			}
			if (temp == null) {
				throw new ParserException("Object doesn't exist");
			}
		}
		// if im here and it's an array, im just looking for length, right?

		// ok so, not an array. maybe JSONObject?
		if (temp instanceof JSONObject) {
			obj = (JSONObject) temp;
			if (split[split.length - 1].equals("length")) {
				int l = 0;
				while (obj.has("" + l)) {
					l++;
				}
				return l;
			} else {
				try {
					return obj.getInt(split[split.length - 1]); // ok exists,
																// now get me
																// the int. or
																// -4 if it's
																// not there
				} catch (JSONException e) {
					throw new ParserException("Field doesn't exist", e);
				}
			}
		}
		// maybe it's an int, so I can pretend it's an int?
		if (temp instanceof Number) {
			return ((Number) temp).intValue();
		}
		// maybe it's an int hiding as a string
		if (temp instanceof String && ((String) temp).matches("^-?\\d+$")) {
			return Integer.parseInt((String) temp);
		}
		// well ok how did you get here? what even are you?
		throw new FatalParserException(
				"Reached the end of the method without exiting, something broke ("
						+ path + ")");
	}

	/**
	 * tries to get a double from the specified path in the specified object. if
	 * it couldn't find a double, a negative number is returned based on where
	 * it broke.
	 *
	 * @param path
	 *            the path to where the value might be
	 * @param obj
	 *            the object in which the value might be
	 * @return the double in the JSONObject specified by the path, or a negative
	 *         number in the set {-1,-2,-3,-4,-5,-6}
	 * @throws org.usfirst.frc.team449.robot.RobotMap.ParserException
	 *             when the requested value doesn't exist
	 */
	private static double strictGetDouble(String path, JSONObject obj)
			throws ParserException {
		String[] split = path.split("\\.");
		Object temp = obj;
		JSONArray arr = null;
		for (int i = 0; i < split.length - 1; i++) {
			if (!(temp instanceof JSONObject)) { // only JSONObjects here.
													// JSONArrays should be done
													// and anything else is
													// useless
				System.err
						.println("Reached an impossible state in strictGetDouble");
				throw new FatalParserException(
						"Reached an impossible state while parsing (next object to parse is not a JSONObject)");
			}
			obj = (JSONObject) temp;
			// ok so rn i have a JSONObject
			if (split[i].matches("\\w+(\\[\\])*(\\[\\d+\\])+$")) { // so like
																	// "arrayname[index]"
																	// or
																	// "arrayname[2nd index][index]"
				String arrGet = split[i];
				Stack<Integer> indices = new Stack<>();
				while (arrGet.matches("\\w+(\\[\\])*(\\[\\d+\\])+$")) {
					indices.push(Integer.parseInt(arrGet.substring(
							arrGet.lastIndexOf('[') + 1,
							arrGet.lastIndexOf(']'))));
					arrGet = arrGet.substring(0, arrGet.lastIndexOf('['));
				}
				try {
					obj = obj.getJSONObject(arrGet);
					while (indices.size() > 1) {
						obj = obj.getJSONObject(indices.pop().toString());
					}
					temp = obj.get(indices.pop().toString());
				} catch (JSONException e) {
					throw new ParserException("Object doesn't exist");
				}
			} else {
				temp = obj.opt(split[i]);
			}
			if (temp == null) {
				throw new ParserException("Object doesn't exist");
			}
		}
		// if im here and it's an array, what field would be a double?
		if (temp instanceof JSONArray) {
			throw new FatalParserException(
					"Arrays don't have doubles as fields");
		}
		// ok so, not an array. maybe JSONObject?
		if (temp instanceof JSONObject) {
			obj = (JSONObject) temp;
			try {
				return obj.getDouble(split[split.length - 1]); // ok exists, now
																// get me the
																// int. or -1 if
																// it's not
																// there
			} catch (JSONException e) {
				try {
					return obj.getInt(split[split.length - 1]);
				} catch (JSONException e1) {
					throw new ParserException("Field doesn't exist", e1);
				}
			}
		}
		// maybe it's an int, so I cant pretend it's an double?
		if (temp instanceof Number) {
			return ((Number) temp).doubleValue();
		}
		// maybe it's an double hiding as a string
		if (temp instanceof String
				&& ((String) temp).matches("^-?\\d+(\\.\\d+)?$")) {
			return Double.parseDouble((String) temp);
		}
		// well ok how did you get here? what even are you?
		throw new FatalParserException(
				"Reached the end of the method without exiting, something broke");
	}

	/**
	 * tries to get a boolean from the specified path in the specified object.
	 * if it couldn't find a boolean, this method return false
	 *
	 * @param path
	 *            the path to where the value might be
	 * @param obj
	 *            the object in which the value might be
	 * @return the boolean in the JSONObject specified by the path, or false
	 * @throws org.usfirst.frc.team449.robot.RobotMap.ParserException
	 *             when the requested value doesn't exist
	 */
	private static boolean strictGetBoolean(String path, JSONObject obj)
			throws ParserException {
		String[] split = path.split("\\.");
		Object temp = obj;
		JSONArray arr = null;
		for (int i = 0; i < split.length - 1; i++) {
			if (!(temp instanceof JSONObject)) { // only JSONObjects here.
													// JSONArrays should be done
													// and anything else is
													// useless
				System.err
						.println("Reached an impossible state in strictGetBoolean "
								+ path + " " + split[i] + " " + temp);
				throw new FatalParserException(
						"Reached an impossible state while parsing (next object to parse is not a JSONObject)");
			}
			obj = (JSONObject) temp;
			// ok so rn i have a JSONObject
			if (split[i].matches("\\w+(\\[\\])*(\\[\\d+\\])+$")) { // so like
																	// "arrayname[index]"
																	// or
																	// "arrayname[2nd index][index]"
				String arrGet = split[i];
				Stack<Integer> indices = new Stack<>();
				while (arrGet.matches("\\w+(\\[\\])*(\\[\\d+\\])+$")) {
					indices.push(Integer.parseInt(arrGet.substring(
							arrGet.lastIndexOf('[') + 1,
							arrGet.lastIndexOf(']'))));
					arrGet = arrGet.substring(0, arrGet.lastIndexOf('['));
				}
				try {
					obj = obj.getJSONObject(arrGet);
					while (indices.size() > 1) {
						obj = obj.getJSONObject(indices.pop().toString());
					}
					temp = obj.get(indices.pop().toString());
				} catch (JSONException e) {
					throw new ParserException("Object doesn't exist");
				}
			} else {
				temp = obj.opt(split[i]);
			}
			if (temp == null) {
				throw new ParserException("Object doesn't exist");
			}
		}
		// if im here and it's an array, what boolean would be here?
		if (temp instanceof JSONArray) {
			throw new FatalParserException(
					"Arrays don't have booleans as fields");
		}
		// ok so, not an array. maybe JSONObject?
		if (temp instanceof JSONObject) {
			obj = (JSONObject) temp;
			try {
				return obj.getBoolean(split[split.length - 1]); // ok exists,
																// now get me
																// the boolean
			} catch (JSONException e) {
				throw new ParserException("Field doesn't exist", e);
			}
		}
		// maybe it's a boolean, so I cant pretend it's a boolean?
		if (temp.equals(Boolean.FALSE)
				|| (temp instanceof String && ((String) temp)
						.equalsIgnoreCase("false"))) {
			return false;
		} else if (temp.equals(Boolean.TRUE)
				|| (temp instanceof String && ((String) temp)
						.equalsIgnoreCase("true"))) {
			return true;
		}
		// well ok how did you get here? what even are you?
		throw new FatalParserException(
				"Reached the end of the method without exiting, something broke");
	}

	private static class ParserException extends RuntimeException {
		/**
		 * Constructs a ParserException with an explanatory message.
		 *
		 * @param message
		 *            Detail about the reason for the exception.
		 */
		public ParserException(String message) {
			super(message);
		}

		/**
		 * Constructs a ParserException with an explanatory message and cause.
		 *
		 * @param message
		 *            Detail about the reason for the exception.
		 * @param cause
		 *            The cause.
		 */
		public ParserException(String message, Throwable cause) {
			super(message, cause);
		}

		/**
		 * Constructs a new ParserException with the specified cause.
		 *
		 * @param cause
		 *            The cause.
		 */
		public ParserException(Throwable cause) {
			super(cause.getMessage(), cause);
		}
	}

	private static class FatalParserException extends RuntimeException {
		/**
		 * Constructs a FatalParserException with an explanatory message.
		 *
		 * @param message
		 *            Detail about the reason for the exception.
		 */
		public FatalParserException(String message) {
			super(message);
		}

		/**
		 * Constructs a FatalParserException with an explanatory message and
		 * cause.
		 *
		 * @param message
		 *            Detail about the reason for the exception.
		 * @param cause
		 *            The cause.
		 */
		public FatalParserException(String message, Throwable cause) {
			super(message, cause);
		}

		/**
		 * Constructs a new FatalParserException with the specified cause.
		 *
		 * @param cause
		 *            The cause.
		 */
		public FatalParserException(Throwable cause) {
			super(cause.getMessage(), cause);
		}
	}
}
