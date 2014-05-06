lunchlunchandroid
=================

This is simply a stab at the proper patterns for an Android implementation of the mobile client for Lunch Buddy.

## Development setup
- I have been using the '[ADT(Android Development Tools)](http://developer.android.com/sdk/installing/bundle.html)' version of Eclipse to do development.
- I have been using Dagger dependency injection package to deal with dependency injection and testing in the Activities. For instructions on [setting up Dagger in Eclipse](http://www.thekeyconsultant.com/2013/09/adding-dagger-to-your-android-project.html)

## Testing
- Even though there is an Android SDK that provides you with classes and methods, most of the methods throw exceptions if you simply try to run them.
- Thus all unit tests must be run inside an Android Simulator. The ADT Eclipse provides you with tools to manage these simulators. 
- Also, simply running as JUnit will not work, you must run as Android Test.
- Run AllTests to run everything in the project..
- If anyone can figure out how to write a Watchman Test that will verify that the test classes are all in a Suite and that the Test Suites are all in the AllTests Suite, it would be greately appreciated.
	- This is extra hard since the test run on the Android Device so even brute forcing a recursive file path, class loader test is not possible.
