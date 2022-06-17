package org.jeecg.modules.flowable.listener.media;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

/**ProcessEndListener
 */
@Slf4j
public class StartListener implements ExecutionListener {



    @Override
    public void notify(DelegateExecution delegateExecution) {
        log.info("MediaStartListener notified ---->"+delegateExecution.getCurrentActivityId());
    }
}
