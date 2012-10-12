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

import deployDataCenter
import TestCaseExecuteEngine
import ThreadedTestCaseExecuteEngine
from argparse import ArgumentParser
import os

if __name__ == "__main__":

    parser = ArgumentParser()
  
    parser.add_argument("-c", "--config", action="store", default="./datacenterCfg", dest="config",
                        help="The path where the json config file generated, by default is ./datacenterCfg")
    parser.add_argument("-d", "--directory", dest="testCaseFolder",
                        help="The test case directory")
    parser.add_argument("-r", "--result", dest="result",
                        help="Test result log file")
    parser.add_argument("-t", "--client", dest="testcaselog",
                        help="Test case log file")
    parser.add_argument("-l", "--load", dest="load", action="store_true",
                        help="Only load config, do not deploy, it will only run testcase")
    parser.add_argument("-f", "--file", dest="module",
                        help="Run tests in the given file")
    parser.add_argument("-x", "--xml", dest="xmlrunner",
                        help="Use the xml runner to generate xml reports and path to store xml files")
    parser.add_argument("-p", "--pthreads", dest="num_threads",
                        help="The number of threads used", default=1)
    
    results = parser.parse_args()
    
    testResultLogFile = None
    if results.result is not None:
        testResultLogFile = results.result
    
    testCaseLogFile = None
    if results.testcaselog is not None:
        testCaseLogFile = results.testcaselog
    deploy = deployDataCenter.deployDataCenters(results.config)    
    if results.load:
        deploy.loadCfg()
    else:
        deploy.deploy()
        
    format = "text"
    xmlDir = None
    if results.xmlrunner is not None:
        xmlDir = results.xmlrunner
        format = "xml"
    
    if results.testCaseFolder is None:
        if results.module is None:
            parser.print_usage()
            exit(1)
        else:
            if results.num_threads == 1:
                engine = TestCaseExecuteEngine.TestCaseExecuteEngine(deploy.testClient, testCaseLogFile, testResultLogFile, format, xmlDir)
            else:
                engine = ThreadedTestCaseExecuteEngine.TestCaseExecuteEngine(deploy.testClient, testCaseLogFile, testResultLogFile, format, xmlDir,results.num_threads)

            engine.loadTestsFromFile(results.module)
            engine.run()
    else:
       if results.num_threads == 1:
           engine = TestCaseExecuteEngine.TestCaseExecuteEngine(deploy.testClient, testCaseLogFile, testResultLogFile, format, xmlDir)
       else:
           engine = ThreadedTestCaseExecuteEngine.TestCaseExecuteEngine(deploy.testClient, testCaseLogFile, testResultLogFile, format, xmlDir, results.num_threads)

       engine.loadTestsFromDir(results.testCaseFolder)
       engine.run()
