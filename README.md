jsf-sandbox2
===========

jsf primefaces sandbox2 project using [Java Standard Web Application](https://jcp.org/en/jsr/detail?id=340), mainly using:

- [Apache Tomcat](http://tomcat.apache.org/whichversion.html) [Reference](https://wiki.apache.org/tomcat/Specifications)
    - [Tomcat Maven Plugin](http://tomcat.apache.org/maven-plugin.html)
    - mvn clean tomcat7:run
    - [Creates an executable jar of the application](http://tomcat.apache.org/maven-plugin-2.2/executable-war-jar.html)
    - mvn clean exec-war-only
    - java -jar target/jsf-sandbox2.jar

- [Eclipse Jetty](https://www.eclipse.org/jetty/documentation/9.3.14.v20161028/what-jetty-version.html) [Reference](https://www.eclipse.org/jetty/documentation/9.3.14.v20161028/jetty-javaee.html)
    - [Jetty Maven Plugin](https://www.eclipse.org/jetty/documentation/9.3.14.v20161028/jetty-maven-plugin.html)
    - mvn clean jetty:run

- If using resource filtering, use: ```tomcat7:run-war and jetty:run-exploded```

[Maven Build plugins](https://maven.apache.org/plugins/) properties:

```xml
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>1.8</java.version>
        <java.version>1.8</java.version>
        <servlet.version>3.1.0</servlet.version>
        <jsf.version>2.2.13</jsf.version>
        <primefaces.version>5.3</primefaces.version>
        <maven.compiler.plugin.version>3.3</maven.compiler.plugin.version>
        <maven.war.plugin.version>2.6</maven.war.plugin.version>
        <jetty.maven.plugin.version>9.2.14.v20151106</jetty.maven.plugin.version>
        <tomcat.maven.plugin.version>2.2</tomcat.maven.plugin.version>
    </properties>
```
