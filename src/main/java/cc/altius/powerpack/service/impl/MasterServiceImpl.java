package cc.altius.powerpack.service.impl;

import cc.altius.powerpack.dao.MasterDao;
import cc.altius.powerpack.model.IdAndLabel;
import cc.altius.powerpack.model.Item;
import cc.altius.powerpack.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MasterServiceImpl implements MasterService {
    private final List<IdAndLabel> genderList;
    private final List<IdAndLabel> statusList;

    @Autowired
    private MasterDao masterDao;

    public MasterServiceImpl() {
        genderList = new LinkedList<>();
        genderList.add(new IdAndLabel("M", "Male"));
        genderList.add(new IdAndLabel("F", "Female"));
        statusList = new LinkedList<>();
        statusList.add(new IdAndLabel("1", "Active"));
        statusList.add(new IdAndLabel("0", "Deactivate"));
    }

    @Override
    public List<IdAndLabel> getRoleList() {
        return this.masterDao.getRoleList();
    }

    @Override
    public List<IdAndLabel> getGenderList() {
        return this.genderList;
    }


    @Override
    public Map<Integer, List<Item>> getLevelItemListMap() {
        List<Item> itemList = masterDao.getAllItemList();
        Map<Integer,List<Item>> levelAndItemMap = itemList.stream().collect(Collectors.groupingBy(Item::getLevel));
//        System.out.println(levelAndItemMap);
        return levelAndItemMap;
    }

    @Override
    public Map<Integer, Integer> getIdNextLevelMap() {
        List<Item> itemList = masterDao.getAllItemList();
        Map <Integer,Integer> idNextLevelMap = itemList.stream().collect(Collectors.toMap(Item::getItemId,Item::getNextLevel));
        return idNextLevelMap;
    }

    @Override
    public List<IdAndLabel> getStatusList() {
        return List.of();
    }
}
