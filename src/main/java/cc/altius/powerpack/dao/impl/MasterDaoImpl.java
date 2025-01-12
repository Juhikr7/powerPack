package cc.altius.powerpack.dao.impl;

import cc.altius.powerpack.dao.MasterDao;
import cc.altius.powerpack.model.IdAndLabel;
import cc.altius.powerpack.model.Item;
import cc.altius.powerpack.model.rowMapper.IdAndLabelRowMapper;
import cc.altius.powerpack.model.rowMapper.ItemRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MasterDaoImpl implements MasterDao {

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
    public List<IdAndLabel> getRoleList() {
        return this.jdbcTemplate.query("SELECT r.ROLE_ID `ID`, r.ROLE_DESC `LABEL` FROM usr_role r", new IdAndLabelRowMapper());
    }

    @Override
    public List<Item> getAllItemList() {
//        String sqlString = "SELECT * FROM ms_items";
        String sqlString = "SELECT i.ITEM_ID,i.LEVEL,i.CODE,i.ITEM_DESCRIPTION,i.NEXT_LEVEL,l.UI_LABEL_DESCRIPTION FROM ms_items i  " +
                "LEFT JOIN ms_ui_labels l ON i.LEVEL = l.UI_LABEL_ID;";
        return jdbcTemplate.query(sqlString,new ItemRowMapper());
    }

}
