package cc.altius.powerpack.dao;

public interface ManageFlowDao {
    public int addFlow(String code, String itemDesc, int level, int nextLevel, String uiLabel, boolean isLastLevel);
}
