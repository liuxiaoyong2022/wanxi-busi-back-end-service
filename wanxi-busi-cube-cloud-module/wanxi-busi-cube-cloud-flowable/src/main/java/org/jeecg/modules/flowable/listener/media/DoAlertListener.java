package org.jeecg.modules.flowable.listener.media;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

/**ProcessEndListener
 */
@Slf4j
public class DoAlertListener implements ExecutionListener {



    @Override
    public void notify(DelegateExecution delegateExecution) {
        log.info("未处理提醒 可能发短信通信 DoAlertListener notified ---->"+delegateExecution.getCurrentActivityId());
    }
}
