println "Configuring logback"

def logPattern = "%d{HH:mm:ss.SSS} [%thread] %highlight(%-5level) %logger{40}: %msg%n%ex{full, " +
        "org.eclipse.jetty, " +
        "sun.reflect, " +
        "java.lang, " +
        "java.util, " +
        "javax, " +
        "org.hibernate, " +
        "org.springframework, " +
        "com.fasterxml.jackson" +
        "CGLIB}"

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = logPattern
    }
}

root(INFO, ["STDOUT"])

logger("kona", INFO)
logger("org.springframework", INFO)
logger("org.hibernate", INFO)
logger("org.hibernate.tool.hbm2ddl.SchemaExport", DEBUG)
logger("org.hibernate.SQL", DEBUG)
