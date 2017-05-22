package mba;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by winn on 17/5/22.
 */
public class Combination {
    public static <T extends Comparable<? super T>> List<List<T>> findSortedCombinations(Collection<T> elements, int n){
        List<List<T>> result = new ArrayList<List<T>>();
        if(n==0){
            result.add(new ArrayList<T>());
            return result;
        }
        List<List<T>> combanations = findSortedCombinations(elements, n-1);
        for(List<T> combanation : combanations) {
            for (T element : elements) {
                if (combanation.contains(element)) {
                    continue;
                }

                List<T> list = new ArrayList<T>();
                list.addAll(combanation);
                if (list.contains(element)) {
                    continue;
                }
                list.add(element);
                Collections.sort(list);
                if (result.contains(list)) {
                    continue;
                }
                result.add(list);
            }
        }
        return result;
    }
}
