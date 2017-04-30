Webdriver + testng + pagefactory project

repo url: https://github.com/sheetalsingh/WebDriverPageFactory


todos:
Threadlocal for parallel execution
Logging - done
Fluentwait
Reporting - extent
Listeners - retry 

remote server handling with switch
screenshot for particular id
image comparison



log4j:
config root logger:
log4j.rootLogger=ALL, DEBUG, INFO, WARN, ERROR, FATAL, OFF,CONSOLE,FILE
log4j.rootLogger=DEBUG, ConsoleAppender, FileAppender

config indiv logger
log4j.logger.com.mycompany.DatabaseUtil=INFO, ConsoleAppender, FileAppender

calling indiv logger
Logger logger1 = Logger.getLogger(“com.mycompany.MyClass”);
Logger logger2 = Logger.getLogger(MyClass.class);





