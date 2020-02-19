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

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.druid.java.util.common.StringUtils;

import java.util.Properties;

/**
 */
public class MetadataStorageConnectorConfig
{
  @JsonProperty
  private boolean createTables = true;

  @JsonProperty
  private String host = "localhost";

  @JsonProperty
  private int port = 1527;

  @JsonProperty
  private String connectURI;

  @JsonProperty
  private String user = null;

  @JsonProperty("password")
  private PasswordProvider passwordProvider;

  @JsonProperty("dbcp")
  private Properties dbcpProperties;

  public static boolean[] branch = {false, false, false, false};
  
  public static void printBranch() {
    for(int i = 0; i < 4; ++i) {
      System.out.print(i+": "+branch[i]+", ");
    }
    System.out.println();
  }
  
  public boolean isCreateTables()
  {
    return createTables;
  }

  public String getHost()
  {
    return host;
  }

  public int getPort()
  {
    return port;
  }

  @JsonProperty
  public String getConnectURI()
  {
    if (connectURI == null) {
      return StringUtils.format("jdbc:derby://%s:%s/druid;create=true", host, port);
    }
    return connectURI;
  }

  public String getUser()
  {
    return user;
  }

  public String getPassword()
  {
    return passwordProvider == null ? null : passwordProvider.getPassword();
  }

  public Properties getDbcpProperties()
  {
    return dbcpProperties;
  }

  @Override
  public String toString()
  {
    return "DbConnectorConfig{" +
           "createTables=" + createTables +
           ", connectURI='" + getConnectURI() + '\'' +
           ", user='" + user + '\'' +
           ", passwordProvider=" + passwordProvider +
           ", dbcpProperties=" + dbcpProperties +
           '}';
  }

  private boolean checkGetHostNotEqual(MetadataStorageConnectorConfig o)
  {
    return getHost() != null ? !getHost().equals(o.getHost()) : o.getHost() != null;
  }
  
  private boolean checkGetConnectUriNotEqual(MetadataStorageConnectorConfig o)
  {
    return getConnectURI() != null
        ? !getConnectURI().equals(o.getConnectURI()) 
        : o.getConnectURI() != null;
  }
  
  private boolean checkGetUserNotEqual(MetadataStorageConnectorConfig o)
  {
    return getUser() != null 
        ? !getUser().equals(o.getUser())
        : o.getUser() != null;
  }
  
  private boolean checkGetDCBPropertiesNotEqual(MetadataStorageConnectorConfig o)
  {
    return getDbcpProperties() == null
        ? o.getDbcpProperties() != null
        : !getDbcpProperties().equals(o.getDbcpProperties());
  }
  
  private boolean checkPasswordProviderEqual(MetadataStorageConnectorConfig o)
  {
    return passwordProvider != null
        ? passwordProvider.equals(o.passwordProvider)
        : o.passwordProvider == null;
  }
  
  private boolean checkIsCreateTablesNotEqual(MetadataStorageConnectorConfig o)
  {
    return isCreateTables() != o.isCreateTables();
  }
  
  private boolean checkGetPortNotEqual(MetadataStorageConnectorConfig o)
  {
    return getPort() != o.getPort();
  }
  
  @Override
  public boolean equals(Object o)
  {
    branch[0] = True;
    if (this == o) {
      branch[1] = True;
      return true;
    }
    if (!(o instanceof MetadataStorageConnectorConfig)) {
      branch[2] = True;
      return false;
    }

    MetadataStorageConnectorConfig that = (MetadataStorageConnectorConfig) o;

    if (checkIsCreateTablesNotEqual(that) &&
        checkGetPortNotEqual(that) &&
        checkGetHostNotEqual(that) &&
        checkGetConnectUriNotEqual(that) &&
        checkGetUserNotEqual(that) &&
        checkGetDCBPropertiesNotEqual(that)
        ) {
      branch[3] = True;
      return false;
    }
    return checkPasswordProviderEqual(that);

  }

  @Override
  public int hashCode()
  {
    int result = (isCreateTables() ? 1 : 0);
    result = 31 * result + (getHost() != null ? getHost().hashCode() : 0);
    result = 31 * result + getPort();
    result = 31 * result + (getConnectURI() != null ? getConnectURI().hashCode() : 0);
    result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
    result = 31 * result + (passwordProvider != null ? passwordProvider.hashCode() : 0);
    result = 31 * result + (dbcpProperties != null ? dbcpProperties.hashCode() : 0);
    return result;
  }
}
