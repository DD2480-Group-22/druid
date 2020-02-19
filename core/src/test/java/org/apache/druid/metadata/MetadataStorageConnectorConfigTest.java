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

package org.apache.druid.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

public class MetadataStorageConnectorConfigTest
{

  private MetadataStorageConnectorConfig createMetadataStorageConfig(
      boolean createTables,
      String host,
      int port,
      String connectURI,
      String user,
      String pwdString
  )
      throws IOException
  {
    return JSON_MAPPER.readValue(
        "{" +
        "\"createTables\": \"" + createTables + "\"," +
        "\"host\": \"" + host + "\"," +
        "\"port\": \"" + port + "\"," +
        "\"connectURI\": \"" + connectURI + "\"," +
        "\"user\": \"" + user + "\"," +
        "\"password\": " + pwdString + "," +
        "\"dbcp\": {\n" +
        "  \"maxConnLifetimeMillis\" : 1200000,\n" +
        "  \"defaultQueryTimeout\" : \"30000\"\n" +
        "}" +
        "}",
        MetadataStorageConnectorConfig.class
    );
  }

  @Test
  public void testEquals() throws IOException
  {
    MetadataStorageConnectorConfig metadataStorageConnectorConfig = createMetadataStorageConfig(
        true,
        "testHost",
        4000,
        "url",
        "user",
        "\"nothing\""
    );
    MetadataStorageConnectorConfig metadataStorageConnectorConfig2 = createMetadataStorageConfig(
        true,
        "testHost",
        4000,
        "url",
        "user",
        "\"nothing\""
    );
    
    MetadataStorageConnectorConfig msccCrafted1 = new MetadataStorageConnectorConfig()
    {
      @Override
      public String getHost()
      {
        return "mock";
      }
    };
    
    MetadataStorageConnectorConfig msccCrafted2 = new MetadataStorageConnectorConfig()
    {
      @Override
      public boolean isCreateTables()
      {
        return false;
      }
    };
    
    MetadataStorageConnectorConfig msccCrafted3 = new MetadataStorageConnectorConfig()
    {
      @Override
      public String getUser()
      {
        return "mock";
      }
    };
    
    MetadataStorageConnectorConfig msccCrafted4 = new MetadataStorageConnectorConfig()
    {
      @Override
      public String getConnectURI()
      {
        return "mock";
      }
    };
    
    String msccCrafted5 = "mock";
    
    Assert.assertTrue(metadataStorageConnectorConfig.equals(metadataStorageConnectorConfig2));
    Assert.assertTrue(metadataStorageConnectorConfig.hashCode() == metadataStorageConnectorConfig2.hashCode());
    Assert.assertFalse(metadataStorageConnectorConfig.equals(msccCrafted1));
    Assert.assertFalse(metadataStorageConnectorConfig.equals(msccCrafted2));
    Assert.assertFalse(metadataStorageConnectorConfig.equals(msccCrafted3));
    Assert.assertFalse(metadataStorageConnectorConfig.equals(msccCrafted4));
    Assert.assertFalse(metadataStorageConnectorConfig.equals(msccCrafted5));
  }

  private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

  @Test
  public void testMetadataStorageConnectionConfigSimplePassword() throws Exception
  {
    testMetadataStorageConnectionConfig(
        true,
        "host",
        1234,
        "connectURI",
        "user",
        "\"nothing\"",
        "nothing"
    );
  }

  @Test
  public void testMetadataStorageConnectionConfigWithDefaultProviderPassword() throws Exception
  {
    testMetadataStorageConnectionConfig(
        true,
        "host",
        1234,
        "connectURI",
        "user",
        "{\"type\":\"default\",\"password\":\"nothing\"}",
        "nothing"
    );
  }

  private void testMetadataStorageConnectionConfig(
      boolean createTables,
      String host,
      int port,
      String connectURI,
      String user,
      String pwdString,
      String pwd
  ) throws Exception
  {
    MetadataStorageConnectorConfig config = JSON_MAPPER.readValue(
        "{" +
        "\"createTables\": \"" + createTables + "\"," +
        "\"host\": \"" + host + "\"," +
        "\"port\": \"" + port + "\"," +
        "\"connectURI\": \"" + connectURI + "\"," +
        "\"user\": \"" + user + "\"," +
        "\"password\": " + pwdString +
        "}",
        MetadataStorageConnectorConfig.class
    );

    Assert.assertEquals(host, config.getHost());
    Assert.assertEquals(port, config.getPort());
    Assert.assertEquals(connectURI, config.getConnectURI());
    Assert.assertEquals(user, config.getUser());
    Assert.assertEquals(pwd, config.getPassword());
    Assert.assertNull(config.getDbcpProperties());
  }

  @Test
  public void testDbcpProperties() throws Exception
  {
    testDbcpPropertiesFile(
        true,
        "host",
        1234,
        "connectURI",
        "user",
        "{\"type\":\"default\",\"password\":\"nothing\"}",
        "nothing"
    );
  }
  private void testDbcpPropertiesFile(
          boolean createTables,
          String host,
          int port,
          String connectURI,
          String user,
          String pwdString,
          String pwd
  ) throws Exception
  {
    MetadataStorageConnectorConfig config = JSON_MAPPER.readValue(
            "{" +
                    "\"createTables\": \"" + createTables + "\"," +
                    "\"host\": \"" + host + "\"," +
                    "\"port\": \"" + port + "\"," +
                    "\"connectURI\": \"" + connectURI + "\"," +
                    "\"user\": \"" + user + "\"," +
                    "\"password\": " + pwdString + "," +
                    "\"dbcp\": {\n" +
                    "  \"maxConnLifetimeMillis\" : 1200000,\n" +
                    "  \"defaultQueryTimeout\" : \"30000\"\n" +
                    "}" +
                    "}",
            MetadataStorageConnectorConfig.class
    );

    Assert.assertEquals(host, config.getHost());
    Assert.assertEquals(port, config.getPort());
    Assert.assertEquals(connectURI, config.getConnectURI());
    Assert.assertEquals(user, config.getUser());
    Assert.assertEquals(pwd, config.getPassword());
    Properties dbcpProperties = config.getDbcpProperties();
    Assert.assertEquals(dbcpProperties.getProperty("maxConnLifetimeMillis"), "1200000");
    Assert.assertEquals(dbcpProperties.getProperty("defaultQueryTimeout"), "30000");
  }
}
