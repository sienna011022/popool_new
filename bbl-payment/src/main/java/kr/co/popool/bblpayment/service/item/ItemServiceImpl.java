package kr.co.popool.bblpayment.service.item;

import kr.co.popool.bblpayment.domain.dto.item.ItemDto;
import kr.co.popool.bblpayment.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public void enrollItem(ItemDto.CREATE createDto) {

    }

    @Override
    public List<ItemDto.READ> findAllItems() {
        return null;
    }

    @Override
    public ItemDto.DETAIL findItemDetail(Long itemId) {
        return null;
    }

    @Override
    public void updateItem(Long itemId) {

    }

    @Override
    public void deleteItem(Long itemId) {

    }
}
