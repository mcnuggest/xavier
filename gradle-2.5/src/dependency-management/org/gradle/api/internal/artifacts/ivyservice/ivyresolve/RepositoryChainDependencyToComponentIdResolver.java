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

package org.gradle.api.internal.artifacts.ivyservice.ivyresolve;

import org.gradle.api.Transformer;
import org.gradle.api.artifacts.ModuleVersionSelector;
import org.gradle.api.internal.artifacts.DefaultModuleVersionIdentifier;
import org.gradle.api.internal.artifacts.ivyservice.ivyresolve.strategy.VersionSelectorScheme;
import org.gradle.internal.component.external.model.DefaultModuleComponentIdentifier;
import org.gradle.internal.component.external.model.ModuleComponentResolveMetaData;
import org.gradle.internal.component.model.DependencyMetaData;
import org.gradle.internal.resolve.resolver.DependencyToComponentIdResolver;
import org.gradle.internal.resolve.result.BuildableComponentIdResolveResult;

public class RepositoryChainDependencyToComponentIdResolver implements DependencyToComponentIdResolver {
    private final VersionSelectorScheme versionSelectorScheme;
    private final DynamicVersionResolver dynamicRevisionResolver;

    public RepositoryChainDependencyToComponentIdResolver(VersionSelectorScheme versionSelectorScheme, VersionedComponentChooser componentChooser, Transformer<ModuleComponentResolveMetaData, RepositoryChainModuleResolution> metaDataFactory) {
        this.versionSelectorScheme = versionSelectorScheme;
        this.dynamicRevisionResolver = new DynamicVersionResolver(componentChooser, metaDataFactory);
    }

    public void add(ModuleComponentRepository repository) {
        dynamicRevisionResolver.add(repository);
    }

    public void resolve(DependencyMetaData dependency, BuildableComponentIdResolveResult result) {
        ModuleVersionSelector requested = dependency.getRequested();
        if (versionSelectorScheme.parseSelector(requested.getVersion()).isDynamic()) {
            dynamicRevisionResolver.resolve(dependency, result);
        } else {
            DefaultModuleComponentIdentifier id = new DefaultModuleComponentIdentifier(requested.getGroup(), requested.getName(), requested.getVersion());
            DefaultModuleVersionIdentifier mvId = new DefaultModuleVersionIdentifier(requested.getGroup(), requested.getName(), requested.getVersion());
            result.resolved(id, mvId);
        }
    }
}
