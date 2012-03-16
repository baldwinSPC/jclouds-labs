/*
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.vcloud.director.v1_5.domain.ovf;

import static com.google.common.base.Objects.equal;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.collect.ImmutableList;

/**
 * Specifies the order in which entities in a VirtualSystemCollection are powered on and shut down
 *
 * <pre>
 * &lt;complexType name="StartupSection_Type" /&gt;
 * </pre>
 */
@XmlRootElement(name = "StartupSection")
public class StartupSection extends SectionType {

   public static Builder<?> builder() {
      return new ConcreteBuilder();
   }

   public Builder<?> toBuilder() {
      return builder().fromStartupSection(this);
   }

   private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
   }
   
   public static class Builder<B extends Builder<B>> extends SectionType.Builder<B> {

      private List<StartupSectionItem> item = Collections.emptyList();
      private List<Object> any = Collections.emptyList();

      /**
       * @see StartupSection#getItem()
       */
      public B item(List<StartupSectionItem> item) {
         this.item = item;
         return self();
      }

      /**
       * @see StartupSection#getAny()
       */
      public B any(List<Object> any) {
         this.any = any;
         return self();
      }

      @Override
      public StartupSection build() {
         return new StartupSection(this);
      }
      
      public B fromStartupSection(StartupSection in) {
         return fromSectionType(in).item(item).any(any);
      }
   }

   @XmlElement(name = "Item")
   private List<StartupSectionItem> items;
   @XmlAnyElement(lax = true)
   private List<Object> any;

   protected StartupSection() {
      // For JAXB
   }

   public StartupSection(Builder<?> builder) {
      super(builder);
      this.items = (items != null) ? ImmutableList.<StartupSectionItem>copyOf(builder.item) : Collections.<StartupSectionItem>emptyList();
      this.any = (any != null) ? ImmutableList.<Object>copyOf(builder.any) : Collections.<Object>emptyList();
   }

   /**
    * Gets the value of the item property.
    */
   public List<StartupSectionItem> getItem() {
      return items;
   }

   /**
    * Gets the value of the any property.
    */
   public List<Object> getAny() {
      return any;
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      StartupSection that = StartupSection.class.cast(o);
      return super.equals(that) &&
            equal(this.items, that.items) && equal(this.any, that.any);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(super.hashCode(), items, any);
   }

   @Override
   public ToStringHelper string() {
      return super.string().add("item", items).add("any", any);
   }
}
