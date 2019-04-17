package fudan.se.lab4.entity.ingredientEntity;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;

public class Cream extends Ingredient {
    public Cream(int number){
        super(number);
        price = 1.0;
        setName(InfoConstant.NAME_CREAM);
    }
}
