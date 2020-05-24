package room.service;

import room.pojo.Source;

import java.util.List;

public interface SourceService {

    //获取所有房客来源
    public List<Source> queryAllSource();

    //新建客源
    public void createSource(Source source);

    //根据客源id修改客源名字
    public void updateNameBySourceId(Source source);

    //根据客源name查询客源是否存在（true：存在   false：不存在）
    public boolean isSourceExist(String name);

    //根据客源id删除客源
    public void deleteSource(Integer sourceId);

}
