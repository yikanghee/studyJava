package hello.springmvc.Repository;

import hello.springmvc.domain.Item;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 1L;

    public Item save(Item item) {
        item.setId(sequence++);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item oldItem = store.get(itemId);
        oldItem.setItemName(updateParam.getItemName());
        oldItem.setPrice(updateParam.getPrice());
        oldItem.setQuantity(updateParam.getQuantity());
    }

    public void clearScore() {
        store.clear();
    }
}
