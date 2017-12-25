import { Component, OnInit, Input, ComponentFactory, ComponentRef, ComponentFactoryResolver, ViewContainerRef, ChangeDetectorRef, TemplateRef, ViewChild, Output } from '@angular/core';

import { StageComponent } from '../stage/stage.component';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})

export class ProjectComponent implements OnInit {
	@ViewChild("stageContainer", { read: ViewContainerRef }) container;
 	componentRef: ComponentRef<StageComponent>;
 	
 	listProjects = {
		project1: [
				{name: 'Tea'},
				{name: 'Milk'},
				{name: 'Coffee'}
			],
		project2: []
	};
	numberStages : number;

  	constructor(private resolver: ComponentFactoryResolver) {}

  	ngOnInit() {
  		this.numberStages = 1;
  	};

  	onItemDrop(e: any, project : string){
  		this.listProjects[project].push(e.dragData.item);
  		this.removeItem(e.dragData.item, this.listProjects[e.dragData.project]);
  	};


  	removeItem(item: any, list: Array<any>) {
	    let index = list.map(function (e) {
	      return e.name
	    }).indexOf(item.name);
	    list.splice(index, 1);
	}

	createComponent(){
	    const factory: ComponentFactory<StageComponent> =
	    	this.resolver.resolveComponentFactory(StageComponent);

	    this.componentRef = this.container.createComponent(factory);
	    
	    this.componentRef.instance.id = "stage" + this.numberStages;
	    this.numberStages++;
	}
}
