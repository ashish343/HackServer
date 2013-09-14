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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that registers a device, whose registration id is identified by
 * {@link #PARAMETER_REG_ID}.
 *
 * <p>
 * The client app should call this servlet everytime it receives a
 * {@code com.google.android.c2dm.intent.REGISTRATION C2DM} intent without an
 * error or {@code unregistered} extra.
 */
@SuppressWarnings("serial")
public class RegisterServlet extends BaseServlet {

  private static final String PARAMETER_REG_ID = "regId";
  private static final String PARAMETER_PHONE_NO = "phoneno";
  private static final String PARAMETER_NAME = "name";
  private static final String PARAMETER_EMAIL = "email";

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
	PrintWriter out = resp.getWriter();
    String regId = getParameter(req, PARAMETER_REG_ID);
    Map<String, String> params = new HashMap<String, String>();
    params.put("name", getParameter(req, PARAMETER_NAME));
    params.put("email", getParameter(req, PARAMETER_EMAIL));
    params.put("phoneno", getParameter(req, PARAMETER_PHONE_NO));
        
    out.println(getParameter(req, PARAMETER_REG_ID));
    out.println(getParameter(req, PARAMETER_NAME));
    out.println(getParameter(req, PARAMETER_EMAIL));
    out.println(getParameter(req, PARAMETER_PHONE_NO));
    
    Datastore.register(regId, params);
    setSuccess(resp);
  }

  /**
   * Displays the existing messages and offer the option to send a new one.
 * @throws ServletException 
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
		  PrintWriter out = resp.getWriter();
		  out.println("<h1>here</h1>");
		doPost(req, resp);
  }
}
