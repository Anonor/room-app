package room.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import room.mapper.HousekeeperMapper;
import room.service.HouseKeeperService;

@Service
public class HouseKeeperServiceImpl implements HouseKeeperService {

    @Autowired
    private HousekeeperMapper housekeeperMapper;



}
