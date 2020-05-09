package room.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import room.mapper.PensionMapper;
import room.service.PensionService;

@Service
public class PensionServiceImpl implements PensionService {

    @Autowired
    private PensionMapper pensionMapper;



}
