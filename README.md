# BasaltSPO Test Application
The BasaltSPO Test Application is a Java-based CLI utility that compares binary packages of two branches and outputs the differences in JSON format. The application uses the REST API provided by Alt Linux to retrieve the list of binary packages for each branch.

The application takes two branch names as its input and performs the following comparisons:

1. Packages in the first branch but not in the second: The application outputs a JSON array of packages that exist in the first branch but not in the second.

2. Packages in the second branch but not in the first: The application outputs a JSON array of packages that exist in the second branch but not in the first.

3. Packages with a higher version in the first branch: The application outputs a JSON array of packages that have a higher version in the first branch compared to the second.
The application performs these comparisons for each architecture supported by the branch.

### Project Structure
The project consists of two modules:

1. CLIUtility: This module contains the CLI utility that interacts with the user and calls the necessary methods from the PackageComparator module.

2. PackageComparator: This module contains the logic for comparing the packages and generating the JSON output.

### JSON Output Format
The JSON output format is as follows:
```html
{
  "onlyIn1": [
    {
        "buildTime": int,
        "release": "string",
        "name": "string",
        "epoch": int,
        "source": "string",
        "arch": "string",
        "version": "string"
    },
    ...
  ],
    "onlyIn2": [
    {
        "buildTime": int,
        "release": "string",
        "name": "string",
        "epoch": int,
        "source": "string",
        "arch": "string",
        "version": "string"
    },
    ...
    ],
  "greaterIn1": [
    {
        "buildTime": int,
        "release": "string",
        "name": "string",
        "epoch": int,
        "source": "string",
        "arch": "string",
        "version": "string"
    },
    ...
  ]
}
```




### Usage
To use this CLI follow these steps:

1. Clone the repository:

```html
git clone git@github.com:11q3/BasaltSPO-TestApp.git
cd BasaltSPO-TestApp
```

2. Build the application:

```html
mvn clean install
```

3. Copy the library in the libraries folder (/usr/share/java)

```html
cp PackageComparator/target/PackageComparator-1.0-SNAPSHOT.jar /usr/share/java
```

4. Run the application with the required parameters:

```html
java -cp /usr/share/java/PackageComparator-1.0-SNAPSHOT.jar:CLIUtility/target/CLIUtility-1.0-SNAPSHOT.jar org.elevenqtwo.CLIUtility <branch1> <branch2> <outputFilePath>
```
