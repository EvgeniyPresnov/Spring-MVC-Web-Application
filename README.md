# Getting Started
This step illustrates the simple web application has implemented by Spring MVC features

# Installing 

## Clone the project

```
git clone git@github.com:ZhekaPresnov/Spring-Web-Application.git
cd Spring-Web-Application
```
## Running the tests

To perform **unit tests**, use the command: 
```
mvn test 
```
To perform **integration tests**, use the command:
```
mvn integration-test
``` 

## Build the project

```
mvn clean install
```

Start the embedded Tomcat server
```
mvn tomcat7:run
```

After that open the browser 

```
http://localhost:8080/books/list
```

We will have the following endpoints

| URI | HTTP Method | Details |
|-----------------|:----------------:|-----------:|
|/books/list        | GET  | To get the list of all the books|
|/books/{id}        | GET  | To get the book by id           |
|/books/add         | POST | To create the book              |
|/books/edit/{id}   | POST | To update the book              |
|/books/delete/{id} | GET  | To delete the book by id        | 


# Build With
* [Maven](https://maven.apache.org/) - Dependency Management

# License
```
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 ```
