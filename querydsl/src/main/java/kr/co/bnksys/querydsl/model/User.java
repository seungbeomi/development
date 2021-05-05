package kr.co.bnksys.querydsl.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "TB_USERS")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Builder
    public User(Long id, String name,
            LocalDateTime fstRgDtti,
            LocalDateTime ltChDtti) {
        this.id = id;
        this.name = name;
        this.setFstRgDtti(fstRgDtti);
        this.setLtChDtti(ltChDtti);
    }

    public void setName(String name) {
        this.name = name;
    }

}
