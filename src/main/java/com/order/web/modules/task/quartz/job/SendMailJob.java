package com.order.web.modules.task.quartz.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * 作业任务定义
 */
public class SendMailJob extends QuartzJobBean {
    protected Log logger = LogFactory.getLog(this.getClass());
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.debug("Quartz jOB start");
    }
}
