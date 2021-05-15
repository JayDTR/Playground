import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { GameControlComponent } from './GameControl/GameControl.component';
import { OddComponent } from './Odd/Odd.component';
import { EvenComponent } from './Even/Even.component';

@NgModule({
  declarations: [			
    AppComponent,
      GameControlComponent,
      OddComponent,
      EvenComponent
   ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
