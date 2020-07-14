# E-Learning Platform REST Web Service

## Overview
This is a Spring boot based application that was built for learning and practicing purposes. 

## Some Use Cases

* Guest users can login or create an account.
* Guest users can browse available courses by category or by tags or by learning paths.
* Authentified users can create or co-create a course with other people and manage it. Assign the course to a category and assigns tags to it. Course creators also manage the course modules.
* Authentified users can enroll, complete a course, or unenroll from a course.
* Admin users manage everything.

## Technical features

* Layered Architecture
* Implemented the Repository pattern for the DAL with Hibernate
* Includes Integration tests for the DAL
* Authentification and authorization with JWT and Spring security
* Secured service endpoints

## ER Diagram 

![ER Diagram](https://i.imgur.com/oztSrJq.png)