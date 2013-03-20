package org.robotframework.mavenplugin;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

/**
 * @author g.arora@iontrading.com
 * @version $Id$
 * @since 0.7.6
 */
public class AbstractRFMojoTestCase extends AbstractMojoTestCase {

    protected void executeLibdocWithPom(String pathToPom) throws Exception, MojoExecutionException, MojoFailureException {
        File pom = getTestFile(pathToPom);
        TestDocMojo mojo = (TestDocMojo) lookupMojo("testdoc", pom);
        mojo.execute();
    }

    protected void deleteDocument(String documentation) throws Exception {
        File document = new File(documentation);
        if (document.exists()) {
            boolean deleted = document.delete();
            if (!deleted) {
                throw new Exception("Cannot delete existing document.");
            }
        }
    }

}
