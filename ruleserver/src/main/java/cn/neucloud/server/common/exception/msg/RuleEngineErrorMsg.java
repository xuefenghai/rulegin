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
package cn.neucloud.server.common.exception.msg;

import cn.neucloud.server.actors.msg.MsgType;
import cn.neucloud.server.actors.msg.RuleEngineError;
import lombok.Data;

/**
 * @author Andrew Shvayka
 */
@Data
public class RuleEngineErrorMsg {

    private final MsgType inMsgType;
    private final RuleEngineError error;

    public boolean isSuccess() {
        return false;
    }

    public MsgType getMsgType() {
        return MsgType.RULE_ENGINE_ERROR;
    }

    public String getErrorMsg() {
        switch (error) {
            case NO_RULES:
                return "No rules configured!";
            case NO_ACTIVE_RULES:
                return "No active rules!";
            case NO_FILTERS_MATCHED:
                return "No rules that match current message!";
            case NO_REQUEST_FROM_ACTIONS:
                return "Rule filters match, but no plugin message produced by Rule actionsType!";
            case NO_TWO_WAY_ACTIONS:
                return "Rule filters match, but no Rule with two-way actionsType configured!";
            case NO_RESPONSE_FROM_ACTIONS:
                return "Rule filters match, message processed by plugin, but no response produced by Rule actionsType!";
            case PLUGIN_TIMEOUT:
                return "Timeout during processing of message by plugin!";
            default:
                throw new RuntimeException("Error " + error + " is not supported!");
        }
    }
}
