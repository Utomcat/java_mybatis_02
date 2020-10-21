package com.ranyk.mybatis.task;

import com.ranyk.mybatis.entity.Tb2;
import com.ranyk.mybatis.service.Tb2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName:SchedulTaskTestOne
 * Description:定时任务一
 *
 * @author ranyi
 * @date 2020-09-04 14:08
 * Version: V1.0
 */
@Slf4j
@Component
public class SchedulingTaskTestOne {


    @Autowired
    private Tb2Service tbService;

    private static Integer stInt = 1;


    /**
     * 定时任务一: 每20秒执行一次想 tb2 表中新增数据, 新增的次数为 stInt 次
     */
    //@Scheduled(cron = "*/20 * * * * ?")
    public void insertIntoTb2Task(){

        Tb2 tb2 = new Tb2();
        tb2.setScore("50");
        StringBuilder sbName = new StringBuilder("姓名");
        StringBuilder sbCourse = new StringBuilder("课程");

        for (int i = 0; i < stInt; i++) {
            try {
                tb2.setName(sbName.append(i).toString());
                tb2.setCourse(sbCourse.append(i).toString());
                if (i%3 == 0){
                    tb2.setTestFiled("测试字段");
                }else {
                    tb2.setTestFiled(String.valueOf(i));
                }

                Tb2 insert = tbService.insert(tb2);

                if (null != insert){
                    log.info("增加成功!");
                }else {
                    log.info("增加失败");
                }
            }catch (Exception e){
                log.info("错误信息为: " + e.getMessage() + "\r\n 错误行为: " + e.getStackTrace()[0].getLineNumber());
            }
        }

        ++stInt;

    }


}
