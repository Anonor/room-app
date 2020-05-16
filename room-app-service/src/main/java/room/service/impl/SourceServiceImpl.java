package room.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import room.mapper.SourceMapper;
import room.pojo.Source;
import room.service.SourceService;

import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceMapper sourceMapper;

    @Override
    public List<Source> queryAllSource() {
        return sourceMapper.selectAll();
    }
}
