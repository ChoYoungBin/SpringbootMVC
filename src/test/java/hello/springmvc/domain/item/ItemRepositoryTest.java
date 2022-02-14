package hello.springmvc.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void test_Save(){
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findBy(savedItem.getId());
        assertThat(savedItem).isEqualTo(findItem);
    }

    @Test
    void test_findAll(){
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 200);
        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void test_Update(){
        //given
        Item originItem = new Item("itemA", 10000, 10);
        Item updatedItem = new Item("itemB", 20000, 200);
        itemRepository.save(originItem);

        //when
        itemRepository.update(originItem.getId(), updatedItem);
        Item resultItem = itemRepository.findBy(originItem.getId());

        //then
        assertThat(resultItem.getId()).isEqualTo(originItem.getId());
        assertThat(resultItem.getItemName()).isEqualTo(updatedItem.getItemName());
        assertThat(resultItem.getPrice()).isEqualTo(updatedItem.getPrice());
        assertThat(resultItem.getQuantity()).isEqualTo(updatedItem.getQuantity());
    }
}
