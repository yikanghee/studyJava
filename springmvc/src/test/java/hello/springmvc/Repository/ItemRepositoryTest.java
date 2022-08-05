package hello.springmvc.Repository;

import hello.springmvc.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class ItemRepositoryTest {

    ItemRepository itemRepository;

    @BeforeEach
    void Before() {
        itemRepository.clearScore();
    }

    @Test
    @DisplayName("save")
    void save() {
        Item item = new Item("아이템1", 1000, 10);
        Item saveItem = itemRepository.save(item);

        Item findById = itemRepository.findById(saveItem.getId());
        Assertions.assertThat(saveItem).isEqualTo(findById);
    }

    @Test
    @DisplayName("findAll")
    void findAll() {
        Item item = new Item("아이템1", 1000, 1);
        Item item1 = new Item("아이템2", 1000, 2);

        itemRepository.save(item);
        itemRepository.save(item1);

        List<Item> all = itemRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);
        Assertions.assertThat(all).contains(item, item1);
    }

}