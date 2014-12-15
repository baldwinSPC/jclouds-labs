/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.profitbricks.http.parser.datacenter;

import java.util.List;

import org.jclouds.date.DateCodecFactory;
import org.jclouds.profitbricks.domain.DataCenter;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

public class GetAllDataCentersResponseHandler extends BaseDataCenterResponseHandler<List<DataCenter>> {

   private final List<DataCenter> dataCenters;

   @Inject
   public GetAllDataCentersResponseHandler(DateCodecFactory dateCodec) {
      super(dateCodec);
      this.dataCenters = Lists.newArrayList();
      this.builder = DataCenter.builder();
   }

   @Override
   public List<DataCenter> getResult() {
      return dataCenters;
   }

   @Override
   public void endElement(String uri, String localName, String qName) throws SAXException {
      setPropertyOnEndTag(qName);
      if ("return".equals(qName)) {
	 dataCenters.add(builder.build());
	 builder = DataCenter.builder();
      }
      clearTextBuffer();
   }
}
