package kr.co.bnksys.querydsl.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    @Column(name = "FST_RG_DTTI")
    private LocalDateTime fstRgDtti;

    @LastModifiedDate
    @Column(name = "LT_CH_DTTI")
    private LocalDateTime ltChDtti;

    public void setCurrentTime(LocalDateTime currentTime) {
        this.fstRgDtti = currentTime;
        this.ltChDtti = currentTime;
    }

}
