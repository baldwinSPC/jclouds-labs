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
package org.jclouds.profitbricks.http.parser.loadbalancer;

import org.jclouds.date.DateCodecFactory;
import org.jclouds.profitbricks.binder.BaseProfitBricksRequestBinder;
import org.jclouds.profitbricks.domain.LoadBalancer;
import org.jclouds.profitbricks.domain.LoadBalancerAlgorithm;
import org.jclouds.profitbricks.domain.ProvisioningState;
import org.jclouds.profitbricks.domain.Server;
import org.jclouds.profitbricks.http.parser.BaseProfitBricksResponseHandler;

public abstract class BaseLoadBalancerResponseHandler<T> extends BaseProfitBricksResponseHandler<T> {
    protected LoadBalancer.Builder builder;

    protected BaseLoadBalancerResponseHandler(DateCodecFactory dateCodec) {
        super(dateCodec);
        this.builder = LoadBalancer.builder();
    }

    @Override
    protected void setPropertyOnEndTag(String qName) {
        if ("loadBalancerId".equals(qName))
            builder.loadBalancerId(textToStringValue());
        else if ("loadBalancerName".equals(qName))
            builder.loadBalancerName(textToStringValue());
        else if ("LoadBalancerAlgorithm".equals(qName))
            builder.loadBalancerAlgorithm(LoadBalancerAlgorithm.fromValue(textToStringValue()));
        else if ("dataCenterId".equals(qName))
            builder.dataCenterId(textToStringValue());
        else if ("dataCenterVersion".equals(qName))
            builder.dataCenterVersion(textToStringValue());
        else if ("internetAccess".equals(qName))
            builder.internetAccess(textToBooleanValue());
        else if ("ip".equals(qName))
            builder.ip(textToStringValue());
        else if ("lanId".equals(qName))
            builder.lanId(textToStringValue());
        else if ("provisioningState".equals(qName))
            builder.provisioningState(ProvisioningState.fromValue(textToStringValue()));
        else if ("creationDate".equals(qName))
            builder.creationDate(textToIso8601Date());
        else if ("lastModificationTime".equals(qName))
            builder.lastModificationTime(textToIso8601Date());
    }
}
