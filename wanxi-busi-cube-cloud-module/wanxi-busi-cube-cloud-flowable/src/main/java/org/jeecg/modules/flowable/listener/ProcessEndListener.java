package org.jeecg.modules.flowable.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

/**ProcessEndListener
 */
@Slf4j
public class ProcessEndListener implements ExecutionListener {



    @Override
    public void notify(DelegateExecution delegateExecution) {
        log.info("ProcessEndListener be notified ---->"+delegateExecution.getEventName());

    }
}
