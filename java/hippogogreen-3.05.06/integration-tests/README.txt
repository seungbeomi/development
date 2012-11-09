Running headless
================
To run the integration tests on a debian/ubuntu machine, install xvfb and firefox

  sudo apt-get install xvfb firefox

From the command line, run

  mvn -P headless integration-test


Adding tests
============
For each test case, export it with the

  org.onehippo.gogreen

package name.  Add the test to

  src/test/resources/tests.xml


Debugging tests
===============
The web applications, tested by selenium, can be run from the command line using

  mvn -P headless cargo.run

The browser that is executing the test can be opened on the users X server as

  mvn -P debug,headless integration-test


