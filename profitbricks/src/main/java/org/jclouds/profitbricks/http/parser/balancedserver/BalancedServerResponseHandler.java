/*
 * Copyright 2015 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.profitbricks.http.parser.balancedserver;

import com.google.inject.Inject;
import org.jclouds.date.DateCodecFactory;
import org.jclouds.profitbricks.domain.BalancedServer;
import org.xml.sax.SAXException;

public class BalancedServerResponseHandler extends BaseBalancedServerParser<BalancedServer> {

    private boolean done = false;

    @Inject
    public BalancedServerResponseHandler(DateCodecFactory dateCodec) {
        super(dateCodec);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (done) {
            return;
        }
        setPropertyOnEndTag(qName);
        if ("return".equals(qName)) {
            done = true;
        }
        clearTextBuffer();
    }

    @Override
    public BalancedServer getResult() {
        return builder.build();
    }
}
