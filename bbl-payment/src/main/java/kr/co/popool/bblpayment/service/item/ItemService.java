package kr.co.popool.bblpayment.service.item;

import kr.co.popool.bblpayment.domain.dto.item.ItemDto;

import java.util.List;

public interface ItemService {

    public void enrollItem(ItemDto.CREATE createDto);
    public List<ItemDto.READ> findAllItems();
    public ItemDto.DETAIL findItemDetail(Long itemId);
    public void updateItem(Long itemId);
    public void deleteItem(Long itemId);
    public int getItemPrice(Long itemId);
}
