# Lama Truffle

A simple Maven project configured for GraalVM native image compilation with Java 21.

This project demonstrates how to create a basic Java application that can be compiled to both:
- Standard JVM bytecode for development and testing
- Native image using GraalVM for production deployment

## Prerequisites

- Java 21+ (recommended: GraalVM for Java 21)
- Maven 3.6+
- GraalVM Native Image (if building native images)

## Quick Start

### 1. Build for JVM (Development)

```bash
mvn clean compile
mvn exec:java
```

Expected output:
```
Hello from Lama Truffle!
Java version: 21.0.x
Running on GraalVM: false
...
Application completed successfully!
```

### 2. Build Native Image

```bash
mvn clean package
native-image -jar target/lama-truffle-1.0.0.jar lama-truffle
./lama-truffle
```

Expected output when running native image:
```
Hello from Lama Truffle!
Java version: 21.0.x
Running on GraalVM: true
...
Application completed successfully!
```

## Project Structure

```
lama-truffle/
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── lama/
│       │           └── truffle/
│       │               └── App.java
│       └── resources/
│           └── application.properties
└── README.md
```

## Build Commands

### Standard JVM Build
```bash
mvn clean compile
mvn exec:java
```

### Native Image Build
```bash
mvn clean package
native-image -jar target/lama-truffle-1.0.0.jar lama-truffle
./lama-truffle
```

## GraalVM Configuration

The project is configured with the following GraalVM settings:

- **Java Version**: 21
- **Native Image Plugin**: Latest version
- **Build Arguments**:
  - `--no-fallback`: Ensures native image compilation succeeds
  - `--enable-preview`: Enables Java preview features
  - `--report-unsupported-elements-at-runtime`: Reports unsupported features at runtime
  - `--initialize-at-build-time`: Optimizes startup time

## Benefits of Native Image

- **Fast Startup**: Sub-millisecond startup times
- **Low Memory Usage**: Significantly reduced memory footprint
- **Smaller Binary**: Single executable file
- **No JVM Required**: Runs directly on the OS

## Troubleshooting

### Common Issues

1. **GraalVM Not Found**: Ensure GraalVM is installed and set as your JAVA_HOME
2. **Native Image Not Available**: Install GraalVM Native Image: `gu install native-image`
3. **Memory Issues**: Increase available memory for native image compilation

### Installation Commands

```bash
sdk install java 21-graal
gu install native-image
sdk default java 21-graal
```

## Next Steps

This basic project provides a foundation for:
- Adding more complex Java features
- Integrating with frameworks (Spring Boot, Micronaut, etc.)
- Adding unit tests
- Setting up CI/CD pipelines
- Containerizing with Docker

## License

This project is open source and available under the [MIT License](LICENSE).
