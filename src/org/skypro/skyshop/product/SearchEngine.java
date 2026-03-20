package org.skypro.skyshop.product;
import org.skypro.skyshop.Searchable;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import java.util.*;

public class SearchEngine {
    private Set<Searchable> elements = new HashSet<>();

    public void add(Searchable item) {
        elements.add(item);
    }



    public List<Searchable> search(String query) {
        Supplier<TreeSet<Searchable>> treeSetSupplier =
                () -> new TreeSet<>(new LengthComparator());
        return elements.stream()
                .filter(element -> element.getSearchTerm()
                        .toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toCollection(treeSetSupplier))
                .descendingSet()  // TreeSet → List через descendingSet()
                .stream()
                .collect(Collectors.toList());
    }

    public TreeMap<String, Searchable> searchMap(String searchTerm) {
        TreeMap<String, Searchable> results = new TreeMap<>();
        for (Searchable element : elements) {
            if (element.getSearchTerm().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.put(element.getName(), element);
            }
        }
        return results;
    }

    public Searchable findBest(String search) throws BestResultNotFound {
        Searchable best = null;
        int maxCount = -1;

        for (Searchable element : elements) {
            if (element == null) continue;
            String term = element.getSearchTerm();
            int count = 0;
            int index = 0;
            while ((index = term.indexOf(search, index)) != -1) {
                count++;
                index += search.length();
            }
            if (count > maxCount) {
                maxCount = count;
                best = element;
            }
        }
        if (best == null) {
            throw new BestResultNotFound(search);
        }
        return best;
    }


    private static class LengthComparator implements Comparator<Searchable> {
        @Override
        public int compare(Searchable a, Searchable b) {
            int lenCmp = Integer.compare(b.getName().length(), a.getName().length());
            return lenCmp != 0 ? lenCmp : a.getName().compareTo(b.getName());
        }
    }
}