package com.mrcoder.framecommon.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelModelListener<T> extends AnalysisEventListener<T> {

    //自定义用于暂时存储data
    private List<T> dataList = new ArrayList<T>();

    @Override
    public void invoke(T object, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(object));
        //根据自己业务做处理
        doSomething(object);
        dataList.add(object);
    }

    private void doSomething(T object) {
        log.info("处理数据！");
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！");
        //datas.clear();
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}