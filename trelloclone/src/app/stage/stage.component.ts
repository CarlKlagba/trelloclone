import { Component, DoCheck, OnInit, Input } from '@angular/core';
import { Item } from '../item/item.model';
import { ItemService } from '../item/item.service';
import { Subscription } from 'rxjs/Subscription';
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-stage',
  templateUrl: './stage.component.html',
  styleUrls: ['./stage.component.css', '../app.component.css']
})
export class StageComponent implements DoCheck, OnInit {

    @Input() id: string;

    listItem: Item[];
    dragEvent: Observable<any>;

    constructor(private itemService: ItemService) {}

    ngDoCheck() {
        this.listItem = this.itemService.getStage(this.id);
    }

    ngOnInit(){
      this.dragEvent = this.itemService.dragEvent();
    }

    onItemDrop(e: any) {
      console.log('onItemDrop');
      this.dragEvent..then( value => {
        if(value.stageId !== this.id){
          console.log('subscribe   '+ value.stageId + '    ID Curr  ' + this.id+ '  ITEM ' + value.item.name);
          this.itemService.moveItem(value.stageId, this.id, value.item);
        }
      });
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
