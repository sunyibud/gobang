package com.sunyi.gobang.common.vo;
import cn.hutool.core.date.DateTime;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 阿里java开发手册： 【强制】表必备三字段：id, create_time, update_time。 说明：其中 id 必为主键，类型为 bigint
 * unsigned、单表时自增、步长为 1。create_time, update_time 的类型均为 datetime
 * 类型，前者现在时表示主动式创建，后者过去分词表示被动式更新。
 * @author Sunyi
 * @date 2023/4/7
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    protected Date createTime;

    /**
     * 更新时间
     */
    protected Date updateTime;
}