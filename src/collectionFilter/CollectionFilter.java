package collectionFilter;

import java.util.ArrayList;
import java.util.List;

public class CollectionFilter {
    List<Object> filter(List<Object> list, FilterImpl filter){
        List<Object> filteredList = new ArrayList<Object>();
        for(Object o : list){
            filteredList.add(filter.apply(list));
        }
        return filteredList;
    }
}
