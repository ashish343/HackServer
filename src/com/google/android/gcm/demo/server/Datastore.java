/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gcm.demo.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Simple implementation of a data store using standard Java collections.
 * <p>
 * This class is thread-safe but not persistent (it will lost the data when the
 * app is restarted) - it is meant just as an example.
 */
public final class Datastore {

  private static final HashMap<String, Map<String, String>> data = new HashMap<String, Map<String,String>>();
  private static final Logger logger =
      Logger.getLogger(Datastore.class.getName());

  private Datastore() {
    throw new UnsupportedOperationException();
  }

  /**
   * Registers a device.
   */
  public static void register(String regId, Map<String, String> params) {
    logger.info("Registering " + regId);
    synchronized (data) {
      data.put(regId, params);
    }
  }

  /**
   * Unregisters a device.
   */
  public static void unregister(String regId) {
    logger.info("Unregistering " + regId);
    synchronized (data) {
      data.remove(regId);
    }
  }
  
  /**
   * Unregisters a device.
 * @return 
   */
  public static Map<String, String> getParams(String regId) {
    logger.info("Unregistering " + regId);
    synchronized (data) {
      return data.get(regId);
    }
  }

  /**
   * Updates the registration id of a device.
   */
  public static void updateRegistration(String oldId, String newId, Map<String, String> params) {
    logger.info("Updating " + oldId + " to " + newId);
    synchronized (data) {
      data.remove(oldId);
      data.put(newId, params);
    }
  }

  /**
   * Gets all registered devices.
   */
  public static List<String> getDevices() {
    synchronized (data) {
      return new ArrayList<String>(data.keySet());
    }
  }

}
