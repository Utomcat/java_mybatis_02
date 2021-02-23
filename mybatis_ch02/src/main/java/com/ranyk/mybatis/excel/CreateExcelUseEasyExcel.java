package com.ranyk.mybatis.excel;

import com.alibaba.excel.EasyExcel;
import com.ranyk.mybatis.excel.vo.ComplexHeadData;
import com.ranyk.mybatis.excel.vo.DemoData;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASS_NAME: CreateExcelUseEasyExcel<br/>
 * Description: 使用阿里的easy Excel 进行Excel操作<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
public class CreateExcelUseEasyExcel {

    public void complexHeadWrite() {
        String fileName = "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ComplexHeadData.class).sheet("模板").doWrite(data());
    }

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData("四川地市" + i,
                    i,
                    i,
                    i,
                    i,
                    i,
                    i,
                    i,
                    i,
                    i,
                    i,
                    i,
                    i,
                    i,
                    i);
            list.add(data);
        }
        DemoData hjData = new DemoData("合计",
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0);
        for (DemoData demoData : list) {
            hjData.setAllCount(hjData.getAllCount() + demoData.getAllCount());
            hjData.setReported(hjData.getReported() + demoData.getReported());
            hjData.setIsRegCount(hjData.getIsRegCount() + demoData.getIsRegCount());
            hjData.setIsRegGeneralCount(hjData.getIsRegGeneralCount() + demoData.getIsRegGeneralCount());
            hjData.setIsRegSeriousCount(hjData.getIsRegSeriousCount() + demoData.getIsRegSeriousCount());
            hjData.setIsHandledCount(hjData.getIsHandledCount() + demoData.getIsHandledCount());
            hjData.setIsHandledGeneralCount(hjData.getIsHandledGeneralCount() + demoData.getIsHandledGeneralCount());
            hjData.setIsHandledSeriousCount(hjData.getIsHandledSeriousCount() + demoData.getIsHandledSeriousCount());
            hjData.setIsConfirmedCount(hjData.getIsConfirmedCount() + demoData.getIsConfirmedCount());
            hjData.setIsConfirmedGeneralCount(hjData.getIsConfirmedGeneralCount() + demoData.getIsConfirmedGeneralCount());
            hjData.setIsConfirmedSeriousCount(hjData.getIsConfirmedSeriousCount() + demoData.getIsConfirmedSeriousCount());
            hjData.setIsArchivedCount(hjData.getIsArchivedCount() + demoData.getIsArchivedCount());
            hjData.setIsArchivedGeneralCount(hjData.getIsArchivedGeneralCount() + demoData.getIsArchivedGeneralCount());
            hjData.setIsArchivedSeriousCount(hjData.getIsArchivedSeriousCount() + demoData.getIsArchivedSeriousCount());
        }
        list.add(hjData);
        return list;
    }
}