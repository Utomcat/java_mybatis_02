package com.ranyk.mybatis.excel;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Boolean;
import jxl.write.Number;
import jxl.write.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

/**
 * CLASS_NAME: CreateExcel<br/>
 * Description: 使用 JXL 方式,操作Excel表格<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Slf4j
public class CreateExcelUseJxl {

    @SuppressWarnings("all")
    public static void createExcel(OutputStream os) throws WriteException, IOException {
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        //构造表头
        //添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
        sheet.mergeCells(0, 0, 4, 0);
        //设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
        WritableFont bold = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
        //生成一个单元格样式控制对象
        WritableCellFormat titleFormat = new WritableCellFormat(bold);
        //单元格中的内容水平方向居中
        titleFormat.setAlignment(jxl.format.Alignment.CENTRE);
        //单元格的内容垂直方向居中
        titleFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        Label title = new Label(0,0,"JExcelApi支持数据类型详细说明",titleFormat);
        //设置第一行的高度
        sheet.setRowView(0, 600, false);
        sheet.addCell(title);

        //创建要显示的具体内容
        //选择字体
        WritableFont color = new WritableFont(WritableFont.ARIAL);
        //设置字体颜色为金黄色
        color.setColour(Colour.GOLD);
        WritableCellFormat colorFormat = new WritableCellFormat(color);
        Label format = new Label(0,1,"数据格式",colorFormat);
        sheet.addCell(format);
        Label floats = new Label(1,1,"浮点型");
        sheet.addCell(floats);
        Label integers = new Label(2,1,"整型");
        sheet.addCell(integers);
        Label booleans = new Label(3,1,"布尔型");
        sheet.addCell(booleans);
        Label dates = new Label(4,1,"日期格式");
        sheet.addCell(dates);

        Label example = new Label(0,2,"数据示例",colorFormat);
        sheet.addCell(example);
        //浮点数据
        //设置下划线
        WritableFont underline= new WritableFont(WritableFont.ARIAL,WritableFont.DEFAULT_POINT_SIZE,WritableFont.NO_BOLD,false, UnderlineStyle.SINGLE);
        WritableCellFormat greyBackground = new WritableCellFormat(underline);
        //设置背景颜色为灰色
        greyBackground.setBackground(Colour.GRAY_25);
        Number number = new Number(1,2,3.1415926535,greyBackground);
        sheet.addCell(number);
        //整形数据
        //黑体
        WritableFont boldNumber = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
        WritableCellFormat boldNumberFormat = new WritableCellFormat(boldNumber);
        Number intS = new Number(2,2,15042699,boldNumberFormat);
        sheet.addCell(intS);
        //布尔型数据
        Boolean bool = new Boolean(3,2,true);
        sheet.addCell(bool);
        //日期型数据
        //设置黑体和下划线
        WritableFont boldDate = new WritableFont(WritableFont.ARIAL,WritableFont.DEFAULT_POINT_SIZE,WritableFont.BOLD,false,UnderlineStyle.SINGLE);
        WritableCellFormat boldDateFormat = new WritableCellFormat(boldDate,DateFormats.FORMAT1);
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        DateTime dt = new DateTime(4,2,date,boldDateFormat);
        sheet.addCell(dt);
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }
}
