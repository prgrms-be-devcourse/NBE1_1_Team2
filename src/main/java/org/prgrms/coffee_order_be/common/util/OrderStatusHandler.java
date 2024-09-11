package org.prgrms.coffee_order_be.common.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.prgrms.coffee_order_be.order.entity.OrderStatus;

@MappedTypes(OrderStatus.class)
public class OrderStatusHandler implements TypeHandler<OrderStatus> {

  @Override
  public void setParameter(PreparedStatement ps, int i, OrderStatus parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, parameter.toString());
  }

  @Override
  public OrderStatus getResult(ResultSet rs, String columnName) throws SQLException {
    String orderStatus = rs.getString(columnName);
    return orderStatus != null ? OrderStatus.valueOf(orderStatus) : null;
  }

  @Override
  public OrderStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
    String orderStatus = rs.getString(columnIndex);
    return orderStatus != null ? OrderStatus.valueOf(orderStatus) : null;
  }

  @Override
  public OrderStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
    String orderStatus = cs.getString(columnIndex);
    return orderStatus != null ? OrderStatus.valueOf(orderStatus) : null;
  }
}
