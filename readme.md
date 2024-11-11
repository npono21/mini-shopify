# Mini-Shopify
SYSC 4806 A Project

## Table of Contents
1. [Contributors](#contributors)
1. [Description](#description)
1. [Milestones](#milestones)
1. [Use Cases](#use-cases)
1. [Procedures](#procedures)
1. [Diagrams](#diagrams)
1. [Issues](#issues)
1. [Kanban](#kanban)

## Contributors

| Name               | Student Number |
|--------------------|----------------|
| Arthur Atangana    | 101005197      |
| Nicholai Ponomarev | 101182048      |
| Rebecca Elliott    | 101199034      |
| Michael De Santis  | 101213450      |

## Description

[Visit the Mini-Shopify web application!](https://sysc4806-minishopify-ergyb4hpcef7fufw.canadaeast-01.azurewebsites.net/)

The Mini-Shopify project is a web application, developed using the Java Spring Boot framework, that provides a simple and easy forum for merchants and customers to interact in an online marketplace. Merchants are able to create one or more shops where they may list various products for purchase. Customers may browse merchant shops to view and purchase items, or they may rely on the site's various search mechanisms to help them find the products they want. Customers may add items to their cart from a vendor's shop, and initiate purchase of the items in their shop through a transaction.

## Milestones
Project milestones.

### Milestone 1: Early Prototype (2024/11/11)
#### Goals
* [x] Project initialization and integration with Spring Boot
* [x] Project Kanban board initialized
* [x] GitHub Issues initialized
* [x] Automated deployment to Azure via GitHub Actions
* [x] Automated testing via GitHub Actions 
* [x] Front-end and back-end connectivity
* [x] At least one important use case implemented and functional

#### Completed Use Cases
1. Register a Merchant Account
2. Create a Shop

## Use Cases
Use Cases for this project used to drive the design and implementation.

### Use Case 1: Register a Merchant Account
* Primary Actor: 
    * New Merchant User
* Preconditions: 
    * None 
* Steps:
    1. In a browser, navigate to the [Mini-Shopify home page on Azure](https://sysc4806-minishopify-ergyb4hpcef7fufw.canadaeast-01.azurewebsites.net/).
    1. Click the __I am a Merchant Login__ Button.
    1. Click the __Register here__ button to begin the registration process.
    1. Complete the __Create your account__ form by supplying the following fields:
        * __Username or Email__
        * __Password__
    1. Click the __Create Mini-Shopify Account__ button to complete merchant registration.
    1. Peruse the merchant dashboard created for the merchant account.
* Status: 
    * Implemented in Milestone 1

### Use Case 2: Create a Shop
* Primary Actor: 
    * Merchant User
* Preconditions:
    * User has registered as a Merchant and is logged in to their account, as per Use Case 1 above.
* Steps:
    1. From the merchant dashboard, click the __Create a Shop__ button.
    1. Complete the resultant form with the following fields:
        * __Shop Name__
        * __Shop Description__
    1. Create the shop by clicking the __Create Shop__ button.
    1. View the shop icon created under the __My Shops__ section of the merchant dashboard.
* Status:
    * Implemented in Milestone 1

## Procedures
Relevant retrieval, build, and usage procedures for this project.

### Source Code Retrieval
Retrieve this repository's source code through one of the following methods:
* Clone using HTTPS:
    - `git https://github.com/ArthurAtangana/SYSC3303A_Project.git` 
* Clone using SSH:
    - `git clone git@github.com:ArthurAtangana/SYSC3303A_Project.git`
* Clone using GitHub CLI:
    - `gh repo clone npono21/mini-shopify`
* Download the `zip` archive from the [GitHub Repository](https://github.com/npono21/mini-shopify).

### Building and Executing the Project Locally
To build and execute the project locally in IntelliJ, select the `AccessingDataJpaApplication.java` class in the _Project_ browser, and select __Run__ (`SHIFT + F10`).

### Running Unit and Integration Tests
Unit and integration tests are automatically executed upon every mainline and feature branch _push_ to the remote repository as a GitHub Action; however, tests may also be run locally.
If you wish to execute the entire suite of unit and integration tests locally under Maven, you may issue the following command from the project's root:
```bash
mvn -B test --file pom.xml
```

## CI/CD/CT

### Continuous Integration (CI)
The Mini-Shopify project is being developed with Continuous Integration (CI) (aka. Trunk-Based Development) practices, as described by [Fowler](https://www.martinfowler.com/articles/continuousIntegration.html). 
All source code is version controlled by `git`, with additional CI functionality provided by GitHub.
Once developers have taken their sprint issues at weekly scrums, development branches for each issue are forked from the repository's [mainline](https://github.com/npono21/mini-shopify/tree/main).
When developer's are ready to commit their changes, they issue a __Pull Request (PR)__ for the development branch directly against the project mainline, with each PR subject to code reviews by at least 2 other developers.
When a PR is successfully merged, corresponding GitHub issues are closed.

### Continuous Deployment (CD)
This project also takes advantages of the Continuous Deployment (CD) capabilities provided by GitHub Actions.
Upon every successful merge to the repository's [mainline](https://github.com/npono21/mini-shopify/tree/main), the updated application is automatically deployed to [Azure](https://sysc4806-minishopify-ergyb4hpcef7fufw.canadaeast-01.azurewebsites.net/).
The GitHub Actions Workflow for deployment is defined in the file `main_sysc4806-shopify.yml`, and may be actively monitored or later reviewed [here](https://github.com/npono21/mini-shopify/actions/workflows/main_sysc4806-minishopify.yml).

### Continuous Testing (CT)
Unit and integration tests are automatically executed upon every mainline and feature branch _push_ to the remote repository as a GitHub Action. The Workflow for these tests are defined in the file `run-tests.yml`, and may be actively monitored or later reviewed [here](https://github.com/npono21/mini-shopify/actions/workflows/run-tests.yml).
__N.B.__ If tests do _not_ pass on the project's mainline, this is because they are caused by known bugs tracked in our project's [GitHub Issues](https://github.com/npono21/mini-shopify/issues) and [Kanban](https://github.com/users/npono21/projects/2) board that are actively being worked on. 

## Diagrams
Design diagrams generated for this project.

### UML Class Diagram
The current UML class diagram for the project is visualized below:

TODO

### Database Schema
The current relational database schema design for the project is visualized below:

TODO


## Issues
For a list of all issues, please refer to the [GitHub Issues](https://github.com/npono21/mini-shopify/issues) page on this project's repository.

## Kanban
To view the Kanban board for this project, please refer to the [GitHub Kanban](https://github.com/users/npono21/projects/2) page on this project's repository.
