Migeran Samples
===============

This repository contains a number of samples that show how to create apps with [Migeran](http://www.migeran.com).

License
-------

The Apache License 2.0 applies to all samples in this repository.

   Copyright (c) 2015 Migeran Ltd

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

Using the Samples with Gradle
-----------------------------

* **Eclipse**: to create an Eclipse project open Terminal, go to the project's directory and execute

        ./gradlew eclipse

    After the project is generated, simply import it into Eclipse.

* **Android Studio/Idea**: to create an Android Studio/Idea project open Terminal, go to the project's directory and execute

        ./gradlew idea

    After the project is generated, simply open the project.

Using the Samples with Maven
----------------------------

* **Eclipse**: before using Maven, the project's build.gradle file _must_ be modified. Open the file in any text editor and replace this line

        sdk.maven('ios', 'com.migeran:migeran-ios:0.93.+')

    with this

        mavenProject = true

    Then open Terminal, go to the project's directory and execute

        mvn eclipse:eclipse 

    After the project is generated, simply import it into Eclipse.

More Information
----------------

#### About Migeran

Migeran is a development environment to create iOS Apps in Java. 
It specifically supports cross-platform iOS and Android development 
to minimize code duplication and development effort.

* Check out our website: http://www.migeran.com
* Read our blog: http://blog.migeran.com
* Follow us on Twitter: http://twitter.com/migeran
* Like us on Facebook: https://www.facebook.com/migeranltd
* Ask for help on [Google Groups](https://groups.google.com/forum/#!forum/migeran) 
or on [StackOverflow](http://stackoverflow.com/questions/ask?tags=migeran)

You may also [contact us directly on our website](http://www.migeran.com/contact.html).

#### Commercial Support

Migeran Ltd leads the development of the Migeran project 
and offers professional support packages, project assistance, 
consulting, and custom application development together with our partners.

If you are interested in our services, contact us 
[over the web](http://www.migeran.com/contact.html) or [in email](mailto:support@migeran.com).
