/**
 * Copyright © 2016-2017 The Thingsboard Authors
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
package cn.neucloud.server.actors.rule;

import akka.actor.ActorRef;
import cn.neucloud.server.actors.msg.RuleEngineError;

public class ChainProcessingContext {

    private final ChainProcessingMetaData md;
    private final int index;
    private final RuleEngineError error;
    //private ToDeviceMsg response;


    public ChainProcessingContext(ChainProcessingMetaData md) {
        super();
        this.md = md;
        this.index = 0;
        this.error = RuleEngineError.NO_RULES;
    }

    private ChainProcessingContext(ChainProcessingContext other, int indexOffset, RuleEngineError error) {
        super();
        this.md = other.md;
        this.index = other.index + indexOffset;
        this.error = error;

        if (this.index < 0 || this.index >= this.md.chain.size()) {
            throw new IllegalArgumentException("Can't apply offset " + indexOffset + " to the chain!");
        }
    }

    public ActorRef getDeviceActor() {
        return md.originator;
    }

    public ActorRef getCurrentActor() {
        return md.chain.getRuleActorMd(index).getActorRef();
    }

    public boolean hasNext() {
        return (getChainLength() - 1) > index;
    }

    public boolean isFailure() {
        return (error != null && error.isCritical());
    }

    public ChainProcessingContext getNext() {
        return new ChainProcessingContext(this, 1, this.error);
    }

    public ChainProcessingContext withError(RuleEngineError error) {
        if (error != null && (this.error == null || this.error.getPriority() < error.getPriority())) {
            return new ChainProcessingContext(this, 0, error);
        } else {
            return this;
        }
    }

    public int getChainLength() {
        return md.chain.size();
    }


   /* public RuleEngineErrorMsg getError() {
        return new RuleEngineErrorMsg(md.chain., error);
    }*/
}
