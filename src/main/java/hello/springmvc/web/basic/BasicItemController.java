package hello.springmvc.web.basic;

import hello.springmvc.domain.item.Item;
import hello.springmvc.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findBy(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    /**
     * ModelAttribute 쓰기전!!
     *
     * @param itemName
     * @param price
     * @param quantity
     * @param model
     * @return

     @PostMapping("/add") public String addItemV1(@RequestParam String itemName,
     @RequestParam int price,
     @RequestParam Integer quantity,
     Model model) {
     Item item = new Item();
     item.setItemName(itemName);
     item.setPrice(price);
     item.setQuantity(quantity);

     itemRepository.save(item);
     model.addAttribute("item", item);

     return "basic/item";
     }
     */

    /**
     * @param item
     * @return
     * @ModelAttribute 는 Request의 RequestParam도 처리하고, Response로 Model에 View 영역도 같은 이름으로 넣어준다.

     @PostMapping("/add") public String addItemV2(@ModelAttribute("item") Item item) {
     // Model model) {
     itemRepository.save(item);
     //@ModelAttribute 는 자동으로 View 에 자동으로 넣어준다. "item"이라는 이름으로.
     //그래서 param에 model날려도되고, addAttribute도 필요가없다.
     //model.addAttribute("item", item);

     return "basic/item";
     }*/

    /**
     * @param item
     * @return
     * @ModelAttribute 의 ("name") 도 생략

     @PostMapping("/add") public String addItemV3(@ModelAttribute Item item) {
     itemRepository.save(item);
     //@ModelAttribute 의 ()를 생략하면 Item -> item 으로 name이 자동생성된다.

     return "basic/item";
     }
     */

    /**
     * @param item
     * @return
     * @ModelAttribute까지도 생략가능
     */
/*    @PostMapping("/add")
    public String addItemV3(Item item) {
        itemRepository.save(item);
        // @ModelAttribute를 생략하면 Item을 RequestParam, Response 객체로 인식하고
        // Item -> item 으로 name을 자동생성까지 해준다.

        return "basic/item";
    }*/
/*    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);

        //PRG 해결
        return "redirect:/basic/items/" + item.getId();
    }*/
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);


        //Redirect Attribute
        //PathVariable 값이 포함되어있으면 param 매핑작업 (ex.itemId = {itemId})
        //값이 포함안되어있으면 QueryParam 으로 세팅됨 (ex. ?status=true )
        //URL 인코딩도 알아서 해줌
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findBy(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";

    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
