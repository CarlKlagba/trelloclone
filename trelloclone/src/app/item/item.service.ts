import { Injectable } from '@angular/core';
import { Item } from './item.model';
import { Subject } from 'rxjs/Subject';
import {Observable} from "rxjs/Observable";

@Injectable()
export class ItemService {
    stagesItemList: any = {};

    numberStages: number;

    private _dragEvent = new Subject<any>();

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
        console.log('getStage ');
        return this.stagesItemList[id];
    }

    public moveItem(sourceStage: string, destStage: string, item: Item): void {
        console.log(' moveItem   destStage '+ destStage + '     sourceStage  '+ sourceStage + '     item '+item.name);
        this.stagesItemList[destStage].push(item);
        this.removeItem(item, this.stagesItemList[sourceStage]);
        console.log(this.stagesItemList[destStage]);
    }

    public publishItem(stageId: string, item: Item) {
         console.log('publishItem ');
         this._dragEvent.next({stageId : stageId, item: item});
    }

    public dragEvent(): Observable<any> {
          return this._dragEvent.asObservable();
    }

    removeItem(item: Item, list: Item[]) {
        const index = list.map(function (e) {
            return e.name;
        }).indexOf(item.name);
        list.splice(index, 1);
    }
}
