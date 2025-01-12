package cc.altius.powerpack.service;

public interface ManageFlowService {
    public int addFlow(String code, String itemDesc, int level, int nextLevel, String uiLabel, boolean isLastLevel);
}
