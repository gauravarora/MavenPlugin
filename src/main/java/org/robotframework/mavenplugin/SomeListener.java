package org.robotframework.mavenplugin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SomeListener {
    public static final int ROBOT_LISTENER_API_VERSION = 2;
    public static final String DEFAULT_FILENAME = "listen_java.txt";
    private BufferedWriter outfile = null;

    public SomeListener() throws IOException {
        this(DEFAULT_FILENAME);
    }

    public SomeListener(String filename) throws IOException {
        String tmpdir = System.getProperty("java.io.tmpdir");
        String sep = System.getProperty("file.separator");
        String outpath = tmpdir + sep + filename;
        outfile = new BufferedWriter(new FileWriter(outpath));
    }

    public void startSuite(String name, Map attrs) throws IOException {
        outfile.write(name + " '" + attrs.get("doc") + "'\n");
    }

    public void startTest(String name, Map attrs) throws IOException {
        outfile.write("- " + name + " '" + attrs.get("doc") + "' [ ");
        List tags = (List)attrs.get("tags");
        for (int i=0; i < tags.size(); i++) {
           outfile.write(tags.get(i) + " ");
        }
        outfile.write(" ] :: ");
        outfile.write(" Test is critical? " + attrs.get("critical"));
    }

    public void endTest(String name, Map attrs) throws IOException {
        String status = attrs.get("status").toString();
        if (status.equals("PASS")) {
            outfile.write("PASS\n");
        }
        else {
            outfile.write("FAIL: " + attrs.get("message") + "\n");
        }
    }

    public void endSuite(String name, Map attrs) throws IOException {
        outfile.write(attrs.get("status") + "\n" + attrs.get("message") + "\n");
    }

    public void close() throws IOException {
        outfile.close();
    }
}
