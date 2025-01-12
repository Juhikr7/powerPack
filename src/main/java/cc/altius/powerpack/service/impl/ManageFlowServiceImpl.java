package cc.altius.powerpack.service.impl;

import cc.altius.powerpack.dao.ManageFlowDao;
import cc.altius.powerpack.service.ManageFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageFlowServiceImpl implements ManageFlowService {
    @Autowired
    private ManageFlowDao manageFlowDao;
    @Override
    public int addFlow(String code, String itemDesc, int level, int nextLevel, String uiLabel, boolean isLastLevel) {
        return manageFlowDao.addFlow(code, itemDesc, level, nextLevel, uiLabel, isLastLevel);
    }
}
