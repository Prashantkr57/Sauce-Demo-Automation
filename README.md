# SauceDemo Automation Project

Automated testing for SauceDemo web app using Selenium WebDriver & TestNG. Ensure app functionality across scenarios.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Usage](#usage)
- [TestNG Suite](#testng-suite)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The SauceDemo Automation Project is aimed at automating the testing of the SauceDemo web application to ensure its functionality remains intact across different scenarios. It utilizes Selenium WebDriver for browser automation and TestNG for test execution and reporting.

## Features

- LoginTest: Tests the login functionality of the SauceDemo application.
- InventoryTest: Tests various features related to the inventory management of the application.
- CartTest: Tests the functionality related to the shopping cart.
- CheckoutStepOneTest: Tests the checkout process - step one.
- CheckoutStepTwoTest: Tests the checkout process - step two.
- CheckoutCompleteTest: Tests the checkout completion page.

## Prerequisites

Before running the automated tests, ensure you have the following installed:

- Java Development Kit (JDK)
- Maven
- WebDriver-compatible browser (Chrome, Firefox, etc.)
- Selenium Grid (optional for cross-browser testing)

## Setup

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Update the WebDriver path in the project configuration file if necessary.
4. Install project dependencies using Maven: `mvn clean install`

## Usage

To run the automated tests, execute the following command:
Run tests: `mvn test`

