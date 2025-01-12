package cc.altius.powerpack.model.rowMapper;

import cc.altius.powerpack.model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Item(
                rs.getInt("ITEM_ID"),
                rs.getInt("LEVEL"),
                rs.getString("CODE"),
                rs.getString("ITEM_DESCRIPTION"),
                rs.getInt("NEXT_LEVEL"),
                rs.getString("UI_LABEL_DESCRIPTION")
        );
    }
}
