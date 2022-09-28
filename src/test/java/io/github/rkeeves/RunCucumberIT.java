package io.github.rkeeves;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
// The below annotation pulls in the 'src/test/resources/cukes' directory's contents
// After compilation, the 'src/test/resources' contents
// gets shoved into 'target/test-classes' which is on the classpath
// so you can refer to 'target/test-classes/cukes' by just 'cukes'
@SelectClasspathResource("cukes")
// The below configuration tells where to look for step definition classes
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "io.github.rkeeves.steps," +
        "io.github.rkeeves.context," +
        "io.github.rkeeves.converter")
public class RunCucumberIT {
}
