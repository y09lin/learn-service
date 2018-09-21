package com.hui.day.learn.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * description ：单词本
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/21
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_word_book")
public class TbWordBook extends BaseDomain{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "create_time")
    private Date createTime;
}
