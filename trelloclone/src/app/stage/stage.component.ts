import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-stage',
  templateUrl: './stage.component.html',
  styleUrls: ['./stage.component.css']
})
export class StageComponent implements OnInit {
	
	@Input() id : string;

	listItem = [];

	constructor() {
	}

	ngOnInit() {

	}

	onItemDrop(e: any){
  		this.listItem.push(e.dragData.item);
  	};
}
