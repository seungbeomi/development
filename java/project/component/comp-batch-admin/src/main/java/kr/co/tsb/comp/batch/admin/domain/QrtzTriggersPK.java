package kr.co.tsb.comp.batch.admin.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.co.tsb.core.domain.BaseVO;
import lombok.Data;

@Data
@Embeddable
public class QrtzTriggersPK extends BaseVO {

	public QrtzTriggersPK() {}
	public QrtzTriggersPK(String triggerName, String triggerGroup) {
		this.triggerName = triggerName;
		this.triggerGroup = triggerGroup;
	}
	
	@NotNull
	@Size(max=80)
	@Column(name="TRIGGER_NAME", nullable=false, length=80)
	private String triggerName;
	
	@NotNull
	@Size(max=80)
	@Column(name="TRIGGER_GROUP", nullable=false, length=80)
	private String triggerGroup;
	
}
