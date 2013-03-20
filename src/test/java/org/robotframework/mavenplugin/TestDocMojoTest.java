package org.robotframework.mavenplugin;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;


public class TestDocMojoTest
        extends AbstractMojoTestCase {

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

    private void executeLibdocWithPom(String pathToPom) throws Exception, MojoExecutionException,
            MojoFailureException {
        File pom = getTestFile(pathToPom);
        TestDocMojo mojo = (TestDocMojo) lookupMojo("testdoc", pom);
        mojo.execute();
    }

    private void deleteDocument(String documentation)
            throws Exception {
        File document = new File(documentation);
        if (document.exists()) {
            boolean deleted = document.delete();
            if (!deleted) {
                throw new Exception("Cannot delete existing document.");
            }
        }
    }

}
