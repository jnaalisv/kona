package kona.build.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.testing.Test

public class IntegrationTestPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.apply plugin: JavaPlugin

        project.sourceSets {
            integrationTest {
                java.srcDir 'src/integrationTest/java'
                resources.srcDir 'src/integrationTest/resources'

                compileClasspath += main.output + test.output
                runtimeClasspath += main.output + test.output
            }
        }

        project.configurations {
            integrationTestCompile.extendsFrom testCompile
            integrationTestRuntime.extendsFrom testRuntime
        }

        def testTask = project.tasks.create 'integrationTest', Test

        testTask.group = JavaBasePlugin.VERIFICATION_GROUP
        testTask.testClassesDir = project.sourceSets.integrationTest.output.classesDir
        testTask.classpath = project.sourceSets.integrationTest.runtimeClasspath
        testTask.reports.html.destination = new File(project.reporting.baseDir, "integrationTest")
        testTask.reports.junitXml.destination = new File(project.buildDir, "integrationTest-results")

        testTask.testLogging {
                events "skipped", "failed"
        }

        project.tasks.check.dependsOn(testTask)
    }
}