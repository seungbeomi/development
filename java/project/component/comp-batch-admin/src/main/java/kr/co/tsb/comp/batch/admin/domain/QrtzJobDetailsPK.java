package kr.co.tsb.comp.batch.admin.domain;

import static kr.co.tsb.comp.batch.admin.Constants.DEFAULT_GROUP;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.co.tsb.core.domain.BaseVO;
import lombok.Data;

@Data
@Embeddable
public class QrtzJobDetailsPK extends BaseVO {

	public QrtzJobDetailsPK() {}
	public QrtzJobDetailsPK(String jobName) {
		this.jobName = jobName;
		this.jobGroup = DEFAULT_GROUP;
	}
	public QrtzJobDetailsPK(String jobName, String jobGroup) {
		this.jobName = jobName;
		this.jobGroup = jobGroup;
	}
	
	@NotNull
	@Size(max=80)
	@Column(name="JOB_NAME", nullable=false, length=80)
	private String jobName;
	
	@NotNull
	@Size(max=80)
	@Column(name="JOB_GROUP", nullable=false, length=80)
	private String jobGroup;

}
