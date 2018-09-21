package com.hui.day.learn.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * description ：用户生词本
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/21
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_user_word")
public class TbUserWord extends BaseDomain {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "word_id")
    private Long wordId;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "grasp_level")
    private Integer graspLevel;

    @Column(name = "add_time")
    private Date addTime;
}
