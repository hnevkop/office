Appliaction notes:

Application build on Spring MVC, Hibernate, JavaScript ( jQuery very little...)
Look And Feel by Twitter bootstrap styles.

NOTE:
- Hibernate Error during startup does not have any functional impact. Working on it. type:
  ERROR org.hibernate.tool.hbm2ddl.SchemaExport - HHH000389: Unsuccessful: alter table SUPPLIER_GROUPS drop constraint

 

An internal Monster department come up with request to build a simple web application where they can store office suppliers details. 

Functional requirements are:

1) A supplier is identified by Name, Address, Email Address and Telephone number.
COMPLETED

2) Each supplier belongs into one or more Groups. Groups are defined simply by its Name: "Cleaners", "Office supply (paper, envelopes, etc)", "Telephone service" and "Security".
COMPLETED

3) Web application should allow Listing, Adding, Updating and Deleting Suppliers. When Listing, Adding or Updating a Supplier, Group(s) must be shown (List) or possible to associate or de-associate with Supplier (Add, Update).
COMPLETED

4) Web application should allow listing all Suppliers in a system or listing Suppliers per Group.
COMPLETED

Technical requirements are:
1) Feel free to use any MVC framework.
2) To store all data, use in memory store - no need to store data in any database system. Data should be available only for web application lifetime and there is no need to store data changes between web application starts – (e.g. it can be stored in a Session object).
NOTES:
IT's USED HSQLDB for storing data.
There are two, three tables implemented with ManyToMany relation.  

3) On web application start, please add some initial dummy data from XML.
COMPLETED: There is a script import.sql where initial data are created

[Nice to have]
4) For Insert or Update, please implement server side validation for Email Address (validates format of email address) and Telephone number (validates 9 digit number, for simplicity please do not implement any special telephone number rules. In our case, valid telephone number is 9 digit number with no spaces). Server side validation should be executed by an AJAX call (please use jQuery) and user should be warned of possible errors before submitting form to server for processing.
NOT COMPLETED


[Nice to have]
5) Please make the application look & feel nice. Please do NOT use any predefined or default styles.
COMPLETED  PARTIALY with  Bootstrap, which is a predifined style among other sthings....  http://getbootstrap.com/2.3.2/ 
