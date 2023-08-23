# EasyGecNG - Development Guidelines

## Tools

- A Java IDE such as IntelliJ or Eclipse
- Release builder - we have not successfully set this up yet (Ant or Gradle)
- Git for source control; repo is on github
- Javadoc for documentation generation (aspirational; not used yet)

## Project

### Language/SDK

- Use simple Java to make it easier to read (POJO or close)
- Swing UI

### Libraries

Minimize use to keep it simple.

- GecoSI (include RXTX) for SPORTident communication
- ...

### Good Practices

- We should be using test-drive development, but we haven't figured out how to do that yet.
- Documentation should be provided using javadoc comments on classes and methods.

## Workflows

### Development

- fork the project
- make your changes
- make a pull request
- go through the review process
- your changes will be merged into the main repository after a successful review

### Continuous release

- main branch is always releasable.
- stable releases (the archive bundle with runnable jar, doc, other files...) are built and tagged from time to time from main.
- development happens in parallel branches, which are merged into main when finished. 
- in the future, we'd like any push to the repository to trigger an automated build, which compile the code and runs tests. 
  This would be implemented with something like GitHub Actions or Travis.

### Create a release bundle

We need to figure out how to do this. The NG developers switched to Intellij from Eclipse on the recommendation of 
an advisor. We were thinking to use Gradle but there are examples of GecoSI-dependent software that use Ant that 
we might emulate. The bundle would ideally include:

- help or doc folder with user documentation
- a jar for development version
- a jar for stable release
- a distribution bundle including jar, user doc, and other files
