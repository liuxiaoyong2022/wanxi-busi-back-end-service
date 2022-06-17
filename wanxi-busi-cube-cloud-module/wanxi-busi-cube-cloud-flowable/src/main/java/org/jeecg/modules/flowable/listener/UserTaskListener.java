package org.jeecg.modules.flowable.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**TaskCreatedAutoSubmitListener
 */
@Slf4j
@Service
public class UserTaskListener implements TaskListener{

    @Override
    public void notify(DelegateTask delegateTask) {
     log.info("hello flow ---->"+delegateTask.getName());
    }

}
