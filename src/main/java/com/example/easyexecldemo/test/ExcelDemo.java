package com.example.easyexecldemo.test;

import com.example.easyexecldemo.po.ScoreData;
import com.example.easyexecldemo.utils.DefaultExcelListener;
import com.example.easyexecldemo.utils.EasyExcelUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ExcelDemo {

    private String filePath = "C:\\Users\\16138\\Desktop\\1.xlsx";

    @Test
    public void test1(){
        //同步读取excel数据（无实体类模型）
        //参数1：文件绝对路径      参数2：Excel的第几个sheet 0：第一个     参数3：从第几行开始读取 0：第一行         
        List<Map<Integer, String>> data = EasyExcelUtil.syncRead(filePath,0,0);
        for(Iterator it = data.iterator(); it.hasNext();){
            Map<Integer,String> obj = (Map<Integer, String>) it.next();
            System.out.println(obj);
        }
    }

    @Test
    public void test2(){
        //同步读取excel数据（无实体类模型）----读取excel表的高二成绩表数据，从第一行开始读取
        //参数1：文件绝对路径      参数2：Excel的第几个sheet 0：第一个     参数3：从第几行开始读取 0：第一行
        List<Map<Integer, String>> data = EasyExcelUtil.syncRead(filePath,0,0);
        for(Iterator it = data.iterator(); it.hasNext();){
            Map<Integer,String> obj = (Map<Integer, String>) it.next();
            System.out.println(obj);
        }
    }

    @Test
    public void test4(){
        //异步读取excel数据（无实体类模型）----读取excel表的第一张sheet高一成绩表数据，从第一行开始读取
        EasyExcelUtil.asyncRead(filePath,new DefaultExcelListener<>(),0,1);
        //异步读取的数据会在监听器类里面处理数据
    }

    @Test
    public void test5(){
        //异步读取excel数据（有实体类模型）----读取excel表的第一张sheet高一成绩表数据，从第一行开始读取
        EasyExcelUtil.asyncReadModel(filePath,new DefaultExcelListener<>(), ScoreData.class,0,1);
        //异步读取的数据会在监听器类里面处理数据
    }

    @Test
    public void test6(){
        //异步读取excel数据（有实体类模型）----读取excel表的第一张sheet高一成绩表数据，从第一行开始读取
        ArrayList<ScoreData> scoreData = new ArrayList<ScoreData>();

        scoreData.add(new ScoreData(1,"4","3",4,5,6,7));
        scoreData.add(new ScoreData(1,"4","3",4,5,6,7));
        scoreData.add(new ScoreData(1,"4","3",4,5,6,7));
        scoreData.add(new ScoreData(1,"4","3",4,5,6,7));
        scoreData.add(new ScoreData(1,"4","3",4,5,6,7));
        scoreData.add(new ScoreData(1,"4","3",4,5,6,7));
        scoreData.add(new ScoreData(1,"4","3",4,5,6,7));
        EasyExcelUtil.write(filePath,ScoreData.class,scoreData);
        //异步读取的数据会在监听器类里面处理数据
    }

}
