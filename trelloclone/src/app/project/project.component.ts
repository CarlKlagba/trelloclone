import { Component, OnInit, Input, ComponentFactory, ComponentRef,
    ComponentFactoryResolver, ViewContainerRef, ChangeDetectorRef,
    TemplateRef, ViewChild, Output } from '@angular/core';

import { StageComponent } from '../stage/stage.component';
import { Item } from '../item/item.model';
import { ItemService } from '../item/item.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})

export class ProjectComponent implements OnInit {
    @ViewChild('stageContainer', { read: ViewContainerRef }) container;
    componentRef: ComponentRef<StageComponent>;

    stageItemList1: Item[] = [
        {name: 'Tea'},
        {name: 'Milk'},
        {name: 'Coffee'},
        {name: 'Cacao'}
    ];

    constructor(private resolver: ComponentFactoryResolver, private itemService: ItemService) {}

    ngOnInit() {
        this.createStage(this.stageItemList1);
    }

    createStage(listItem: Item[]) {
	    const factory: ComponentFactory<StageComponent> =
	    	this.resolver.resolveComponentFactory(StageComponent);
	    this.componentRef = this.container.createComponent(factory);
	    this.componentRef.instance.id = this.itemService.createStage(listItem);
    }
}
