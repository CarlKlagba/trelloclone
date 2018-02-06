import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Ng2DragDropModule } from 'ng2-drag-drop';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ProjectComponent } from './project/project.component';
import { StageComponent } from './stage/stage.component';
import { ItemService } from './item/item.service';


@NgModule({
  declarations: [
    AppComponent,
    ProjectComponent,
    StageComponent
  ],
  entryComponents: [StageComponent],
  imports: [
    BrowserModule,
    Ng2DragDropModule.forRoot(),
    FormsModule
  ],
  providers: [ItemService],
  bootstrap: [AppComponent]
})
export class AppModule { }
