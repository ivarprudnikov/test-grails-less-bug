grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: false,
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails{
	tomcat{
		jvmArgs = ["-server", "-XX:MaxPermSize=512m", "-XX:MaxNewSize=256m", "-XX:NewSize=256m",
 "-Xms768m", "-Xmx1024m", "-XX:SurvivorRatio=128", "-XX:MaxTenuringThreshold=0",
"-XX:+UseTLAB", "-XX:+UseConcMarkSweepGC", "-XX:+CMSClassUnloadingEnabled",
"-XX:+CMSIncrementalMode", "-XX:-UseGCOverheadLimit", "-XX:+ExplicitGCInvokesConcurrent"]
	}
}

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility
	
    repositories {
		
        inherits true

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
		
		mavenRepo "http://repository.codehaus.org/"
		mavenRepo "http://download.java.net/maven/2/"
		mavenRepo "http://repo.spring.io/milestone/"
		
    }

    dependencies {
        runtime 'mysql:mysql-connector-java:5.1.29'
    }

    plugins {
		
        // plugins for the build system only
        build ":tomcat:7.0.52.1"

        // plugins for the compile step
//        compile ":scaffolding:2.0.2"
//        compile ':cache:1.1.1'
//		compile ":cache-headers:1.1.6"
		
        // plugins needed at runtime but not for compilation
        runtime ":hibernate:3.6.10.9"
//        runtime ":database-migration:1.3.8"
		
		runtime ":resources:1.2.7"
		compile ":less-resources:1.3.3.2"
		
		// spring security
		compile ":spring-security-core:2.0-RC2"
		compile ":spring-security-ui:1.0-RC1", {
			excludes 'jquery'
		}
		compile ":mail:1.0.1"
		runtime ":jquery:1.11.0.2"
		runtime ":jquery-ui:1.10.3", {
			excludes 'jquery'
		}
		runtime ":famfamfam:1.0.1"
		
    }
}
