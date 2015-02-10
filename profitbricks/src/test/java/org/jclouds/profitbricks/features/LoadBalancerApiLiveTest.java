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
package org.jclouds.profitbricks.features;

import com.google.common.collect.Iterables;
import java.util.List;
import org.jclouds.profitbricks.BaseProfitBricksLiveTest;
import org.jclouds.profitbricks.domain.DataCenter;
import org.jclouds.profitbricks.domain.LoadBalancer;
import org.jclouds.profitbricks.domain.LoadBalancerAlgorithm;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

@Test(groups = "unit", testName = "LoadBalancerApiLiveTest")
public class LoadBalancerApiLiveTest extends BaseProfitBricksLiveTest {

    String dataCenterId;

    @Override
    protected void initialize() {
        super.initialize();
        List<DataCenter> dataCenters = api.dataCenterApi().getAllDataCenters();
        assertFalse(dataCenters.isEmpty(), "Must atleast have 1 datacenter available for server testing.");

        DataCenter dataCenter = Iterables.getFirst(dataCenters, null);

        dataCenterId = dataCenter.id();
    }

    @Test
    public void testCreateLoadBalancer() throws Exception {
        LoadBalancer.Request.CreatePayload payload = LoadBalancer.Request.creatingBuilder()
                .dataCenterId(dataCenterId)
                .loadBalancerName("testName")
                .loadBalancerAlgorithm(LoadBalancerAlgorithm.ROUND_ROBIN)
                .ip("0.0.0.1")
                .lanId("1")
                .serverIds("1,2")
                .build();

        LoadBalancer loadBalancer = api.loadBalancerApi().createLoadBalancer(payload);

        assertNotNull(loadBalancer);

    }

    @Test
    public void testGetAllLoadBalancers() throws Exception {
        List<LoadBalancer> loadBalancers = api.loadBalancerApi().getAllLoadBalancers();

        assertNotNull(loadBalancers);
        assertFalse(loadBalancers.isEmpty());
    }
}
