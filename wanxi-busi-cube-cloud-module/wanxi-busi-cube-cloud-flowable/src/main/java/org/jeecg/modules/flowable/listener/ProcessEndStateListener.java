package org.jeecg.modules.flowable.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Service;

/**ProcessEndListener
 */
@Slf4j
public class ProcessEndStateListener implements ExecutionListener {



    @Override
    public void notify(DelegateExecution delegateExecution) {
        log.info("fuck flow ---->"+delegateExecution.getEventName());
    }
}
