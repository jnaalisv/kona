import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

println "Configuring logback for integration tests"

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{40}: %msg%n"
    }
}

root(INFO, ["STDOUT"])

logger("kona", INFO)
logger("org.springframework", INFO)
logger("org.hibernate", INFO)
logger("org.hibernate.tool.hbm2ddl.SchemaExport", DEBUG)
logger("org.hibernate.SQL", DEBUG)