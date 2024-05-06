# EasyGecNG

EasyGecNG is a fork of [EasyGec](http://t.porret.free.fr/lienlogiciel.php?idmenu=60), modified for [Navigation Games](https://www.navigationgames.org/), a Boston-based non-profit focused on introducing the sport of orienteering to new audiences, especially school children. EasyGec was developed by Thierry Porret, and he has kindly given us permission to use and modify his source code.


Here is a link to a blog post on NavigationGames.org detailing goals for this project:
https://www.navigationgames.org/post/in-the-works-new-software-for-teaching-orienteering



<!-- TODO: figure out licensing oml -->
Copyright (c) 2023 Thierry Porret and Navigation Games.

GecoSI is distributed under the MIT license; some parts are released by SPORTident under the CC BY 3.0 license. 


# User Installation Instructions

If you want to install a pre-built version of this software (i.e. you are not a developer), please visit the [release page](https://www.navigationgames.org/about-3)., and download these dependencies:
- [Java 8](https://www.oracle.com/java/technologies/javase/javase8u211-later-archive-downloads.html) if on windows
- [SportIdent Driver](https://www.sportident.com/products/96-software/161-usb-driver.html)

# Developer Installation Instructions
### 1. Install Dependencies
You must have java8 installed.
- `jdk8-openjdk` on linux/mac. You must _not_ have a java version higher than v21.
- download from [here](https://www.oracle.com/java/technologies/javase/javase8u211-later-archive-downloads.html) if on windows
You must also have the [SportIdent Driver](https://www.sportident.com/products/96-software/161-usb-driver.html) installed, for older SI Hardware. The new stuff supports HID and should work out of the box.

> Note: The BSF8 Stations work on linux with the [cp210x drivers](https://github.com/torvalds/linux/blob/master/drivers/usb/serial/cp210x.c), which have been in the kernel for at least 15 years. They should work on any linux device. See the support matrix HERE (TODO).

Linux: Add yourself to the correct dialout group. `sudo usermod -a -G dialout $USER` on debian-likes, `sudo usermod -a -G uucp $USER` on arch-likes. Log out, log in, check that the `uucp/dialout` appears in the results of the `group` command.

### 2. Clone the repository
`git clone git@github.com:qbowers/EasyGecNG.git`

### 3. Test build
`./gradlew build`

### 4. Run the App
`./gradlew run`

### 5. That's it!
run `./gradlew tasks` to list available tasks, including running unit tests and building an output jar. Modify available tasks in `app/build.gradle`

## IDE integration

### VSCode
Simply open the correct folder in the editor. Build/run/degub from terminal, or use the following extensions to get Gradle UI buttons:
- [Language Support for Java(TM) by Red Hat](https://open-vsx.org/extension/redhat/java)
- [Gradle for Java](https://open-vsx.org/extension/vscjava/vscode-gradle)
- If you're on the Microsoft-ified version of VSCode, try [this extension pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

### IntelliJ
1. Use the "New Project > Get from VCS" button. Put in the url of this git repo.
2. A popup stating "gradle build scripts found." Click `Load Gradle Project`
3. A menu should appear with the available Gradle tasks Double click `EasyGecNG > Tasks > application > run`
4. That's it!

## Generating Documentation
TODO :)
We will use Javadoc

## Generating Releases
TODO :)

### CI/CD
- main branch is always releasable.
- stable releases (the archive bundle with runnable jar, doc, other files...) are built and tagged from time to time from main.
- in the future, we'd like any push to the repository to trigger an automated build, which compile the code and runs tests. This would be implemented with something like GitHub Actions or Travis.

### Manually creating a Release
We need to figure out how to do this. The bundle would ideally include:
- help or doc folder with user documentation
- a jar for development version (???)
- a jar for stable release
- a distribution bundle including jar, user doc, and other files


# Development Guidelines
### Good Practices
- We should be using test-driven development, but we haven't figured out how to do that yet.
- Documentation should be provided using javadoc comments on classes and methods.

### Dependencies
These are managed automatically by Gradle, but also enumerated here. TODO: Licensing
- jSerialComm
- jDom
- jUnit4
- Mockito-core
- [GecoSI](https://github.com/qbowers/GecoSI)
  - jSerialComm