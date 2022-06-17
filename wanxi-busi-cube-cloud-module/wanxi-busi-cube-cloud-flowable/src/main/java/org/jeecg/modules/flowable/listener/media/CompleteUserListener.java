package org.jeecg.modules.flowable.listener.media;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Service;

/**TaskCreatedAutoSubmitListener
 */
@Slf4j
@Service
public class CompleteUserListener implements ExecutionListener {



    @Override
    public void notify(DelegateExecution delegateExecution) {
        log.info(" 到点自动完成? CompleteUserListener be nofified ---->"+delegateExecution.getCurrentActivityId()+  "task:"+delegateExecution.getEventName());
    }
}
