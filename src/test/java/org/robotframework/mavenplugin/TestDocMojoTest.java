package org.robotframework.mavenplugin;

import java.io.File;


public class TestDocMojoTest
        extends AbstractRFMojoTestCase {

    private final String outputDirectory = "target/robotframework/testdoc/";
    private final String txtResourceLibDoc = outputDirectory + "invalid_login.html";
    private final String javalibLibDoc = outputDirectory + "ExampleLib.html";
    private final String mylibLibDoc = outputDirectory + "mylib.html";
    private final String mypackageMylibLibDoc = outputDirectory + "mypackage.mylib.html";

    protected void setUp()
            throws Exception {
        super.setUp();
        File outputDir = new File(outputDirectory);
        outputDir.mkdirs();
        deleteDocument(txtResourceLibDoc);
        deleteDocument(javalibLibDoc);
        deleteDocument(mylibLibDoc);
        deleteDocument(mypackageMylibLibDoc);
    }

    public void testLibDocForTxtResource()
            throws Exception {
        executeLibdocWithPom("src/test/resources/pom-testdoc-txtfile.xml");
        assertTrue(txtResourceLibDoc + " not found", new File(txtResourceLibDoc).exists());

    }

}
