
package cn.neucloud.server.core.rule;


public interface RuleLifecycleComponent {

    void resume();

    void suspend();

    void stop();

}
