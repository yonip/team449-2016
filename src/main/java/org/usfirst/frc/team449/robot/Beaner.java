package org.usfirst.frc.team449.robot;

import org.json.JSONObject;

import javax.annotation.processing.ProcessingEnvironment;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * a class to create beans and a map for this robot
 */
public class Beaner {
    public static void main(String[] args) {
        String path = "src/main/java/org/usfirst/frc/team449/robot";
        Map<String, File> files = mapFiles(new File(path));
        Set<String> names= files.keySet();
        for (String name : names) {
            Pattern mapPattern = Pattern.compile("\\w+(?=Map\\.java)"); // look for something composed of word characters surrounded by path separator and Map.java
            Matcher mapMatcher = mapPattern.matcher(name);
            int start;
            int end;
            if (mapMatcher.find()) {
                start = mapMatcher.start();
                end = mapMatcher.end();
            } else {
                continue;
            }
            String beanName = name.substring(start, end);
            File subsystem = files.get(beanName + "Subsystem.java");
            File map = files.get(name);
            beanName = beanName.toLowerCase();

            if (subsystem == null) {
                // TODO decide what error to throw here, caused only by bad naming conventions
            }

            System.out.println(beanName);
        }
    }

    public static Stack<File> getFiles(File file) {
        Stack<File> dir = new Stack<>();
        Stack<File> files = new Stack<>();
        File[] subfiles;
        dir.push(file);
        if (!file.isDirectory()) {
            return dir;
        }

        while (!dir.empty()) {
            subfiles = dir.pop().listFiles();
            for (File subfile : subfiles) {
                if (subfile.isDirectory()) {
                    dir.push(subfile);
                } else {
                    files.push(subfile);
                }
            }
        }

        return files;
    }

    public static Map<String, File> mapFiles(File file) {
        Map<String, File> map = new HashMap<>();
        Stack<File> files = getFiles(file);
        for (File f : files) {
            File rep = map.put(f.getName(), f);
            if (rep != null) {
                System.out.println("Replaced file " + rep.getAbsolutePath() + " with " + f.getAbsolutePath());
            }
        }
        return map;
    }

}
