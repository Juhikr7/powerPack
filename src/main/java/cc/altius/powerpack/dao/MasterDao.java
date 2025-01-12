package cc.altius.powerpack.dao;

import cc.altius.powerpack.model.IdAndLabel;
import cc.altius.powerpack.model.Item;

import java.util.List;

public interface MasterDao {
    public List<IdAndLabel> getRoleList();
    public List<Item> getAllItemList();
}
