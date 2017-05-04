Webdriver + testng + pagefactory project
repo url: https://github.com/sheetalsingh/WebDriverPageFactory


#How to run framework:
Option 1: command line: single/multiple xml can be given
mvn clean test -DsuiteXmlFile=testng.xml,testngParallel.xml

Option 2: run any single xml manually






todos:
1. Fluentwait/ Wait
2. Reporting - extent/allure?
3. Remote server handling
4. Fileseparator, separate all path in one class
5. image comparison  - try Sikuli
6. email report  - use jenkin
7. Try to automate some good site like wiki or github

#issues:
1. retry listener show skip cases count as well - P3 [maven report show correct result, no skipped cases shown]
2. screenshot for particular id - done (some issue coming, not used in framework)  - P3
