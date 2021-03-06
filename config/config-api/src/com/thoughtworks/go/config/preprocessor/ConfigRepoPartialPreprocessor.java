/*
 * Copyright 2016 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thoughtworks.go.config.preprocessor;

import com.rits.cloning.Cloner;
import com.thoughtworks.go.config.CruiseConfig;
import com.thoughtworks.go.config.GoConfigPreprocessor;
import com.thoughtworks.go.config.PartialsProvider;

public class ConfigRepoPartialPreprocessor implements GoConfigPreprocessor {

    private PartialsProvider partialsProvider;

    public ConfigRepoPartialPreprocessor() {
    }

    public void init(PartialsProvider partialsProvider) {
        this.partialsProvider = partialsProvider;
    }

    public void process(CruiseConfig cruiseConfig) {
        if (cruiseConfig.getPartials().isEmpty() && partialsProvider != null) {
            cruiseConfig.merge(new Cloner().deepClone(partialsProvider.lastPartials()));
        } else {
            cruiseConfig.merge(cruiseConfig.getPartials());
        }
    }
}
