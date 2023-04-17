package com.sunyi.gobang.common.database.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 阿里java开发手册： 【强制】表必备三字段：id, create_time, update_time。 说明：其中 id 必为主键，类型为 bigint
 * unsigned、单表时自增、步长为 1。create_time, update_time 的类型均为 datetime
 * 类型，前者现在时表示主动式创建，后者过去分词表示被动式更新。
 *
 * @author Sunyi
 * @date 2023/4/8
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel implements Serializable {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    protected Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;


    @Override
    public String toString() {
        return "BaseModel{" + "createTime=" + createTime + ", updateTime=" + updateTime + '}';
    }

}
