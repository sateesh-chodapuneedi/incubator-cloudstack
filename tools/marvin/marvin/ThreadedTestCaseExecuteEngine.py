# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
# 
#   http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.

import unittest
import xmlrunner
import os
import sys
import logging
from functools import partial
import threading
import Queue

def testCaseLogger(message, logger=None):
    if logger is not None:
        logger.debug(message)

class ThreadedTests(threading.Thread):

   def __init__(self, testqueue, format, testResultLogFile, xmlDir):
      threading.Thread.__init__(self)
      self.testqueue = testqueue
      self.format = format
      self.testResultLogFile = testResultLogFile
      self.xmlDir = xmlDir

   def run(self):
       test=self.testqueue.get()
       if self.format == "text":
           unittest.TextTestRunner(stream=self.testResultLogFile, verbosity=2).run(test)
       elif self.format == "xml":
           xmlrunner.XMLTestRunner(output=self.xmlDir, verbose=True).run(test)

       self.testqueue.task_done()

class TestCaseExecuteEngine(object):
    def __init__(self, testclient, config, testcaseLogFile=None, testResultLogFile=None, format="text", xmlDir="xml-reports",num_threads=1):
        """
        Initialize the testcase execution engine, just the basics here
        @var testcaseLogFile: client log file
        @var testResultLogFile: summary report file  
        """
        self.testclient = testclient
        self.config = config
        self.logformat = logging.Formatter("%(asctime)s - %(levelname)s - %(name)s - %(message)s")
        self.loader = unittest.loader.TestLoader()
        self.suite = None
        self.format = format
        self.numthreads=num_threads

        if testcaseLogFile is not None:
            self.logfile = testcaseLogFile
            self.logger = logging.getLogger("TestCaseExecuteEngine")
            fh = logging.FileHandler(self.logfile) 
            fh.setFormatter(self.logformat)
            self.logger.addHandler(fh)
            self.logger.setLevel(logging.DEBUG)
        if testResultLogFile is not None:
            ch = logging.StreamHandler()
            ch.setLevel(logging.ERROR)
            ch.setFormatter(self.logformat)
            self.logger.addHandler(ch)
            fp = open(testResultLogFile, "w")
            self.testResultLogFile = fp
        else:
            self.testResultLogFile = sys.stdout
        if self.format == "xml"  and (xmlDir is not None):
            self.xmlDir = xmlDir
            
    def loadTestsFromDir(self, testDirectory):
        """ Load the test suites from a package with multiple test files """
        self.suite = self.loader.discover(testDirectory)
        self.injectTestCase(self.suite)
        
    def loadTestsFromFile(self, file_name):
        """ Load the tests from a single script/module """
        if os.path.isfile(file_name):
            self.suite = self.loader.discover(os.path.dirname(file_name), os.path.basename(file_name))
            self.injectTestCase(self.suite)
        
    def injectTestCase(self, testSuites):
        for test in testSuites:
            if isinstance(test, unittest.BaseTestSuite):
                self.injectTestCase(test)
            else:
                #logger bears the name of the test class
                testcaselogger = logging.getLogger("testclient.testcase.%s"%test.__class__.__name__)
                fh = logging.FileHandler(self.logfile) 
                fh.setFormatter(self.logformat)
                testcaselogger.addHandler(fh)
                testcaselogger.setLevel(logging.DEBUG)
                
                #inject testclient and logger into each unittest 
                setattr(test, "testClient", self.testclient)
                setattr(test, "config", self.config)
                setattr(test, "debug", partial(testCaseLogger, logger=testcaselogger))
                setattr(test.__class__, "clstestclient", self.testclient)
                if hasattr(test, "UserName"):
                    self.testclient.createNewApiClient(test.UserName, test.DomainName, test.AcctType)

    def run(self):
        testqueue=Queue.Queue()
        for test in self.suite:
            testqueue.put(test)

        thread_list=[]
        for i in range(num_threads):
            thread_list.append(ThreadedTests(testqueue, self.format, self.testResultLogFile, self.xmlDir))

        for thread in thread_list:
            thread.start()
        testqueue.join()
