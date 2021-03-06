/**
 * Licensed to Cloudera, Inc. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  Cloudera, Inc. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloudera.flume.master;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.cloudera.flume.conf.FlumeConfiguration;
import com.google.common.base.Preconditions;

/**
 * Generates an execable that loads a set of configs.
 */
public class LoadConfigCommand {

  static Logger LOG = Logger.getLogger(LoadConfigCommand.class);

  /**
   * Actual Command execution.
   */
  public static Execable buildExecable() {
    return new Execable() {
      @Override
      public void exec(String[] argv) {
        Preconditions.checkArgument(argv.length == 0);
        FlumeMaster master = FlumeMaster.getInstance();
        String f = FlumeConfiguration.get().getMasterSavefile();
        try {
          master.getSpecMan().loadConfigFile(f);
        } catch (IOException e) {
          LOG.warn("Save Config " + f + " failed", e);
        }
      }
    };
  }

}
