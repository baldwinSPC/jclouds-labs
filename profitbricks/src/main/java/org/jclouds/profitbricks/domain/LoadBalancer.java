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
package org.jclouds.profitbricks.domain;

import com.google.auto.value.AutoValue;
import org.jclouds.javax.annotation.Nullable;

import java.util.Date;
import java.util.List;

@AutoValue
public abstract class LoadBalancer {

    @Nullable
    public abstract String loadBalancerId();

    @Nullable
    public abstract String loadBalancerName();

    @Nullable
    public abstract LoadBalancerAlgorithm loadBalancerAlgorithm();

    @Nullable
    public abstract String dataCenterId();

    @Nullable
    public abstract String dataCenterVersion();

    @Nullable
    public abstract boolean internetAccess();

    @Nullable
    public abstract String ip();

    @Nullable
    public abstract String lanId();

    @Nullable
    public abstract ProvisioningState provisioningState();

    @Nullable
    public abstract Date creationTime();

    @Nullable
    public abstract Date lastModificationTime();

    public static LoadBalancer create(String loadBalancerId, String loadBalancerName, LoadBalancerAlgorithm loadBalancerAlgorithm,
            String dataCenterId, String dataCenterVersion, boolean internetAccess,
            String ip, String lanId, ProvisioningState provisioningState, Date creationTime, Date lastModificationTime) {
        return new AutoValue_LoadBalancer(loadBalancerId, loadBalancerName, loadBalancerAlgorithm, dataCenterId, dataCenterVersion, internetAccess, ip, lanId, provisioningState, creationTime, lastModificationTime);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public String loadBalancerId;

        public String loadBalancerName;

        public LoadBalancerAlgorithm loadBalancerAlgorithm;

        public String dataCenterId;

        public String dataCenterVersion;

        public boolean internetAccess;

        public String ip;

        public String lanId;

        public ProvisioningState provisioningState;

        @Nullable
        public Date creationTime;

        public Date lastModificationTime;

        public Builder loadBalancerId(String loadBalancerId) {
            this.loadBalancerId = loadBalancerId;
            return this;
        }

        public Builder loadBalancerName(String loadBalancerName) {
            this.loadBalancerName = loadBalancerName;
            return this;
        }

        public Builder loadBalancerAlgorithm(LoadBalancerAlgorithm loadBalancerAlgorithm) {
            this.loadBalancerAlgorithm = loadBalancerAlgorithm;
            return this;
        }

        public Builder dataCenterId(String dataCenterId) {
            this.dataCenterId = dataCenterId;
            return this;
        }

        public Builder dataCenterVersion(String dataCenterVersion) {
            this.dataCenterVersion = dataCenterVersion;
            return this;
        }

        public Builder internetAccess(boolean internetAccess) {
            this.internetAccess = internetAccess;
            return this;
        }

        public Builder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder lanId(String lanId) {
            this.lanId = lanId;
            return this;
        }

        public Builder creationTime(Date creationTime) {
            this.creationTime = creationTime;
            return this;
        }

        public Builder provisioningState(ProvisioningState provisioningState) {
            this.provisioningState = provisioningState;
            return this;
        }

        public Builder lastModificationTime(Date lastModificationTime) {
            this.lastModificationTime = lastModificationTime;
            return this;
        }

        public LoadBalancer build() {
            return LoadBalancer.create(loadBalancerId, loadBalancerName, loadBalancerAlgorithm, dataCenterId, dataCenterVersion, internetAccess, ip, lanId, provisioningState, creationTime, lastModificationTime);
        }

        public Builder fromLoadBalancer(LoadBalancer in) {
            return this.loadBalancerId(in.loadBalancerId()).loadBalancerName(in.loadBalancerName()).loadBalancerAlgorithm(in.loadBalancerAlgorithm())
                    .dataCenterId(in.dataCenterId()).dataCenterVersion(in.dataCenterVersion()).internetAccess(in.internetAccess())
                    .ip(in.ip()).lanId(in.lanId()).provisioningState(in.provisioningState()).creationTime(in.creationTime()).lastModificationTime(in.lastModificationTime());
        }
    }

    public static final class Request {

        public static CreatePayload.Builder creatingBuilder() {
            return new CreatePayload.Builder();
        }

        @AutoValue
        public abstract static class CreatePayload {

            public abstract String dataCenterId();

            public abstract String loadBalancerName();

            public abstract LoadBalancerAlgorithm loadBalancerAlgorithm();

            public abstract String ip();

            @Nullable
            public abstract String lanId();

            @Nullable
            public abstract String serverIds();

            public static CreatePayload create(String dataCenterId, String loadBalancerName, LoadBalancerAlgorithm loadBalancerAlgorithm, String ip, String lanId, String serverIds) {
                return new AutoValue_LoadBalancer_Request_CreatePayload(dataCenterId, loadBalancerName, loadBalancerAlgorithm, ip, lanId, serverIds);
            }

            public static class Builder {

                public String dataCenterId;
                public String loadBalancerName;
                public LoadBalancerAlgorithm loadBalancerAlgorithm;
                public String ip;
                public String lanId;
                public String serverIds;

                public Builder dataCenterId(String dataCenterId) {
                    this.dataCenterId = dataCenterId;
                    return this;
                }

                public Builder loadBalancerName(String loadBalancerName) {
                    this.loadBalancerName = loadBalancerName;
                    return this;
                }

                public Builder loadBalancerAlgorithm(LoadBalancerAlgorithm loadBalancerAlgorithm) {
                    this.loadBalancerAlgorithm = loadBalancerAlgorithm;
                    return this;
                }

                public Builder ip(String ip) {
                    this.ip = ip;
                    return this;
                }

                public Builder lanId(String lanId) {
                    this.lanId = lanId;
                    return this;
                }

                public Builder serverIds(String serverIds) {
                    this.serverIds = serverIds;
                    return this;
                }

                public CreatePayload build() {
                    return CreatePayload.create(dataCenterId, loadBalancerName, loadBalancerAlgorithm, ip, lanId, serverIds);
                }
            }
        }

        @AutoValue
        public abstract static class RegisterPayload {

            public abstract List<String> serverIds();

            public abstract String loadBalancerId();

            public static RegisterPayload create(List<String> serverIds, String loadBalancerId) {
                return new AutoValue_LoadBalancer_Request_RegisterPayload(serverIds, loadBalancerId);
            }

            public static class Builder {

                public List<String> serverIds;
                public String loadBalancerId;

                public Builder serverIds(List<String> serverIds) {
                    this.serverIds = serverIds;
                    return this;
                }

                public Builder loadBalancerId(String loadBalancerId) {
                    this.loadBalancerId = loadBalancerId;
                    return this;
                }

                public RegisterPayload build() {
                    return RegisterPayload.create(serverIds, loadBalancerId);
                }
            }
        }
        
         @AutoValue
        public abstract static class DeregisterPayload {

            public abstract List<String> serverIds();

            public abstract String loadBalancerId();

            public static DeregisterPayload create(List<String> serverIds, String loadBalancerId) {
                return new AutoValue_LoadBalancer_Request_DeregisterPayload(serverIds, loadBalancerId);
            }

            public static class Builder {

                public List<String> serverIds;
                public String loadBalancerId;

                public Builder serverIds(List<String> serverIds) {
                    this.serverIds = serverIds;
                    return this;
                }

                public Builder loadBalancerId(String loadBalancerId) {
                    this.loadBalancerId = loadBalancerId;
                    return this;
                }

                public DeregisterPayload build() {
                    return DeregisterPayload.create(serverIds, loadBalancerId);
                }
            }
        }
    }
}
