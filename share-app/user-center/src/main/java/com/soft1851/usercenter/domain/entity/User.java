package com.soft1851.usercenter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author Tao
 * @version 1.0
 * @ClassName User
 * @Description TODO
 * @date 2020-09-24 20:18
 **/
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "JDBC") // strategy 设置使用数据库主键自增策略；generator 设置插入完成后，查询最后生成的 ID 填充到该属性中。
    private Integer id;

    @Column(name = "wx_id")
    private String wxId;

    @Column(name = "wx_nickname")
    private String wxNickname;

    @Column(name = "roles")
    private String roles;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "bonus")
    private Integer bonus;

}
