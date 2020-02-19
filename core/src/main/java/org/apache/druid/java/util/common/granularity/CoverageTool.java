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
  private static final boolean[] truncate = new boolean[52];
  private static final boolean[] addAtKey = new boolean[15];
  private static final boolean[] createTimestampParser = new boolean[13];
  private static final boolean[] isOvershadowed = new boolean[14];

  private static final Logger log = new Logger(CoverageTool.class);

  public static void setBranchTruncate(int index)
  {
    truncate[index] = true;
  }

  public static void getResultTruncate()
  {
    log.info("The truncate function");
    int nrTrue = 0;
    for (int i = 0; i < truncate.length; i++) {
      log.info("Index: " + i + ", result: " + truncate[i]);
      if (truncate[i]) nrTrue++;
    }
    log.info("Number of visited branches: " + nrTrue);
    log.info("Number of missed branches: " + (truncate.length - nrTrue));
  }

  public static void setBranchAddAtKey(int index)
  {
    addAtKey[index] = true;
  }

  public static void getResultAddAtKey()
  {
    log.info("The addAtKey function");
    for (int i = 0; i < addAtKey.length; i++) {
      log.info("Index: " + i + ", result: " + addAtKey[i]);
    }
  }

  public static void setCreateTimestampParser(int index)
  {
    createTimestampParser[index] = true;
  }

  public static void getCreateTimestampParser()
  {
    log.info("The createTimestampParser function");
    for (int i = 0; i < createTimestampParser.length; i++) {
      log.info("Index: " + i + ", result: " + createTimestampParser[i]);
    }
  }

  public static void setisOvershadowed(int index)
  {
    isOvershadowed[index] = true;
  }

  public static void getisOvershadowed()
  {
    log.info("The isOvershadowed function");
    for (int i = 0; i < isOvershadowed.length; i++) {
      log.info("Index: " + i + ", result: " + isOvershadowed[i]);
    }
  }

}
