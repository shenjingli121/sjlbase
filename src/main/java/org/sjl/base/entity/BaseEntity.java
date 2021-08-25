package org.sjl.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class BaseEntity {


    @TableId(type = IdType.AUTO)
    protected Long id;
    @JsonFormat
    protected Date createTime;
    @JsonFormat
    protected Date updateTime;
    protected long createUserId;
    protected long updateUserId;
    @TableLogic
    protected boolean deleteStatus;



}
