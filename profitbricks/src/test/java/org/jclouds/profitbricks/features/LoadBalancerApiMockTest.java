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

import com.google.common.collect.Lists;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import org.jclouds.profitbricks.ProfitBricksApi;
import org.jclouds.profitbricks.domain.LoadBalancer;
import org.jclouds.profitbricks.domain.LoadBalancerAlgorithm;
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
    public void testGetLoadBalancer() throws Exception {
        MockWebServer server = mockWebServer();
        server.enqueue(new MockResponse().setBody(payloadFromResource("/loadbalancer/loadbalancer.xml")));

        ProfitBricksApi pbApi = api(server.getUrl(rootUrl));
        LoadBalancerApi api = pbApi.loadBalancerApi();

        String id = "aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee";

        String content = "<ws:getLoadBalancer><loadBalancerId>" + id + "</loadBalancerId></ws:getLoadBalancer>";
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

    @Test
    public void testCreateLoadBalancer() throws Exception {
        MockWebServer server = mockWebServer();
        server.enqueue(new MockResponse().setBody(payloadFromResource("/loadbalancer/loadbalancer-create.xml")));

        ProfitBricksApi pbApi = api(server.getUrl(rootUrl));
        LoadBalancerApi api = pbApi.loadBalancerApi();

        String content = "<ws:createLoadBalancer>"
                + "<request>"
                + "<dataCenterId>aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeeee</dataCenterId>"
                + "<loadBalancerName>load-balancer-name</loadBalancerName>"
                + "<loadBalancerAlgorithm>ROUND_ROBIN</loadBalancerAlgorithm>"
                + "<ip>-ip</ip>"
                + "<lanId>lan-id</lanId>"
                + "<serverIds>server-ids</serverIds>"
                + "</request>"
                + "</ws:createLoadBalancer>";

        try {

            LoadBalancer loadbalancer = api.createLoadBalancer(LoadBalancer.Request.CreatePayload.create("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeeee", "load-balancer-name",
                    LoadBalancerAlgorithm.ROUND_ROBIN, "-ip", "lan-id", "server-ids"));

            assertRequestHasCommonProperties(server.takeRequest(), content);

        } finally {
            pbApi.close();
            server.shutdown();
        }
    }

    @Test
    public void testDeregisterLoadBalancer() throws Exception {
        MockWebServer server = mockWebServer();
        server.enqueue(new MockResponse().setBody(payloadFromResource("/loadbalancer/loadbalancer-deregister.xml")));

        ProfitBricksApi pbApi = api(server.getUrl(rootUrl));
        LoadBalancerApi api = pbApi.loadBalancerApi();

        String content = "<ws:deregisterServersOnLoadBalancer>"
                + "<serverIds>1</serverIds>"
                + "<serverIds>2</serverIds>"
                + "<loadBalancerId>load-balancer-id</loadBalancerId>"
                + "</ws:deregisterServersOnLoadBalancer>";

        try {
            List<String> serverIds = Lists.newArrayList();
            serverIds.add("1");
            serverIds.add("2");
            LoadBalancer loadbalancer = api.deregisterLoadBalancer(LoadBalancer.Request.DeregisterPayload.create(serverIds, "load-balancer-id"));

            assertRequestHasCommonProperties(server.takeRequest(), content);
        } finally {
            pbApi.close();
            server.shutdown();
        }
    }

    @Test
    public void testRegisterLoadBalancer() throws Exception {
        MockWebServer server = mockWebServer();
        server.enqueue(new MockResponse().setBody(payloadFromResource("/loadbalancer/loadbalancer-register.xml")));

        ProfitBricksApi pbApi = api(server.getUrl(rootUrl));
        LoadBalancerApi api = pbApi.loadBalancerApi();

        String content = "<ws:registerServersOnLoadBalancer>"
                + "<serverIds>1</serverIds>"
                + "<serverIds>2</serverIds>"
                + "<loadBalancerId>load-balancer-id</loadBalancerId>"
                + "</ws:registerServersOnLoadBalancer>";

        try {
            List<String> serverIds = Lists.newArrayList();
            serverIds.add("1");
            serverIds.add("2");
            LoadBalancer loadbalancer = api.registerLoadBalancer(LoadBalancer.Request.RegisterPayload.create(serverIds, "load-balancer-id"));

            assertRequestHasCommonProperties(server.takeRequest(), content);
        } finally {
            pbApi.close();
            server.shutdown();
        }
    }

    @Test
    public void testDeleteLoadBalancer() throws Exception {
        MockWebServer server = mockWebServer();
        server.enqueue(new MockResponse().setBody(payloadFromResource("/loadbalancer/loadbalancer-register.xml")));

        ProfitBricksApi pbApi = api(server.getUrl(rootUrl));
        LoadBalancerApi api = pbApi.loadBalancerApi();

        String loadBalancerId = "qwertyui-qwer-qwer-qwer-qwertyyuiiop";

        String content = "<loadBalancerId>" + loadBalancerId + "</loadBalancerId>";

        try {
            boolean done = api.deleteLoadbalancer(loadBalancerId);

            assertRequestHasCommonProperties(server.takeRequest(), content);
            assertTrue(done);
        } finally {
            pbApi.close();
            server.shutdown();
        }
    }
}
