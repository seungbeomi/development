package kr.co.tsb.comp.batch.admin.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.co.tsb.core.domain.BaseVO;

@Embeddable
public class QrtzJobDetailsPK extends BaseVO {

	public QrtzJobDetailsPK() {}
	public QrtzJobDetailsPK(String jobName, String jobGroup) {
		this.jobName = jobName;
		this.jobGroup = jobGroup;
	}
	
	@NotNull
	@Size(max=80)
	@Column(name="JOB_NAME", nullable=false, length=80, insertable=false, updatable=false)
	private String jobName;
	
	@NotNull
	@Size(max=80)
	@Column(name="JOB_GROUP", nullable=false, length=80, insertable=false, updatable=false)
	private String jobGroup;
	
}
