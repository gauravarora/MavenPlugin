package org.robotframework.mavenplugin;

import java.io.File;


public class TestDocMojoTest
        extends AbstractRFMojoTestCase {

    private final String outputDirectory = "target/robotframework/testdoc/";
    private final String txtResourceTestDoc = outputDirectory + "invalid.html";

    protected void setUp()
            throws Exception {
        super.setUp();
        File outputDir = new File(outputDirectory);
        outputDir.mkdirs();
        deleteDocument(txtResourceTestDoc);
    }

    public void testLibDocForTxtResource()
            throws Exception {
        executeLibdocWithPom("testdoc", "src/test/resources/pom-testdoc-txtfile.xml");
        assertTrue(txtResourceTestDoc + " not found", new File(txtResourceTestDoc).exists());
    }
}
