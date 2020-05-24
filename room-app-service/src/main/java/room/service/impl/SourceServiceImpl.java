package room.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import room.mapper.SourceMapper;
import room.pojo.Source;
import room.service.SourceService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceMapper sourceMapper;

    @Override
    public List<Source> queryAllSource() {
        return sourceMapper.selectAll();
    }

    @Override
    public Integer querySourceIdBySourceName(String sourceName) {
        Example example = new Example(Source.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("source_name", sourceName);
        return sourceMapper.selectOneByExample(example).getSourceId();
    }

    @Override
    public void createSource(Source source) {
        sourceMapper.insert(source);
    }

    @Override
    public void updateNameBySourceId(Source source) {
        sourceMapper.updateByPrimaryKeySelective(source);
    }

    @Override
    public boolean isSourceExist(String name) {
        Example example = new Example(Source.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("source_name", name);
        return sourceMapper.selectOneByExample(example) == null ? false : true;
    }

    @Override
    public void deleteSource(Integer sourceId) {
        sourceMapper.deleteByPrimaryKey(sourceId);
    }
}
