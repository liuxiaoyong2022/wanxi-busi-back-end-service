package org.jeecg.modules.flowable.listener.media;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

/**ProcessEndListener
 */
@Slf4j
public class SuperAlertListener implements ExecutionListener {



    @Override
    public void notify(DelegateExecution delegateExecution) {
        log.info(" 持续提醒   SuperAlertListener notified ---->"+delegateExecution.getCurrentActivityId()+"   processsInstanceId:"+delegateExecution.getProcessInstanceId());
    }
}
