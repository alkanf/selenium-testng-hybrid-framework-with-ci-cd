## Test Execution

Tests are executed using the **Grouping.xml** TestNG suite.

### Local Execution

Run the framework locally with Maven:

mvn clean test -DsuiteXmlFile=src/test/java/testCases/Grouping.xml

After execution, **Extent Reports** will be generated under the **target/reports** directory.
The report will automatically open in your default browser.

### Remote Execution (CI/CD)

When tests run remotely (for**GitHub Actions**), reports are not stored in the repository.

To view them:

1. Go to the **Actions** tab in GitHub.
2. Open the latest workflow run.
3. Download the report from the **Artifacts** section.

### Flexible Test Execution

By default, the framework runs **all test cases** using the `Grouping.xml` suite.

However, different testing strategies can be executed by **changing the TestNG XML suite file** (for example from GitHub Actions or Maven configuration).

The following test types are available but **not executed by default**:

* **Data Driven Testing (DDT):** 
* **Parallel Testing:**
* **Targeted Testing:**

To execute these tests in CI/CD, simply change the **XML suite file** used in the GitHub Actions workflow.

