package fudan.se.lab4.dto;

import fudan.se.lab4.constant.InfoConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class Order implements Serializable {
    private static final long serialVersionUID = 6442456165785725948L;

    @ApiModelProperty(notes = "order ID", required = true, dataType = "String")
    private String id;

    @ApiModelProperty(notes = "order items", required = true, dataType = "OrderItem")
    private List<OrderItem> orderItems;

    @ApiModelProperty(notes = "the currency", required = true, dataType = "String")
    private String currency;

    public Order(String id, String currency, List<OrderItem> orderItems) {
        this.currency = currency;
        this.id = id;
        this.orderItems = orderItems;
    }

    public Order() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double totalPrice() {

        assert orderItems != null : InfoConstant.ORDER_ITEMS_NULL;
        return orderItems.stream().map(orderItem -> {
            assert orderItem != null : InfoConstant.ORDER_ITEM_NULL;
            return orderItem.cost(currency);
        })
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
