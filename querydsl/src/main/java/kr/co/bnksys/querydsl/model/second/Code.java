package kr.co.bnksys.querydsl.model.second;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_EBCC_CODE")
@Entity
public class Code {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "CODE_TEXT")
    private String codeText;

    @Column(name = "CODE_VALUE")
    private String codeValue;

}
