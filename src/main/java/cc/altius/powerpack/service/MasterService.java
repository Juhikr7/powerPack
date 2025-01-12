package cc.altius.powerpack.service;

import cc.altius.powerpack.model.IdAndLabel;
import cc.altius.powerpack.model.Item;

import java.util.List;
import java.util.Map;

public interface MasterService {

    public List<IdAndLabel> getRoleList();

    public List<IdAndLabel> getGenderList();

    public Map<Integer,List<Item>> getLevelItemListMap();

    public Map<Integer,Integer> getIdNextLevelMap();

    public List<IdAndLabel> getStatusList();

}
