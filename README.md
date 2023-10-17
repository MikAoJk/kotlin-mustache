[![build main branch](https://github.com/MikAoJk/kotlin-mustache/actions/workflows/build.yml/badge.svg)](https://github.com/MikAoJk/kotlin-mustache/actions/workflows/build.yml)
![GitHub issues](https://img.shields.io/github/issues-raw/MikAoJk/kotlin-mustache)
![GitHub pull requests](https://img.shields.io/github/issues-pr-raw/MikAoJk/kotlin-mustache)
![GitHub](https://img.shields.io/github/license/MikAoJk/kotlin-mustache)

# kotlin-mustache
This project is for testing development with mustache, kotlin, gradle and ktor

## Technologies used
* Kotlin
* Gradle
* Ktor
* Mustache
* JDK 17
* Npm

#### Docs
Architectural Decision can be found here: https://mikaojk.github.io/kotlin-mustache/decisions/


## Getting Started
### Prerequisites
#### NPM
Make sure you have NPM installed
I recommend that you use, a package manager for installing npm:
https://nodejs.org/en/download/package-manager#nvm
or you can follow this guide:
https://docs.npmjs.com/downloading-and-installing-node-js-and-npm

```bash
npm --version
```

#### JDK 17
Make sure you have the Java JDK 17 installed
You can check which version you have installed using this command:
``` bash
java -version
 ```

#### Build and run tests
To build locally and run the integration tests you can simply run
``` bash
./gradlew clean build
```
or on windows
`gradlew.bat clean build`

## Run the main class in your favourite IDE(Intellij)
Go to src/main/kotlin/no/kartveit/Bootstrap.kt and run it

## Testing the login endpoint locally
Open a web browser and go to this side http://localhost:8080/login </br>
Username is joakim and password is 1234

### Upgrading the gradle wrapper
Find the newest version of gradle here: https://gradle.org/releases/ Then run this command:
Remeber to change $gradleVersjon to latest release

``` bash
./gradlew wrapper --gradle-version $gradleVersjon
```

## Inquiries
Questions related to the code or project can be asked as issues here on GitHub.
