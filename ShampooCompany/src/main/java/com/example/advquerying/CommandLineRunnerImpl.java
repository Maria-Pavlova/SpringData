package com.example.advquerying;

import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

        private final ShampooService shampooService;
        private final IngredientService ingredientService;

    public CommandLineRunnerImpl(ShampooService shampooService, IngredientService ingredientService) {

        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }


    @Override
    public void run(String... args) throws Exception{

//       this.shampooService.findBySize(Size.MEDIUM)
//               .forEach(System.out::println);

//        this.shampooService.selectBySizeOrLabelId(Size.MEDIUM, 10)
//                .forEach(System.out::println);

//        this.shampooService.findByPriceGreaterThenOrderByPriceDesc(BigDecimal.valueOf(5))
//                .forEach(System.out::println);

//        this.ingredientService.findAllByNameStartingWithOrderByPrice("M")
//                .forEach(System.out::println);

//        this.ingredientService.findByNameInNames(List.of("Lavender","Herbs","Apple"))
//                .forEach(System.out::println);

//        int count = this.shampooService.countPriceLowerThan(BigDecimal.valueOf(8.5));
//        System.out.println(count);


//        this.shampooService.findByIngredientsIn(Set.of("Berry", "Mineral-Collagen"))
//                .forEach(System.out::println);

//        this.shampooService.findByIngredientsCountLessThan(2)
//                .forEach(System.out::println);

//
//        this.ingredientService.deleteIngredientByGivenName(".....");

//        this.ingredientService.increasePriceByPercent(1.1);

 //       this.ingredientService.increasePriceByNamesIn(BigDecimal.valueOf(1.1), Set.of("Herbs", "Apple"));
    }
}
