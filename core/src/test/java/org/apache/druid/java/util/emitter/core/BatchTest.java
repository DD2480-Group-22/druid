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

package org.apache.druid.java.util.emitter.core;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import static org.easymock.EasyMock.mock;

public class BatchTest
{
  @Test(expected = IllegalStateException.class)
  public void testTryReleaseShared()
  {
    HttpPostEmitter hpeMock = mock(HttpPostEmitter.class);
    byte[] buffMock = new byte[4];

    Batch testBatch = new Batch(hpeMock, buffMock, 10);

    testBatch.tryReleaseShared(4);
  }

  @Test
  public void getCoverage()
  {
    // branch coverage tool check for DD2480
    int total = 5;
    int curr_branches = 0;

    for (int i = 0; i < total; i++) {
      if (Batch.branch[i]) {
        curr_branches++;
      }
    }

    int cov = curr_branches * 100 / total;

    Writer outwriter = new OutputStreamWriter(new FileOutputStream("coverage-report_TRYRELEASESHARED.txt"), StandardCharsets.UTF_8);
    outwriter.write("[+] Total coverage for the method 'tryReleaseShared' is %" + cov);
    outwriter.close();
  }
}
