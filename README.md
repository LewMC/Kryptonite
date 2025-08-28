![Kryptonite](https://cdn.modrinth.com/data/cached_images/858d70fa091faac26ddc7b3abafcf83acafd1c5b.png)

- 💾 **Download Kryptonite** - https://lewmc.net/plugin/kryptonite
- ⭐ Enjoying Kryptonite? We'd love to hear your feedback on Spigot. Leave us a review [here](https://www.spigotmc.org/resources/kryptonite.116844/).

[Maven Repository](https://repo.lewmc.net) - [Documentation](https://wiki.lewmc.net/kryptonite.html) - [JavaDocs](https://lewmc.github.io/kryptonite) - [Code Analysis](https://sonarcloud.io/project/overview?id=LewMC_Kryptonite)

## Creating a local copy.

### Clone the repository.

Use the Git CLI to clone the repository from GitHub.

```sh
git clone https://github.com/lewmilburn/Kryptonite
```

### Install Maven.

You will need Maven to build the project to `./target`, you can install it at https://maven.apache.org/download.cgi.

### Set Java 21 as your `JAVA_HOME` variable.

You can validate this by running the command below and ensuring it returns Java 21.

```sh
java -version
```

### Build the project.

Navigate to the cloned repository and run:

```sh
mvn clean install
```

This will compile the project and package it into a JAR file in the `./target` directory.

## Contributing

We welcome contributions from the community. Please fork the repository, make your changes, and submit a pull request. Please see [https://github.com/LewMC/Kryptonite/issues](https://github.com/LewMC/Kryptonite/issues/1) before contributing.

## Licensing

Kryptonite is licensed under the Apache License 2.0. See [LICENSE](LICENSE) for more information.
