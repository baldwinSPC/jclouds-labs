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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import org.jclouds.profitbricks.ProfitBricksApi;
import org.jclouds.profitbricks.domain.LoadBalancer;
import org.jclouds.profitbricks.internal.BaseProfitBricksMockTest;
import org.testng.annotations.Test;

import java.util.List;

@Test(groups = "unit", testName = "LoadBalancerApiMockTest")
public class LoadBalancerApiMockTest extends BaseProfitBricksMockTest {

    @Test
    public void testGetAllLoadBalancers() throws Exception {
        MockWebServer server = mockWebServer();
        server.enqueue(new MockResponse().setBody(payloadFromResource("/loadbalancer/loadbalancers.xml")));

        ProfitBricksApi pbApi = api(server.getUrl(rootUrl));
        LoadBalancerApi api = pbApi.loadBalancerApi();

        try {
            List<LoadBalancer> loadBalancerList = api.getAllLoadBalancers();

            assertRequestHasCommonProperties(server.takeRequest());
            assertNotNull(loadBalancerList);
            assertTrue(loadBalancerList.size() == 2);

        } finally {
            pbApi.close();
            server.shutdown();
        }
    }

    @Test
    public void testGetServer() throws Exception {
        MockWebServer server = mockWebServer();
        server.enqueue(new MockResponse().setBody(payloadFromResource("/loadbalancer/loadbalancer.xml")));

        ProfitBricksApi pbApi = api(server.getUrl(rootUrl));
        LoadBalancerApi api = pbApi.loadBalancerApi();

        String id = "aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee";

        String content = " <ws:getLoadBalancer><loadBalancerId>" + id + "</loadBalancerId></ws:getLoadBalancer>";
        try {
            LoadBalancer loadBalancer = api.getLoadBalancer(id);

            assertRequestHasCommonProperties(server.takeRequest(), content);
            assertNotNull(loadBalancer);
            assertEquals(loadBalancer.loadBalancerId(), id);
        } finally {
            pbApi.close();
            server.shutdown();
        }
    }
}
