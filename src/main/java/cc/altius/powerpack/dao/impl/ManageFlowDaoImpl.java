package cc.altius.powerpack.dao.impl;

import cc.altius.powerpack.dao.ManageFlowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ManageFlowDaoImpl implements ManageFlowDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    @Transactional
    public int addFlow(String code, String itemDesc, int level, int nextLevel, String uiLabel, boolean isLastLevel) {
        int uiLabelId = 0;
        if (!isLastLevel){
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("ms_ui_labels").usingGeneratedKeyColumns("UI_LABEL_ID");
            Map<String,Object> params = new HashMap<>();
            params.put("UI_LABEL_DESCRIPTION",uiLabel);
            uiLabelId = simpleJdbcInsert.executeAndReturnKey(params).intValue();
        }
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("ms_items").usingGeneratedKeyColumns("ITEM_ID");
        Map<String,Object> params = new HashMap<>();
        params.put("LEVEL",level);
        params.put("CODE",code);
        params.put("ITEM_DESCRIPTION",itemDesc);
        if(!isLastLevel){
            params.put("NEXT_LEVEL",uiLabelId != 0 ? uiLabelId : nextLevel != 0 ? nextLevel : null );
        }
        return simpleJdbcInsert.executeAndReturnKey(params).intValue();
    }
}
