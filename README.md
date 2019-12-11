# Kanta Service

![](https://img.shields.io/github/license/SKempin/Lyrics-King-React-Native.svg?style=flat-square)
[![Build Status](https://travis-ci.org/SKempin/Lyrics-King-React-Native.svg?branch=master)](https://travis-ci.org/SKempin/Lyrics-King-React-Native)

[Kanta Service](https://github.com/nhaancs/kanta-service) is a backend for [KANTA APP](https://github.com/nhaancs/kanta-react-native), which is an e-commerce application focus on recommendation system.

The Kanta Service is developed and supported by KANTA Team.

# Sub-Modules Organization

The project is splitted in several sub-modules:

* **app**:
Source code
* **conf**:
Configuration
* **project**:
Sbt plugin configuration.
* **public**:
Public folder with html, css, static files
* **sbt-dist**:
Services and framework related to WebEngine
* **test**:
Test module

# Building

Open Kanta Service in [Intellij](https://www.jetbrains.com/idea/)

## Requirements

Running the Kanta Service requires Java 8.
Depending on the features you want to use, you may need some third-party software, such as Libre Office and pdftohtml for document preview or ImageMagick for pictures. The list of third-party software is available in our Admin documentation: [Installing and Setting Up Related Software](https://github.com/nhaancs/kanta-service/blob/master/README.md).

Building the Nuxeo Platform requires the following tools:

* JDK 8 (Oracle's JDK or OpenJDK recommended)
* Sbt 0.13.18
* Git (obviously)

# Deploying

1. Get the source code:
```
git clone git@github.com:nhaancs/kanta-service.git
cd kanta-service
```
2. Build using Sbt:
```
sbt run
```

# Reporting issues

You can follow the developments in the Kanta Service project of our github issues tracker: [https://github.com/nhaancs/kanta-service/issues](https://github.com/nhaancs/kanta-service/issues).

# Licensing

You are welcome to use this however you wish within the MIT license.

