package com.ranyk.mybatis.excel.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

/**
 * CLASS_NAME: DemoData<br/>
 * Description: 数据模型对象<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DemoData {

    /**
     * 行政区域(市)
     */
    @ExcelProperty({"行政区域"})
    private String administrativeRegions;
    /**
     * 当前地市的所有问题数量,忽略这个字段
     */
    @ExcelIgnore
    private Integer allCount;
    /**
     * 当前地市的已上报的数量
     */
    @ExcelProperty({"已上报"})
    private Integer reported;
    /**
     * 当前地市的已受理的数量
     */
    @ExcelProperty({"已受理","合计"})
    private Integer isRegCount;
    /**
     * 当前地市的已受理的一般问题数量
     */
    @ExcelProperty({"已受理","一般问题"})
    private Integer isRegGeneralCount;
    /**
     * 当前地市的已受理的严重问题数量
     */
    @ExcelProperty({"已受理","严重问题"})
    private Integer isRegSeriousCount;
    /**
     * 当前地市的已经办的数量
     */
    @ExcelProperty({"已办结","合计"})
    private Integer isHandledCount;
    /**
     * 当前地市的已经办的一般问题数量
     */
    @ExcelProperty({"已办结","一般问题"})
    private Integer isHandledGeneralCount;
    /**
     * 当前地市的已经办的严重问题数量
     */
    @ExcelProperty({"已办结","严重问题"})
    private Integer isHandledSeriousCount;
    /**
     * 当前地市的已确认的数量
     */
    @ExcelProperty({"已确认","合计"})
    private Integer isConfirmedCount;
    /**
     * 当前地市的已确认的一般问题数量
     */
    @ExcelProperty({"已确认","一般问题"})
    private Integer isConfirmedGeneralCount;
    /**
     * 当前地市的已确认的严重问题数量
     */
    @ExcelProperty({"已确认","严重问题"})
    private Integer isConfirmedSeriousCount;
    /**
     * 当前地市的已归档的数量
     */
    @ExcelProperty({"已归档","合计"})
    private Integer isArchivedCount;
    /**
     * 当前地市的已归档的一般问题数量
     */
    @ExcelProperty({"已归档","一般问题"})
    private Integer isArchivedGeneralCount;
    /**
     * 当前地市的已归档的严重问题数量
     */
    @ExcelProperty({"已归档","严重问题"})
    private Integer isArchivedSeriousCount;
}
