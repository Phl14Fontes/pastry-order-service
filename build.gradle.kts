plugins {
	java
	id("org.springframework.boot") version "3.3.7"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.flywaydb.flyway") version "10.18.0"
}

group = "com.mba"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("org.springframework.kafka:spring-kafka:3.3.1")

	// Slf4J
	implementation("org.apache.logging.log4j:log4j-api")
	implementation("org.apache.logging.log4j:log4j-core")

	// ULID
	implementation("de.huxhorn.sulky:de.huxhorn.sulky.ulid:8.2.0")

	// Database
	implementation("org.flywaydb:flyway-database-postgresql:10.15.0")
	runtimeOnly("org.postgresql:postgresql")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	implementation("org.springdoc:springdoc-openapi-starter-common:2.6.0")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
