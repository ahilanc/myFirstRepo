# myFirstRepo
Automation Repository

Introduction:
    This cucumber Framework includes Gherkin formatted feature files that specify the behavior of online products and associated step definitions to execute the scripts. It is used to orchestrate test runs against these executable specifications.

Courgette-JVM:
    Courgette-JVM is an extension of Cucumber-JVM with added capabilities to run cucumber tests in parallel on a feature level or on a scenario level. It also provides an option to automatically re-run failed scenarios. Thus it will help to reduce time consumptions in execution.
    
Key Features:
•	All features can be executed in parallel on independent threads in case of no dependencies.

•	All scenarios can be executed in parallel on independent threads (except dependencies)

•	Automatic re-run of failed scenarios.

•	Require only 1 annotated class to run all feature files in parallel.

•	Single report generation for all executed features including embedded files (Json and Html reports)

•	Single re-run listing all failed scenarios that occurred during parallel execution.

•	Integrate with Report Portal to support AI powered dashboards.

•	Searchable and paginated Courgette-JVM Html Report which includes all step definitions, embedded screenshots, thrown exceptions, pie chart and Courgette run information.

Execution Instruction:

Make sure that java installed and Maven should be available

Clone the repository: 

$ git clone https://github.com/ahilanc/myFirstRepo.git

Set up the Framework: Open IntelliJ and Import the Framework as Maven project.

•	Open IntelliJ IDEA and close any existing project.
•	From the Welcome screen, click Import Project
•	In View Menu - > Tool Windows -> Maven, Click on ‘Reimport all maven projects’

How to run Cucumber tests using Parallel Test Runner:

•	Press  ‘Add Configurations’ from the run/debug configuration dialog in Intellij IDEA
•	In the Run/Debug Configuration Dialog, click ‘+’ button on the toolbar. Select the desired configuration type as ‘Junit’
•	Select Cucumber Framework in Use classpath of module
•	Select ‘JAR manifest’ in Shorten command line
•	Check if SDK is not available in JRE, first go to Project Structure from File Menu and Select java 1.8 in Project SDK then select the   SDK in JRE from Run/Debug Configuration
•	Type “ParallelTestRunner” in class name and give same name in name
•	Click on Ok button
•	Run ‘ParallelTestRunner’ in Run Configuration
•	Once tests are executed, folder path to see the report – ( output -> courgette report -> Index.html with using Chrome)
•	Click on each scenario hyperlink to see the screenshots with execution status




