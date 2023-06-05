# BF.Collection

[![RELEASE](https://img.shields.io/github/v/release/Bloogefest/BF.Collection?style=for-the-badge)](https://github.com/Bloogefest/BF.Collection/releases/latest)
[![CI/CD](https://img.shields.io/github/actions/workflow/status/Bloogefest/BF.Collection/master.yml?label=CI%2FCD&style=for-the-badge)](https://github.com/Bloogefest/BF.Collection/actions/workflows/master.yml)

### Snapshot

#### Maven

```xml
<repository>
  <id>ossrh-snapshot</id>
  <name>OSSRH SNAPSHOT</name>
  <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
</repository>
```

```xml
<dependency>
  <groupId>com.bloogefest</groupId>
  <artifactId>collection</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

#### Gradle

```kotlin
repositories {
    maven {
        name = "OSSRH SNAPSHOT"
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}
```

```kotlin
dependencies {
    implementation("com.bloogefest:collection:1.0.0-SNAPSHOT")
}
```