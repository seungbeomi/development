package kr.co.tsb.comp.batch.admin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.co.tsb.core.domain.BaseVO;
import lombok.Data;

import org.hibernate.annotations.ForeignKey;

@Data
@Entity
@Table(name = "QRTZ_SIMPLE_TRIGGERS")
public class QrtzSimpleTriggersVO extends BaseVO {

	@Id
	private QrtzTriggersPK qrtzTriggersPK;
	
	@NotNull
	@Size(max=7)
	@Column(name="REPEAT_COUNT", nullable=false, length=7)
	private long repeatCount;
	
	@NotNull
	@Size(max=12)
	@Column(name="REPEAT_INTERVAL", nullable=false, length=12)
	private long repeatInterval;
	
	@NotNull
	@Size(max=7)
	@Column(name="TIMES_TRIGGERED", nullable=false, length=7)
	private long timesTriggered;
	
	@ManyToOne
	@ForeignKey(name="FK_QRTZ_SIMPLE_TRIGGERS")
	@JoinColumns({
		@JoinColumn(name="TRIGGER_NAME", referencedColumnName="TRIGGER_NAME", insertable=false, updatable=false),
		@JoinColumn(name="TRIGGER_GROUP", referencedColumnName="TRIGGER_GROUP", insertable=false, updatable=false)
	})
	private QrtzTriggersVO qrtzTriggersVO;

}
