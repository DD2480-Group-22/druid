/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.druid.java.util.common.granularity;

import org.apache.druid.java.util.common.logger.Logger;

/**
 * PeriodGranularity buckets data based on any custom time period
 */
public class CoverageTool
{

  public static boolean[] cases1 = new boolean[13];
  public static boolean[] cases2 = new boolean[13];

  private static final Logger log = new Logger(CoverageTool.class);

  public static void setBranch1(int index)
  {
    cases1[index] = true;
  }

  public static void setBranch2(int index)
  {
    cases2[index] = true;
  }

  public static void getResult1()
  {
    log.info("The cases1 function");
    int nrTrue = 0;
    for (int i = 0; i < cases1.length; i++) {
      log.info("Index: " + i + ", result: " + cases1[i]);
      if (cases1[i]) {
        nrTrue++;
      }
    }
    log.info("Number of visited branches: " + nrTrue);
    log.info("Number of missed branches: " + (cases1.length - nrTrue));
  }

  public static void getResult2()
  {
    log.info("The cases2 function");
    int nrTrue = 0;
    for (int i = 0; i < cases2.length; i++) {
      log.info("Index: " + i + ", result: " + cases2[i]);
      if (cases2[i]) {
        nrTrue++;
      }
    }
    log.info("Number of visited branches: " + nrTrue);
    log.info("Number of missed branches: " + (cases2.length - nrTrue));
  }

}

