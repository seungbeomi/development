package kr.co.tsb.comp.batch.admin.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.co.tsb.core.domain.BaseVO;
import lombok.Data;

import org.hibernate.annotations.ForeignKey;

@Data 
@Entity
@Table(name = "QRTZ_TRIGGERS")
public class QrtzTriggersVO extends BaseVO {

	@Id
	private QrtzTriggersPK qrtzTriggersPK;
	
	@Embedded
	private QrtzJobDetailsPK qrtzJobDetailsPK;

	@NotNull
	@Size(max=5)
	@Column(name="IS_VOLATILE", nullable=false, length=5)
	private String isVolatile;
	
	@Size(max=120)
	@Column(name="DESCRIPTION", length=120)
	private String description;
	
	@Size(max=13)
	@Column(name="NEXT_FIRE_TIME", length=13)
	private long nextFireTime;
	
	@Size(max=13)
	@Column(name="PREV_FIRE_TIME", length=13)
	private long prevFireTime;
	
	@Size(max=13)
	@Column(name="PRIORITY", length=13)
	private long priority;
	
	@NotNull
	@Size(max=16)
	@Column(name="TRIGGER_STATE", nullable=false, length=16)
	private String triggerState;
	
	@NotNull
	@Size(max=8)
	@Column(name="TRIGGER_TYPE", nullable=false, length=8)
	private String triggerType;
	
	@NotNull
	@Size(max=13)
	@Column(name="START_TIME", nullable=false, length=13)
	private long startTime;
	
	@Size(max=13)
	@Column(name="END_TIME", length=13)
	private long endTime;
	
	@Size(max=80)
	@Column(name="CALENDAR_NAME", length=80)
	private String calendarName;
	
	@Size(max=2)
	@Column(name="MISFIRE_INSTR", length=2)
	private int misfireInstr;
	
	@OneToOne(cascade=CascadeType.ALL)
	@ForeignKey(name="FK_QRTZ_TRIGGERS")
	@JoinColumns({
		@JoinColumn(name="JOB_NAME", referencedColumnName="JOB_NAME"),
		@JoinColumn(name="JOB_GROUP", referencedColumnName="JOB_GROUP")
	})
	private QrtzJobDetailsVO qutzDetailsVO;
	
	@OneToOne(mappedBy="qrtzTriggersVO", cascade=CascadeType.ALL)
	private QrtzSimpleTriggersVO qrtzSimpleTriggersVO;
	
}
