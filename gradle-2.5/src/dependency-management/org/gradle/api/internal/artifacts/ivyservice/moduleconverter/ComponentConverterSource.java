/*
 * Copyright 2015 the original author or authors.
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
package org.gradle.api.internal.artifacts.ivyservice.moduleconverter;

import org.gradle.api.artifacts.Configuration;
import org.gradle.api.internal.artifacts.ModuleInternal;

import java.util.Set;

public class ComponentConverterSource {
    private final Set<? extends Configuration> configurations;
    private final ModuleInternal module;

    public ComponentConverterSource(Set<? extends Configuration> configurations, ModuleInternal module) {
        this.configurations = configurations;
        this.module = module;
    }

    public Set<? extends Configuration> getConfigurations() {
        return configurations;
    }

    public ModuleInternal getModule() {
        return module;
    }
}
