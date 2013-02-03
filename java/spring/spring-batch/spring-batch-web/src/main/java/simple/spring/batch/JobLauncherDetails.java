/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package simple.spring.batch;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.StatefulJob;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author Dave Syer
 * 
 */
@Component
public class JobLauncherDetails extends QuartzJobBean implements StatefulJob {

	/**
	 * Special key in job data map for the name of a job to run.
	 */
	static final String JOB_NAME = "jobName";

	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	@Autowired
	private JobLocator jobLocator;
	@Autowired
	private JobLauncher jobLauncher;

	public void setJobLocator(JobLocator jobLocator) {
    this.jobLocator = jobLocator;
  }
  public void setJobLauncher(JobLauncher jobLauncher) {
    this.jobLauncher = jobLauncher;
  }

	@SuppressWarnings("unchecked")
	protected void executeInternal(JobExecutionContext context) {
		Map<String, Object> jobDataMap = context.getMergedJobDataMap();
		String jobName = (String) jobDataMap.get(JOB_NAME);
		
		log.info("Quartz trigger firing with Spring Batch jobName="+jobName);
		
		// JobParameters을 변경시키기 위한 설정
		// 파라미터가 같으면 동일한 잡으로 취급
		//jobDataMap.put("date", new Date());
		
		JobParameters jobParameters = getJobParametersFromJobMap(jobDataMap);
		try {
		  Job job = jobLocator.getJob(jobName);
		  
			JobExecution jobExecution = jobLauncher.run(jobLocator.getJob(jobName), jobParameters);
			System.out.println("<<< " + jobExecution.getExitStatus().getExitCode());
			
			if ("COMPLETED".equals(jobExecution.getStatus())) {
			  System.out.println("COMPLETED!!");
			}
			
			Collection<StepExecution> collection = jobExecution.getStepExecutions();
			
			for (StepExecution s : collection) {
			  System.out.println(s);
			  ExecutionContext excutionContext = s.getExecutionContext();
			  
			}
			
		}
		catch (JobExecutionException e) {
			// log.error("Could not execute job.", e);
		}
	}

	/*
	 * Copy parameters that are of the correct type over to
	 * {@link JobParameters}, ignoring jobName.
	 * 
	 * @return a {@link JobParameters} instance
	 */
	private JobParameters getJobParametersFromJobMap(Map<String, Object> jobDataMap) {

		JobParametersBuilder builder = new JobParametersBuilder();

		for (Entry<String, Object> entry : jobDataMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if (value instanceof String && !key.equals(JOB_NAME)) {
				builder.addString(key, (String) value);
			}
			else if (value instanceof Float || value instanceof Double) {
				builder.addDouble(key, ((Number) value).doubleValue());
			}
			else if (value instanceof Integer || value instanceof Long) {
				builder.addLong(key, ((Number)value).longValue());
			}
			else if (value instanceof Date) {
				builder.addDate(key, (Date) value);
			}
			else {
			  //System.out.println(">>> key : " + key + ", value : " + value);
				//log.debug("JobDataMap contains values which are not job parameters (ignoring).");
			}
		}

		return builder.toJobParameters();

	}

}
