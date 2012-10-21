package kr.co.tsb.comp.batch.admin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.co.tsb.core.domain.BaseVO;
import lombok.Data;

@Data
@Entity
@Table(name = "QRTZ_JOB_DETAILS")
public class QrtzJobDetailsVO extends BaseVO {

	@Id
	private QrtzJobDetailsPK qrtzJobDetailsPK;

	@Size(max=120)
	@Column(name="DESCRIPTION", length=120)
	private String description;
	
	@NotNull
	@Size(max=128)
	@Column(name="JOB_CLASS_NAME", nullable=false, length=128)
	private String jobClassName;
	
	@NotNull
	@Size(max=5)
	@Column(name="IS_DURABLE", nullable=false, length=5)
	private String isDurable;
	
	@NotNull
	@Size(max=5)
	@Column(name="IS_VOLATILE", nullable=false, length=5)
	private String isVolatile;
	
	@NotNull
	@Size(max=5)
	@Column(name="IS_STATEFUL", nullable=false, length=5)
	private String isStateful;
	
	@NotNull
	@Size(max=5)
	@Column(name="REQUESTS_RECOVERY", nullable=false, length=5)
	private String requestsRecovery;

}
