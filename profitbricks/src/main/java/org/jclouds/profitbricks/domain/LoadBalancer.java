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

import java.util.Date;

@AutoValue
public abstract class LoadBalancer {

    public abstract String loadBalancerId();

    public abstract String loadBalancerName();

    public abstract LoadBalancerAlgorithm loadBalancerAlgorithm();

    public abstract String dataCenterId();

    public abstract String dataCenterVersion();

    public abstract boolean internetAccess();

    public abstract String ip();

    public abstract String lanId();

    public abstract ProvisioningState provisioningState();

    public abstract Date creationTime();

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

        public Date creationDate;

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

        public Builder creationDate(Date creationDate) {
            this.creationDate = creationDate;
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
            return LoadBalancer.create(loadBalancerId, loadBalancerName, loadBalancerAlgorithm, dataCenterId, dataCenterVersion, internetAccess, ip, lanId, provisioningState, creationDate, lastModificationTime);
        }

        public Builder fromLoadBalancer(LoadBalancer in) {
            return this.loadBalancerId(in.loadBalancerId()).loadBalancerName(in.loadBalancerName()).loadBalancerAlgorithm(in.loadBalancerAlgorithm())
                    .dataCenterId(in.dataCenterId()).dataCenterVersion(in.dataCenterVersion()).internetAccess(in.internetAccess())
                    .ip(in.ip()).lanId(in.lanId()).provisioningState(in.provisioningState()).creationDate(in.creationTime()).lastModificationTime(in.lastModificationTime());
        }
    }
}
