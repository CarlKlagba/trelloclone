import { Component, DoCheck, Input } from '@angular/core';
import { Item } from '../item/item.model';
import { ItemService } from '../item/item.service';
import { SimpleChange } from '@angular/core/src/change_detection/change_detection_util';

@Component({
  selector: 'app-stage',
  templateUrl: './stage.component.html',
  styleUrls: ['./stage.component.css', '../app.component.css']
})
export class StageComponent implements DoCheck {

    @Input() id: string;

    listItem: Item[];

    constructor(private itemService: ItemService) {}

    ngDoCheck() {
        this.listItem = this.itemService.getStage(this.id);
    }

    onItemDrop(e: any) {
		this.itemService.consumItem(e.dragData.stage, this.id);
    }

    onItemDrag(item: Item) {
        this.itemService.publishItem(this.id, item);
    }

    removeItem(item: any, list: Array<any>) {
        const index = list.map(function (e) {
            return e.name;
        }).indexOf(item.name);
        list.splice(index, 1);
    }
}
