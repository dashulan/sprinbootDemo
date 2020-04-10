package com.dashulan.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMethods {


    @Test
    public void test(){
        List<Integer> list1 = new ArrayList<>();
        list1.addAll(Arrays.asList(1, 3, 5, 6));
        List<Integer> list2 = new ArrayList<>();
        list2.addAll(Arrays.asList(2, 3, 6, 7));
        List<Integer> list3 = new ArrayList<>();
        list3.addAll(Arrays.asList(3, 5, 7, 9));
        List<Integer> list = new ArrayList<>();
        list.addAll(list1);

        list.retainAll(list1);
        list.retainAll(list2);
        list.retainAll(list3);
        assertThat(list).size().isEqualTo(1);
    }
}
