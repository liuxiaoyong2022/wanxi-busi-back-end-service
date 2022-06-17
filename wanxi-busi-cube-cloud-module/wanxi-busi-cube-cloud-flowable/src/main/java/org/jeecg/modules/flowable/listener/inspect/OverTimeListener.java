package org.jeecg.modules.flowable.listener.inspect;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;

/**TaskCreatedAutoSubmitListener
 */
@Slf4j
@Service
public class OverTimeListener implements ExecutionListener {



    @Override
    public void notify(DelegateExecution delegateExecution) {
        delegateExecution.setVariable("taskTimeout",1);
        log.info(" 这是任务超时 进入持续提醒 inspect CompleteUserListener be nofified ---->"+delegateExecution.getCurrentActivityId()+"  task:"+delegateExecution.getEventName());
    }
}
