package fudan.se.lab4.controller;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.MarketingStrategy;
import fudan.se.lab4.service.impl.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/orders")
@Api(value="order",description = "the service to handle orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @RequestMapping(value="/add",method= RequestMethod.POST,produces = "application/json")
    @ApiOperation(value = "add a order and return the payment information.")
    @ApiImplicitParam(name = "order",value = "the submitted order",dataType = "Order",required = true,paramType = "body")
    public ResponseEntity<PaymentInfo> order(@RequestBody @Valid Order order) {
        ArrayList<MarketingStrategy> strategies = new ArrayList<>();
        strategies.add(new DoubleElevenStrategy());
        strategies.add(new CombinationDiscountStrategy());
        strategies.add(new FullDiscountStrategy());
        strategies.add(new TeaAndCoffee15OffStrategy());
        orderServiceImpl.setStrategies(strategies);
        return ok().body(orderServiceImpl.pay(order));
    }
}
