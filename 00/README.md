Lecture 00 - Intro to Java
==========================

## Source Code

Each two-digit directory contains code similar to that which I (hope to) develop "live" during the Lecture with the same two-digit number, as well as any supplemental material.

The ``code_from_slides`` subdirectory will contain compilable code that you may see in the slides. Even if we don't cover the slides in class, I'll provide the slides and associated code for your review. 

* For Java classes, the filename is the public classname + ".java", e.g., if you see ``public class Vehicle`` on a slide you'll find the code in ``Vehicle.java``. 

* For any other file type, you should be able to find the filename somewhere near it in red on the slide.

## Building and Running Java Code

* If a directory contains a ``build.xml`` file, you can usually build it using ``ant`` or ``ant build`` and remove the .class files using ``ant clean``. Keep those ants busy!

* If no ``build.xml`` file is present, find the class contain the ``void main(String[] args)`` method (``grep main *`` usually does the trick) and compile and run it. For example, if Hello.java contains the ``void main(String[] args)`` method, then ``javac Hello.java`` will compile it and ``java Hello`` will run it. 

Use the Tab key to auto-complete commands, parameters, and filenames! 

## Searching for Keywords

To search for source files containing a specific word such as "Override", try e.g., ``grep Override *``. If you're searching for a phrase containing spaces such as "multiple inheritance", put them in double quotes, e.g., ``grep "multiple inheritance" *``.

You can also search through the slides and other PDF documents, too, if you put them in a common directory under Linux. To find "multiple inheritance" in a directory containing files with a .pdf extension, try ``pdfgrep "multiple inheritance" *.pdf``. Note that Linux cares about file name capitalization, so if you use the more Windows-like .PDF extension, use ``pdfgrep "multiple inheritance" *.PDF`` instead - or tell it to ignore case using the -i flag, for example, ``pdfgrep -i "multiple inheritance" *.pdf``

## Need help?

If you need assistance, I'm always available via email and Teams.
