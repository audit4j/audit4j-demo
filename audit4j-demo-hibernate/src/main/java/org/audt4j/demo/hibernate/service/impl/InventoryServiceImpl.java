package org.audt4j.demo.hibernate.service.impl;

import org.audit4j.core.AuditManager;
import org.audit4j.core.dto.EventBuilder;
import org.audt4j.demo.hibernate.service.InventoryService;
import org.audt4j.demo.hibernate.service.Item;
import org.springframework.stereotype.Service;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

    @Override
    public void addItem(Item item) {
        AuditManager.getInstance().audit(
                new EventBuilder().addAction("addInventoryItem").addField("itemName", item.getName())
                        .addField("itemValue", item.getValue()).build());
        
        //Method Body
    }
}
