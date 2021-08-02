#
Social Networking KATA
Steps to build the project:

Clone the repo to local computer.
Open repo as a maven project by selecting pom.xml file.

#
Project Management Tool:Maven
#
Dependecies:

jUnit (included in pom.xml) testing framework.
PrettyTime (included in pom.xml) in order to save time, used this library for the time ago requirement.
##
This project mainly consists of 4 different classes.

##
Message
Message class is used to encapsulate the message object (content, timestapm , user etc).
##
Timeline
Timeline is a class which contains all the mapping of users and their posts.
##
User
User is used to create a User object. For simplicity and since I did not use a data store (DB) userId is not used, instead
the whole user object is stored instead of a reference to it, although in a real world setting this design would not be optimal.
##
Wall
Contains all the user messages, and the aggregated messages of the users being followed

Test cases are provided in the Test folder