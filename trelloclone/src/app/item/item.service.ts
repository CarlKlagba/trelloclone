import { Injectable } from '@angular/core';
import { Item } from './item.model';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItemService {
    stagesItemList: any = {};

    numberStages: number;

    private publishedItem  = {stageId : '', item: <Item> {name: null}};

    constructor() {
        this.numberStages = 0;
    }

    public createStage(listItem: Item[]): string {
        this.numberStages++;
        const idStage = 'stage' + this.numberStages;
        this.stagesItemList[idStage] = listItem;
        return idStage;
    }

    public getStage(id: string): Item[] {
        return this.stagesItemList[id];
    }

    public moveItem(sourceStage: string, destStage: string, item: Item): void {
        this.stagesItemList[destStage].push(item);
        this.removeItem(item, this.stagesItemList[destStage]);
    }

    public publishItem(stageId: string, item: Item) {
         this.publishedItem = {stageId : stageId, item: item};
         console.log('publishItem ' + this.publishedItem);
    }

    public consumItem(sourceStage: string, destStage: string) {
        console.log('consum Item start ' + sourceStage + '   ' + this.publishedItem.stageId);
        console.log(sourceStage === this.publishedItem.stageId);
       if (sourceStage === this.publishedItem.stageId) {
           this.moveItem(sourceStage, destStage, this.publishedItem.item);
       }
       console.log('consum Item done ' + sourceStage + '  '  + destStage);
    }

    removeItem(item: Item, list: Item[]) {
        const index = list.map(function (e) {
            return e.name;
        }).indexOf(item.name);
        list.splice(index, 1);
    }
}
