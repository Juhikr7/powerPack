package cc.altius.powerpack.model.rowMapper;

import cc.altius.powerpack.model.IdAndLabel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IdAndLabelRowMapper implements RowMapper<IdAndLabel> {
    @Override
    public IdAndLabel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new IdAndLabel(rs.getString("ID"), rs.getString("LABEL"));
    }
}
