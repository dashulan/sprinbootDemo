package com.dashulan.demo.web;

import javax.validation.Valid;
import com.dashulan.demo.tacos.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import com.dashulan.demo.tacos.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO","FLOur Tortilla", Type.WRAP),
                new Ingredient("FLTO","FLOur Tortilla", Type.WRAP),
                new Ingredient("FLTO","FLOur Tortilla", Type.WRAP),
                new Ingredient("FLTO","FLOur Tortilla", Type.WRAP),
                new Ingredient("FLTO","FLOur Tortilla", Type.WRAP),
                new Ingredient("FLTO","FLOur Tortilla", Type.WRAP),
                new Ingredient("FLTO","FLOur Tortilla", Type.WRAP),
                new Ingredient("FLTO","FLOur Tortilla", Type.WRAP),
                new Ingredient("FLTO","FLOur Tortilla", Type.WRAP)
        ) ;

        Type[] types =Type.values();

        return "design";
    }
}
